package telerikacademy.extensionrepository.areas.files.validator;

import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.areas.files.exeptions.StorageException;

import java.io.IOException;

public class ZipValidator {
    private static final int ZIP_NUM_OF_BYTES = 4;
    private static final int RAR_NUM_OF_BYTES = 6;
    private static final String ZIP_FILE_FORMAT_SIGNATURE = "50 4B 03 04";
    //    private static final String ZIP_FILE_FORMAT_SIGNATURE_EMPTY = "50 4B 05 06";
//    private static final String ZIP_FILE_FORMAT_SIGNATURE_SPANNED = "50 4B 07 08";
    private static final String RAR_FILE_FORMAT_SIGNATURE = "52 61 72 21 1A 07";

    public static void checkFile(MultipartFile file) {
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!isValidZipOrRarFile(bytes, ZIP_FILE_FORMAT_SIGNATURE, ZIP_NUM_OF_BYTES) &&
                !isValidZipOrRarFile(bytes, RAR_FILE_FORMAT_SIGNATURE, RAR_NUM_OF_BYTES)) {
            throw new StorageException("Not a zip or rar file!");
        }
    }

    private static boolean isValidZipOrRarFile(byte[] bytes, String type, int numberOfBytes) {
        StringBuilder bytesHexString = new StringBuilder();
        for (int i = 0; i < numberOfBytes; i++) {
            bytesHexString.append(String.format("%02X ", bytes[i]));
        }

        String result = bytesHexString.toString().trim();
        return result.equals(type);
    }
}
