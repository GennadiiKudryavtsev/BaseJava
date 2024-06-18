package storage;

import exceptions.ExistStorageException;
import exceptions.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage<T> implements Storage{

    public final int size() {
        return doSize();
    }

    public final void clear() {
        doClear();
    }

    public final void update(Resume r) {
        T searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public final void save(Resume r) {
        T searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public final void delete(String uuid) {
        T searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }
//
    public final Resume get(String uuid) {
        T searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    private T getExistingSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (!isExisting(searchKey)) {
            throw new NotExistStorageException((uuid));
        }
        return searchKey;
    }

    private T getNotExistingSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (isExisting(searchKey)) {
            throw new ExistStorageException((uuid));
        }
         return searchKey;
    }

    protected abstract int doSize();
    protected abstract void doClear();
    protected abstract void doUpdate(Resume r, T searchKey);
    protected abstract void doSave(Resume r, T searchKey);
    protected abstract void doDelete(T searchKey);
    protected abstract Resume doGet(T searchKey);
    protected abstract T getSearchKey(String uuid);
    protected abstract boolean isExisting(T searchKey);
}
