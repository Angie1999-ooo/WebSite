package com.shop.cosm.library;

import java.io.IOException;

public interface ISettingRecognize {

    void train() throws IOException;

    String recognize(byte[] bytes,double valueConfidence) throws IOException;
}
