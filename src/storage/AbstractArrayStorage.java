package storage;

import exceptions.*;
import model.Resume;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;
    @Override
    protected int doSize() {
        return size;
    }

    @Override
    protected void doClear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        storage[searchKey] = r;
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, searchKey);
            size++;
        }
    }

    @Override
    protected void doDelete(Integer searchKey) {
            fillDeletedElement(searchKey);
            storage[size - 1] = null;
            size--;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
            return storage[searchKey];
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = Arrays.asList(Arrays.copyOf(storage, size));
        Collections.sort(list);
        System.out.println(list);
        return list;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    @Override
    protected abstract Integer getSearchKey(String uuid);

    @Override
    protected boolean isExisting(Integer searchKey) {
        return searchKey >= 0;
    }
}