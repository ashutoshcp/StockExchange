package service;

import java.io.BufferedReader;
import java.io.IOException;

public interface StockProcessor {
    void process(BufferedReader br) throws IOException;
}
