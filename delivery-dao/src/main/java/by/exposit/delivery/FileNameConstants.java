package by.exposit.delivery;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

@Slf4j
public final class FileNameConstants {

    private FileNameConstants(){}

    private static final String PATH_TO_RESOURCES_FOLDER;

    static {
        URL res = FileNameConstants.class.getClassLoader().getResource("database");
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        PATH_TO_RESOURCES_FOLDER = file.getAbsolutePath();
        log.info("Path to resource folder: '{}'", PATH_TO_RESOURCES_FOLDER);
    }

    public static final String CLIENT_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "client";
    public static final String STORE_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "store";
    public static final String PRODUCT_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "product";
    public static final String ORDER_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "order";
}
