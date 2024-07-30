package com.urise.webapp.storage;

import com.urise.webapp.exceptions.StorageException;
import com.urise.webapp.model.Resume;
import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage {

        public ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
     public void doWrite(Resume r, OutputStream os) throws IOException {
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(r);
            oos.close();
    }

    @Override
     public Resume doRead(InputStream is){
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
