package storage;

import model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{

    protected Map<String, Resume> map = new HashMap<>();
    @Override
    protected int doSize() {
        return map.size();
    }

    @Override
    protected void doClear() {
        map.clear();
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.replace((String) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        if (isExisting(uuid)) {
            return uuid;
        }
        return -1;
    }

    @Override
    protected boolean isExisting(Object searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }
}
