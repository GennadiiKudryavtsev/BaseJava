package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer>{

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
    protected void doUpdate(Resume r, Integer searchKey) {
        list.set(searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        list.add(r);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        list.remove((searchKey.intValue()));
        System.out.println(list.size());
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExisting(Integer searchKey) {
        return searchKey >= 0;
    }


    @Override
    public List<Resume> getAllSorted() {
        return list;
    }

}
