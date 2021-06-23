package com.shop.cosm.library;

import java.io.IOException;

public interface IRecognizeRepo {

    String add(byte[] bytes,String username,String  extension) throws IOException;

    void delete(String filename);

    String[] readAll(String trainingDir);
//ByteArrayInputStream read() throws IOException;

    byte[] read(String filename) throws IOException;

}