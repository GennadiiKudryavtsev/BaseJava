package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ContexStrategy {
    AbstractFileStorage abstractFileStorage;

    public ContexStrategy(AbstractFileStorage abstractFileStorage) {
        this.abstractFileStorage = abstractFileStorage;
    }

     public void doWrite(Resume r, OutputStream os) {
        try {
            abstractFileStorage.doWrite(r, os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

     public Resume doRead(InputStream is) {

        try {
            return abstractFileStorage.doRead(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
