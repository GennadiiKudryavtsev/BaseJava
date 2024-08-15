package com.urise.webapp.storage;

import com.urise.webapp.exceptions.ExistStorageException;
import com.urise.webapp.exceptions.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<T> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public final int size() {
        return doSize();
    }

    public final void clear() {
        doClear();
    }

    public final void update(Resume r) {
//        LOG.info("Update " + r);
        T searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public final void save(Resume r) {
//        LOG.info("Save " + r);
        T searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public final void delete(String uuid) {
//        LOG.info("Delete " + uuid);
        T searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        T searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    private T getExistingSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (!isExisting(searchKey)) {
//            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException((uuid));
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
//        LOG.info("getAllSorted");
        List<Resume> list = doCopyAll();
        return list;
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

    protected abstract List<Resume> doCopyAll();
}
