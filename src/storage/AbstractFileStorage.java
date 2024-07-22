package storage;

import exceptions.StorageException;
import model.Resume;
import java.io.File;
import java.io.IOException;
import java.util.*;

public abstract class AbstractFileStorage extends AbstractStorage<File>{
    private final File directory;

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
        int count = 0;
        for (File f : getListFiles(directory)) {
            if (f != null) {
                count++;
            }else {
                throw new StorageException("File not found", null);
            }
        }
        return count;
    }

    @Override
    protected void doClear() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("File not found", null);
        }
        for (File f: files) {
            try {
                doDeleteFile(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        }catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        }catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        try {
            doDeleteFile(file);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (File f : getListFiles(directory)) {
            if (f == file) {
                throw new StorageException("File is exist", file.getName());
            }
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        }catch (IOException e) {
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
        List<Resume> listResumes = null;
        for (File f : getListFiles(directory)) {
            try {
                listResumes.add(doRead(f));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return listResumes;
    }

    public File[] getListFiles(File file) {
        File[] files = file.listFiles();
        return files;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;
    protected abstract Resume doRead(File file) throws IOException;
    protected abstract void doDeleteFile(File file) throws IOException;
}
