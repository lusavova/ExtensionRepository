package telerikacademy.extensionrepository.validator;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public class ZipValidator {

    private static final String ZIP_FILE_FORMAT_SIGNATURE = "50 4B 03 04";
    private static final String ZIP_FILE_FORMAT_SIGNATURE_EMPTY = "50 4B 05 06";
    private static final String ZIP_FILE_FORMAT_SIGNATURE_SPANNED = "50 4B 07 08";
    private static final String RAR_FILE_FORMAT_SIGNATURE = "52 61 72 21 1A 07";

    public void validate(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();

        if (isValidZipOrRarFile(bytes, ZIP_FILE_FORMAT_SIGNATURE, 4) ||
                isValidZipOrRarFile(bytes, RAR_FILE_FORMAT_SIGNATURE, 6)){
            System.out.println("is valid");
        } else {
            System.out.println("NOT valid");
        }
    }

    private boolean isValidZipOrRarFile(byte[] bytes, String type, int numberOfBytes) {
        StringBuilder bytesHexString = new StringBuilder();
        for (int i = 0; i < numberOfBytes; i++) {
            bytesHexString.append(String.format("%02X ", bytes[i]));
        }

        String result = bytesHexString.toString().trim();
        System.out.println(result);
        if (result.equals(type)){
            return true;
        }
        return false;
    }

}
