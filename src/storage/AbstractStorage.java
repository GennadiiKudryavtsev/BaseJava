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
        Object search = getSearchKey(r.getUuid());
        if (!isExisting(search)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            doUpdate(r, search);
            throw new ExistStorageException(r.getUuid());
        }
    }

    public final void save(Resume r) {
        Object search = getSearchKey(r.getUuid());
        if (isExisting(search)) {
            throw new ExistStorageException(r.getUuid());
        }  else {
            doSave(r, search);
        }
    }

    public final void delete(String uuid) {
        Object search = getSearchKey(uuid);
        if (!isExisting(search)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(search);
        }
    }
//
    public final Resume get(String uuid) {
        Object search = getSearchKey(uuid);
        if (!isExisting(search)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(search);
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
