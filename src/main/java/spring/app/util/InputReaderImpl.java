package spring.app.util;


import spring.app.util.contract.InputReader;

import java.io.BufferedReader;
import java.io.IOException;



public class InputReaderImpl implements InputReader {

    private final BufferedReader reader;

    public InputReaderImpl(BufferedReader reader) {
        this.reader = reader;
    }

    public String read() {
        String line = null;
        try {
            line = this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }


}
