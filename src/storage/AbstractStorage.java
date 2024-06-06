package storage;

import exceptions.ExistStorageException;
import exceptions.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage{

    public final int size() {
        return doSize();
    }

    public final void clear() {
        doClear();
    }

    public final void update(Resume r) {
        Object searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public final void save(Resume r) {
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public final void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }
//
    public final Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExisting(searchKey)) {
            throw new NotExistStorageException((uuid));
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExisting(searchKey)) {
            throw new ExistStorageException((uuid));
        }
        return searchKey;
    }

    protected abstract int doSize();
    protected abstract void doClear();
    protected abstract void doUpdate(Resume r, Object searchKey);
    protected abstract void doSave(Resume r, Object searchKey);
    protected abstract void doDelete(Object searchKey);
    protected abstract Resume doGet(Object searchKey);
    protected abstract Object getSearchKey(String uuid);
    protected abstract boolean isExisting(Object searchKey);
}
