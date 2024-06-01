package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage{

    protected List<Resume> list = new ArrayList<>();

    @Override
    protected int doSize() {
        return list.size();
    }

    @Override
    protected void doClear() {
        list.clear();
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        list.set((int)searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        list.add(r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        list.remove((int)searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return list.get((int) searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExisting(Object searchKey) {
        return (int)searchKey >= 0;
    }


    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
    }

}
