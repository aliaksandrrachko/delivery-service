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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class load data from file and save it in file
 */
@Slf4j
public class JsonDataFileUploader {

    private static final String JSON_EXTENSION = ".json";

    private JsonDataFileUploader(){ }

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        mapper.findAndRegisterModules();
    }

    public static <T extends AEntity<K>, K extends Number> List<T> load(Class<T> clazz, String folderName) {
        List<T> result = new ArrayList<>();
        try {
            File[] files = new File(folderName).listFiles();
            if (files == null) {
                return Collections.emptyList();
            }
            for (File file : files) {
                T entity = mapper.readValue(file, clazz);
                result.add(entity);
            }
            return result;
        } catch (IOException e) {
            log.error("Exception happened when you trying load data from file {}, message: {}", folderName, e.getMessage());
        }
        return Collections.emptyList();
    }

    public static <T extends AEntity<K>, K extends Number> Optional<T> load(Class<T> clazz, K id, String folderName){
        String fileName = folderName + id.toString() + JSON_EXTENSION;
        if (!Files.exists(Path.of(fileName))){
            return Optional.empty();
        }
        try {
            return Optional.of(mapper.readValue(new File(fileName), clazz));
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

    public static <T extends AEntity<K>, K extends Number> void save(T entity, String folderName){
        String fileName = folderName + entity.getId().toString() + JSON_EXTENSION;
        try {
            mapper.writeValue(new File(fileName), entity);
        } catch (IOException e) {
            log.error("Exception happened when you trying save entity to file {}, message: {}", fileName, e.getMessage());
        }
    }

    public static <K extends Number> void delete(K id, String folderName){
        String fileName = folderName + id.toString() + JSON_EXTENSION;
        try {
            Files.delete(Path.of(fileName));
        } catch (IOException e) {
            log.error("Exception happened when you trying delete entity, id={}, message: {}", id, e.getMessage());
        }
    }

    public static <K extends Number> K findLastId(String folderName){
        List<String> files = Stream.of(new File(folderName).listFiles())
                .map(file -> file.getName().replace(JSON_EXTENSION, "")).collect(Collectors.toList());
        return null;
    }
}
