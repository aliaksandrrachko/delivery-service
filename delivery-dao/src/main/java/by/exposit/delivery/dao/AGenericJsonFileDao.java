package by.exposit.delivery.dao;

import by.exposit.delivery.api.dao.IAGenericDao;
import by.exposit.delivery.entities.AEntity;
import by.exposit.delivery.utils.JsonDataFileUploader;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AGenericJsonFileDao<T extends AEntity<K>, K extends Number> implements IAGenericDao<T, K> {

    private final Class<T> clazz;
    protected final String dataFolderPath;

    protected List<T> cache;

    protected void initCacheMethod() {
        cache = JsonDataFileUploader.load(clazz, dataFolderPath);
        if (cache == null) {
            cache = new LinkedList<>();
        }
    }

    protected AGenericJsonFileDao(Class<T> clazz, String dataFolderPath) {
        this.clazz = clazz;
        this.dataFolderPath = dataFolderPath;
    }

    @Override
    public Class<T> getGenericClass() {
        return clazz;
    }

    @Override
    public List<T> getAll() {
        return cache;
    }

    @Override
    public Optional<T> get(K id) {
        return cache.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(K id) {
        cache = cache.parallelStream().filter(e -> !e.getId().equals(id)).collect(Collectors.toCollection(LinkedList::new));
        JsonDataFileUploader.delete(id, dataFolderPath);
    }

    @Override
    public K getLastKey() {
        List<K> ids = cache.stream().map(AEntity::getId).sorted().collect(Collectors.toList());
        if (ids.isEmpty()){
            return null;
        } else {
            return ids.get(ids.size() - 1);
        }
    }

    @Override
    public Optional<T> create(T entity) {
        cache.add(entity);
        JsonDataFileUploader.save(entity, dataFolderPath);
        return Optional.of(entity);
    }

    @Override
    public Optional<T> update(K id, T entity) {
        var entityInCache = cache.parallelStream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        if (entityInCache == null) {
            return Optional.empty();
        } else {
            cache = cache.parallelStream().filter(e -> !e.getId().equals(id)).collect(Collectors.toCollection(LinkedList::new));
            entity.setId(id);
            cache.add(entity);
            JsonDataFileUploader.save(entity, dataFolderPath);
            return Optional.of(entity);
        }
    }
}
