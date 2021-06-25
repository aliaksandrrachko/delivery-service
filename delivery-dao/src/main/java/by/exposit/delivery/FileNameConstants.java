package by.exposit.delivery;

import java.io.File;
import java.util.Objects;

public final class FileNameConstants {

    private FileNameConstants(){}

    public static final String PATH_TO_RESOURCES_FOLDER;

    static {
        ClassLoader classLoader = FileNameConstants.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("database")).getFile());
        PATH_TO_RESOURCES_FOLDER = file.toString();
    }

    public static final String CLIENT_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "client";
    public static final String STORE_FOLDER_PATH = PATH_TO_RESOURCES_FOLDER + File.separator + "store";
}
