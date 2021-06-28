package by.exposit.delivery.utils;

import by.exposit.delivery.entities.AEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * This class load data from file and save it in file
 */
@Slf4j
public class JsonDataFileUploader {

    private static final String JSON_EXTENSION = ".json";

    private JsonDataFileUploader() {
    }

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        OBJECT_MAPPER.findAndRegisterModules();
    }

    public static <T extends AEntity<K>, K extends Number> List<T> load(Class<T> clazz, String folderName) {
        LinkedList<T> result = new LinkedList<>();
        try {
            File dataBaseFolder = new File(folderName);
            if (!dataBaseFolder.exists()){
                Files.createDirectory(dataBaseFolder.toPath());
            }

            File[] files = dataBaseFolder.listFiles();
            if (files == null) {
                return result;
            }
            for (File file : files) {
                T entity = OBJECT_MAPPER.readValue(file, clazz);
                result.add(entity);
            }
            return result;
        } catch (IOException e) {
            log.error("Exception happened when you trying load data from file {}, message: {}, database is empty.",
                    folderName, e.getMessage());
        }
        return result;
    }

    public static <T extends AEntity<K>, K extends Number> Optional<T> load(Class<T> clazz, K id, String folderName) {
        String fileName = folderName + File.separator + id.toString() + JSON_EXTENSION;
        if (!Files.exists(Path.of(fileName))) {
            return Optional.empty();
        }
        try {
            return Optional.of(OBJECT_MAPPER.readValue(new File(fileName), clazz));
        } catch (IOException e) {
            log.error("Exception happened when you trying load entity from file {}, message: {}", fileName, e.getMessage());
        }
        return Optional.empty();
    }

    public static <T extends AEntity<K>, K extends Number> void save(List<T> list, String folderName) {
        for (T entity : list) {
            save(entity, folderName);
        }
    }

    public static <T extends AEntity<K>, K extends Number> void save(T entity, String folderName) {
        String fileName = folderName + File.separator + entity.getId().toString() + JSON_EXTENSION;
        try {
            OBJECT_MAPPER.writeValue(new File(fileName), entity);
        } catch (IOException e) {
            log.error("Exception happened when you trying save entity to file {}, message: {}", fileName, e.getMessage());
        }
    }

    public static <K extends Number> void delete(K id, String folderName){
        String fileName = folderName + File.separator + id.toString() + JSON_EXTENSION;
        try {
            Files.delete(Path.of(fileName));
        } catch (IOException e) {
            log.error("Exception happened when you trying delete entity, id={}, message: {}", id, e.getMessage());
        }
    }
}
