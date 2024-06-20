package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume>{

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
    protected void doUpdate(Resume r, Resume resume) {
        map.replace(resume.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Resume resume) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Resume resume) {
        map.remove(resume.getUuid());
    }

    @Override
    protected Resume doGet(Resume resume) {
        return map.get(resume.getUuid());
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExisting(Resume resume) {
        return resume != null;
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList(map.values());
    }
}
