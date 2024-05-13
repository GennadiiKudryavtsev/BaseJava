package storage;

import exceptions.ExistStorageException;
import exceptions.NotExistStorageException;
import exceptions.StorageException;
import model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[size] = r;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        System.out.println(index);
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        else if (isExisting(index)) {
            throw new ExistStorageException(r.getUuid());
        }  else {
            insertElement(r, index);
            size++;
        }
    }

    private boolean isExisting(int index) {
        return index >= 0;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public Resume[] getAll() {
        Resume[] result = Arrays.copyOf(storage, size);
        return result;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract int getIndex(String uuid);
}
