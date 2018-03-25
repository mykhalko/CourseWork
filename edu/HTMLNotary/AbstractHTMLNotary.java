package edu.HTMLNotary;

import java.io.FileOutputStream;
import java.io.IOException;

public interface AbstractHTMLNotary {

    void startTable(String title) throws IOException;
    void finishTable() throws IOException;
    void startRow(String title) throws IOException;
    void finishRow() throws IOException;
    void addData(String title, String data) throws IOException;
}
