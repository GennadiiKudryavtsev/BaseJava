package com.urise.webapp.storage;

import com.urise.webapp.exceptions.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.strategy.Strategy;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final Strategy strategy;

    public PathStorage(String dir, Strategy strategy) {
        this.strategy = strategy;
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected int doSize() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            strategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
        doUpdate(r, path);
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
            return strategy.doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExisting(Path path) {
        return Files.exists(path);
    }

    @Override
    public List<Resume> doCopyAll() {
        try (Stream<Path> pathStream = Files.list(directory)) {
            return pathStream
                    .map(this::doGet)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Path read error", e);
        }
    }
}
