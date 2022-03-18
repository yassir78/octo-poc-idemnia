package ma.octo.poc.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class FileConverter {

    public static File decode(String encodedString) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        File file = File.createTempFile("decoded", ".tmp");
        FileUtils.writeByteArrayToFile(file, decodedBytes);
        return file;
    }
}
