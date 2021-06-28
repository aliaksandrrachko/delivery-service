package by.exposit.delivery;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public final class FileNameConstants {

    private FileNameConstants(){}

    private static final String DATABASE_FOLDER_NAME = "database";
    private static final String PATH_TO_RESOURCES_FOLDER;

    static {
        URI jarFileUri = null;
        try {
            jarFileUri = FileNameConstants.class.getProtectionDomain().getCodeSource().getLocation().toURI();
            log.info("{} file uri: {}.", FileNameConstants.class, jarFileUri.getPath());
            log.info("{}, parent path: {}.", FileNameConstants.class, new File(jarFileUri).getParent());
        } catch (URISyntaxException e) {
            log.warn("Database not fount: {}.", e.getMessage());
        }

        File pathToDatabase =  new File(new File(jarFileUri).getParent() + File.separator  + DATABASE_FOLDER_NAME);
        if (!pathToDatabase.exists()){
            pathToDatabase = new File(
                    new File(jarFileUri).getParent() +
                            File.separator + "classes" + File.separator + DATABASE_FOLDER_NAME);
        }

        PATH_TO_RESOURCES_FOLDER = pathToDatabase.getPath();
        log.info("Path to resource folder: '{}'", PATH_TO_RESOURCES_FOLDER);
    }

    public static final String CLIENT_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "client";
    public static final String STORE_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "store";
    public static final String PRODUCT_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "product";
    public static final String ORDER_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "order";
}
