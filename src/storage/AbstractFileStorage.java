package storage;

import exceptions.StorageException;
import model.Resume;
import java.io.File;
import java.io.IOException;
import java.util.*;

public abstract class AbstractFileStorage extends AbstractStorage<File>{
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is nor readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected int doSize() {
        try {
            return doSizeFiles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doClear() {
        File[] dir = directory.listFiles();
        if (dir == null) return;
        for (File f: dir) {
           f.delete();
        }
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) { // удаляет файл
        file.delete();
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExisting(File file) {
        return file.exists();
    }

    @Override
    public List<Resume> doCopyAll() {
        File file = new File(directory.getAbsolutePath());
        File[] files = file.listFiles();
        assert files != null;
        List<Object> o = new ArrayList<>(Arrays.asList(files));
        List<Resume> listResumes = new ArrayList<>((Collection) o);
        for (int i = 0; i<o.size(); i++) {
            System.out.println(o.get(i));
        }
        return listResumes;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;
    protected abstract Resume doRead(File file) throws IOException;
    protected abstract int doSizeFiles() throws IOException;

}
