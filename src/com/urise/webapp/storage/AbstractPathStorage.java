package com.urise.webapp.storage;

import com.urise.webapp.exceptions.StorageException;
import com.urise.webapp.model.Resume;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path>{
    private Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }
    AbstractPathStorage() {}

    @Override
    protected int doSize() {
        int count = 0;
        try {
            DirectoryStream<Path> path = Files.newDirectoryStream(directory);
            for (Path files : path) {
                if (files.isAbsolute()) {
                    count++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    protected void doClear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
        if (path.isAbsolute()) {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        }
        } catch (IOException e) {
                throw new StorageException("IO error", path.getFileName().toString(), e);
            }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Path  getSearchKey(String uuid) {
       Path path = Paths.get(directory + "/" + uuid);
        return path;
    }

    @Override
    protected boolean isExisting(Path path) {
        return Files.exists(path);
    }

    @Override
    public List<Resume> doCopyAll() {
        List<Resume> listResumes = new ArrayList<>();
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory);
            for (Path p: directoryStream) {
                listResumes.add(doRead(new BufferedInputStream(new FileInputStream(p.toString()))));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listResumes;
    }

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;
    protected abstract Resume doRead(InputStream is) throws IOException;
}
