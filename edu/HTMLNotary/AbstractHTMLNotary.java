package edu.HTMLNotary;

import java.io.FileOutputStream;
import java.io.IOException;

public interface AbstractHTMLNotary {

    public void startTable(String title) throws IOException;
    public void finishTable() throws IOException;
    public void startRow(String title) throws IOException;
    public void finishRow() throws IOException;
    public void addData(String title, String data) throws IOException;
}
