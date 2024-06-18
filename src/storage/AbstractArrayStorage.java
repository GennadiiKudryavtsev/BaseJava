package storage;

import exceptions.*;
import model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

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
    protected void doUpdate(Resume r, Object searchKey) {
        storage[size] = r;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, (int)searchKey);
            size++;
        }
    }

    @Override
    protected void doDelete(Object searchKey) {
            fillDeletedElement((int)searchKey);
            storage[size - 1] = null;
            size--;
    }

    @Override
    protected Resume doGet(Object searchKey) {
            return storage[(int) searchKey];
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
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExisting(Object searchKey) {
        return (int)searchKey >= 0;
    }
}
