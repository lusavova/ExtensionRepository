package telerikacademy.extensionrepository.areas.tags.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import telerikacademy.extensionrepository.areas.products.models.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String tagName;

    @ManyToMany (mappedBy = "tags", targetEntity = Product.class)
    @JsonBackReference
    private List<Product> products;

    public Tag() {
        products = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
