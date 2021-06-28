package by.exposit.delivery;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@Slf4j
public final class FileNameConstants {

    private FileNameConstants(){}

    private static final String PATH_TO_RESOURCES_FOLDER;

    static {
        URI uri = null;
        try {
            uri = ClassLoader.getSystemResource("database").toURI();
        } catch (URISyntaxException e) {
            log.warn("Database not found: {}", e.getMessage());
        }
        PATH_TO_RESOURCES_FOLDER = Paths.get(uri).toString();
    }

    public static final String CLIENT_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "client";
    public static final String STORE_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "store";
    public static final String PRODUCT_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "product";
    public static final String ORDER_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "order";
}
