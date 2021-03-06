package telerikacademy.extensionrepository.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import telerikacademy.extensionrepository.mapper.ProductDTOMapper;
import telerikacademy.extensionrepository.data.ProductsRepository;
import telerikacademy.extensionrepository.enums.ProductStatus;
import telerikacademy.extensionrepository.exceptions.ProductNotFoundExeption;
import telerikacademy.extensionrepository.models.File;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.dto.ProductDTO;
import telerikacademy.extensionrepository.services.base.StorageService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ProductServiceImplTests {
    private ProductServiceImpl productService;
    private ProductDTOMapper mockMapper = Mockito.mock(ProductDTOMapper.class);
    private ProductsRepository mockProductsRepository = Mockito.mock(ProductsRepository.class);
    private StorageService mockStorageService = Mockito.mock(StorageService.class);

    @Before
    public void setUp() {
        productService = new ProductServiceImpl(mockProductsRepository, mockMapper, mockStorageService);
    }

    @Test
    public void findById_should_returnProduct_when_productIsFound() {
        long id = 1;
        Product expectedProduct = new Product();
        Mockito.when(mockProductsRepository.findById(id)).thenReturn(Optional.of(expectedProduct));

        Product actualProduct = productService.findById(id);
        Assert.assertEquals(expectedProduct, actualProduct);
    }

    @Test(expected = ProductNotFoundExeption.class)
    public void findById_should_throwException_when_productIsNotFound() {
        long id = 1;
        Mockito.when(mockProductsRepository.findById(id)).thenReturn(Optional.empty());

        productService.findById(id);
    }

    @Test
    public void addProduct_should_saveProduct_when_productIsFound() {
        ProductDTO productDTO = new ProductDTO();
        Product expectedProduct = new Product();
        Mockito.when(mockMapper.mapProductDTOToProduct(productDTO)).thenReturn(expectedProduct);

        final boolean[] wasSaveInvoked = {false};
        Mockito.doAnswer( x -> {
            wasSaveInvoked[0] = true;
            return null;
        }).when(mockProductsRepository).saveAndFlush(expectedProduct);

        productService.addProduct(productDTO);
        Assert.assertTrue(wasSaveInvoked[0]);
    }

    @Test
    public void addProduct_should_setProductStatusToPending() {
        ProductDTO productDTO = new ProductDTO();
        Product expectedProduct = new Product();

        Mockito.when(mockMapper.mapProductDTOToProduct(productDTO)).thenReturn(expectedProduct);
        Mockito.when(mockProductsRepository.saveAndFlush(expectedProduct)).thenReturn(expectedProduct);

        Product actualProduct = productService.addProduct(productDTO);
        Assert.assertEquals(actualProduct.getProductStatus(), ProductStatus.PENDING.name());
    }

    @Test
    public void addProduct_should_setNumberOfDownloadsToZero() {
        ProductDTO productDTO = new ProductDTO();
        Product expectedProduct = new Product();

        Mockito.when(mockMapper.mapProductDTOToProduct(productDTO)).thenReturn(expectedProduct);
        Mockito.when(mockProductsRepository.saveAndFlush(expectedProduct)).thenReturn(expectedProduct);

        Product actualProduct = productService.addProduct(productDTO);
        Assert.assertEquals(actualProduct.getNumberOfDownloads(), 0);
    }

    @Test
    public void addProduct_should_setUploadDateToCurrentDate() {
        ProductDTO productDTO = new ProductDTO();
        Product expectedProduct = new Product();

        Mockito.when(mockMapper.mapProductDTOToProduct(productDTO)).thenReturn(expectedProduct);
        Mockito.when(mockProductsRepository.saveAndFlush(expectedProduct)).thenReturn(expectedProduct);

        Product actualProduct = productService.addProduct(productDTO);
        Assert.assertEquals(actualProduct.getUploadDate().toString(), new Date().toString());
    }

    @Test
    public void updateProduct_should_saveUpdatedProduct() {
        long id = 1;
        Product expectedProduct = new Product();
        Mockito.when(mockProductsRepository.findById(id)).thenReturn(Optional.of(expectedProduct));
        Product actualProduct = productService.findById(id);
        Assert.assertEquals(expectedProduct, actualProduct);

        final boolean[] wasSaveInvoked = {false};
        Mockito.doAnswer( x -> {
            wasSaveInvoked[0] = true;
            return null;
        }).when(mockProductsRepository).saveAndFlush(expectedProduct);

        productService.updateProduct(expectedProduct, id);
        Assert.assertTrue(wasSaveInvoked[0]);
    }

    /*
    * public void deleteProduct(long id) {
        Product product =  findById(id);
        productsRepository.deleteById(id);
        Path filePath = Paths.get(product.getFile().getDownloadLink());
        storageService.deleteFilesFromSystem(filePath);
        Path imagePath = Paths.get(product.getProductPicture().getDownloadLink());
        storageService.deleteFilesFromSystem(imagePath);
    }*/

    @Test
    public void deleteProduct_should_deleteProductFromDb() {
        long id = 1;
        Product expectedProduct = new Product();
        File file = new File();
        file.setDownloadLink("testDownloadLink");
        expectedProduct.setFile(file);

        File productPicture = new File();
        productPicture.setDownloadLink("testPictureDownloadLink");
        expectedProduct.setProductPicture(productPicture);

        Mockito.when(mockProductsRepository.findById(id)).thenReturn(Optional.of(expectedProduct));

        final boolean[] wasDeleteByIdInvoked = {false};
        Mockito.doAnswer(x -> {
            wasDeleteByIdInvoked[0] = true;
            return null;
        }).when(mockProductsRepository).deleteById(id);

        Path filePath = Paths.get(file.getDownloadLink());
        final boolean[] wasFileDeleteInvoked = {false};
        Mockito.doAnswer( x -> {
            wasFileDeleteInvoked[0] = true;
            return null;
        }).when(mockStorageService).deleteFilesFromSystem(filePath);

        Path imagePath = Paths.get(productPicture.getDownloadLink());
        final boolean[] wasImageDeleteInvoked = {false};
        Mockito.doAnswer( x -> {
            wasImageDeleteInvoked[0] = true;
            return null;
        }).when(mockStorageService).deleteFilesFromSystem(imagePath);

        productService.deleteProduct(id);

        Assert.assertTrue(wasDeleteByIdInvoked[0]);
        Assert.assertTrue(wasFileDeleteInvoked[0]);
        Assert.assertTrue(wasImageDeleteInvoked[0]);
    }

    @Test
    public void listAllProducts_should_returnAllProductsFromDb() {
        List<Product> expectedProducts = Arrays.asList(new Product(), new Product());
        Mockito.when(mockProductsRepository.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.listAllProducts();
        Assert.assertArrayEquals(expectedProducts.toArray(), actualProducts.toArray());
    }

    @Test
    public void listAllDisabledProducts_should_returnAllDisabledProductsFromDb() {
        List<Product> expectedProducts = Arrays.asList(new Product(), new Product());
        Mockito.when(mockProductsRepository.listAllDisabledProducts()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.listAllDisabledProducts();
        Assert.assertArrayEquals(expectedProducts.toArray(), actualProducts.toArray());
    }

    @Test
    public void listAllActiveProducts_should_returnAllActiveProductsFromDb() {
        List<Product> expectedProducts = Arrays.asList(new Product(), new Product());
        Mockito.when(mockProductsRepository.listAllActiveProducts()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.listAllActiveProducts();
        Assert.assertArrayEquals(expectedProducts.toArray(), actualProducts.toArray());
    }

    @Test
    public void listAllPendingProducts_should_returnAllPendingProductsFromDb() {
        List<Product> expectedProducts = Arrays.asList(new Product(), new Product());
        Mockito.when(mockProductsRepository.listAllPendingProducts()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.listAllPendingProducts();
        Assert.assertArrayEquals(expectedProducts.toArray(), actualProducts.toArray());
    }

    @Test
    public void changeProductStatus_should_changeProductStatusAndSaveToDb() {
        long id = 1;
        Product expectedProduct = new Product();
        Mockito.when(mockProductsRepository.findById(id)).thenReturn(Optional.of(expectedProduct));
        Mockito.doAnswer((Answer) x -> {
            Object arg0 = x.getArgument(0);
            Assert.assertEquals("Pending", ((Product)arg0).getProductStatus());
            return null;
        }).when(mockProductsRepository).saveAndFlush(expectedProduct);


        productService.changeProductStatus(id, "Pending");
    }

    @Test
    public void changeFeatureProductStatus_should_changeProductFeatureStatusAndSaveToDb() {
        long id = 1;
        Product expectedProduct = new Product();
        Mockito.when(mockProductsRepository.findById(id)).thenReturn(Optional.of(expectedProduct));
        Mockito.doAnswer((Answer) x -> {
            Object arg0 = x.getArgument(0);
            Assert.assertEquals(true, ((Product)arg0).isFeaturedProduct());
            return null;
        }).when(mockProductsRepository).saveAndFlush(expectedProduct);


        productService.changeFeatureProductStatus(id, true);
    }

    @Test
    public void githubRepoAlreadyExists_should_returnTrue_when_repoExists() {
        Product product = new Product();
        product.setSourceRepositoryLink("repoLink");

        List<Product> expectedProducts = Arrays.asList(new Product(), product);
        Mockito.when(mockProductsRepository.findAll()).thenReturn(expectedProducts);

        boolean doesExist = productService.githubRepoAlreadyExists("repoLink");
        Assert.assertTrue(doesExist);
    }

    @Test
    public void githubRepoAlreadyExists_should_returnFalse_when_repoDoesNotExists() {
        Product product = new Product();
        product.setSourceRepositoryLink("repoLink");

        List<Product> expectedProducts = Arrays.asList(new Product(), product);
        Mockito.when(mockProductsRepository.findAll()).thenReturn(expectedProducts);

        boolean doesExist = productService.githubRepoAlreadyExists("repoLinkNotExistent");
        Assert.assertFalse(doesExist);
    }
}
