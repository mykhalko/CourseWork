package edu.HTMLNotary;

import java.io.FileWriter;
import java.io.IOException;

public class HTMLNotary implements AbstractHTMLNotary{

    FileWriter out;
    String path;

    public HTMLNotary(String path) throws IOException{
        this.path = path;
    }

    public HTMLNotary(){
        out = null;
    };

    public void setPath(String path) throws IOException{
        if(path.endsWith("/")){
            this.path = path;
        }
        else{
            this.path = path + "/";
        }
    }

    public void start(String title) throws IOException{
        if(out != null){
            out.close();
        }
        out = new FileWriter(path + title + ".html");
        out.write("<!DOCTYPE html>\n<html>\n\t<head>\n\t\t<meta charset='UTF-8'>");
        out.write("\n\t\t<title>" + title + "</title>");
        out.write("\n\t\t<link rel='stylesheet' href='example.css'>");
        out.write("\n\t\t<script type='text/javascript' src='example.js'></script>\n\t</head>");
        out.write("\n\t<body>");
    }

    public void finish() throws IOException{
        out.write("\n\t</body>\n</html>");
        out.close();
    };

    @Override
    public void startTable(String title) throws IOException {
        out.write("\n\t\t<table class='" + title + "'>");
    }

    @Override
    public void finishTable() throws IOException {
        out.write("\n\t\t</table>");
    }

    @Override
    public void startRow(String title) throws IOException {
        out.write("\n\t\t\t<tr class='" + title + "'>");
    }

    @Override
    public void finishRow() throws IOException {
        out.write("\n\t\t\t</tr>");
    }

    @Override
    public void addData(String title, String data) throws IOException {
        out.write("\n\t\t\t\t<td class='" + title + "'>" + data + "</td>");
    }

    public void addData(String title, int[] data) throws IOException{
        int length = data.length;
        for(int i = 0; i < length; i++){
            this.addData(title, String.valueOf(data[i]));
        }
    }

    public void addData(String title, double[] data) throws IOException{
        int length = data.length;
        for(int i = 0; i < length; i++){
            this.addData(title, String.valueOf(data[i]));
        }
    }

    public <T extends Number>void addData(String title, T[] data) throws IOException{
        int length = data.length;
        for(int i = 0; i < length; i++){
            this.addData(title, data[i].toString());
        }
    }

    public <T extends Number>void auto(T[][] data, String ... titles) throws IOException{
        int rowsAmount = data.length;
        int valuesAmount = data[0].length;

        if(titles.length < 2*rowsAmount + 1) {
            throw new IOException("Not enough titles!");
        }
        if(titles.length > 2*rowsAmount + 1){
            throw new IOException("Too many titles!");
        }

        for(int i = 1; i < rowsAmount; i++){
            if(valuesAmount != data[i].length){
                throw new IOException("Different sizes!");
            }
        }

        this.startTable(titles[0]);
        for(int i = 0; i < rowsAmount; i++){
            this.startRow(titles[2*i + 1]);
            for(int j = 0; j < valuesAmount; j++){
                this.addData(titles[2*i + 2], data[i][j].toString());
            }
            this.finishRow();
        }
        this.finishTable();
    }

    public static <T extends Number> T[][] glue(T[] ... data){
        return data;
    }

    public static Integer[] parseObject(int[] array){
        Integer[] parsed = new Integer[array.length];
        for(int i = 0; i < array.length; i++){
            parsed[i] = array[i];
        }
        return parsed;
    }

    public static Double[] parseObject(double[] array){
        Double[] parsed = new Double[array.length];
        for(int i = 0; i < array.length; i++){
            parsed[i] = array[i];
        }
        return parsed;
    }
}