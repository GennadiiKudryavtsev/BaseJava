package com.urise.webapp.storage;

import com.urise.webapp.exceptions.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.strategy.Strategy;

import java.io.*;
import java.util.*;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final Strategy strategy;

    public FileStorage(File directory, Strategy strategy) {
        Objects.requireNonNull(directory, "directory must not be null");
        this.strategy = strategy;
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
        return getListFiles(directory).length;
    }

    @Override
    protected void doClear() {
        for (File f : getListFiles(directory)) {
            doDelete(f);
        }
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            strategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                throw new IOException("File is already exists");
            }
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
        doUpdate(r, file);
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return strategy.doRead(new BufferedInputStream(new FileInputStream(file)));
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
        List<Resume> listResumes = new ArrayList<>();
        for (File f : getListFiles(directory)) {
            try {
                listResumes.add(strategy.doRead(new BufferedInputStream(new FileInputStream(f))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return listResumes;
    }

    public File[] getListFiles(File file) {
        File[] files = file.listFiles();
        if (files == null) {
            throw new StorageException("File not found", null);
        }
        return files;
    }
}
