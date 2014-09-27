package main;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import au.com.bytecode.opencsv.CSVWriter;

// use multiple ways to write to file

public class WriteManager {

    private String fileName;

    public WriteManager(String file) {
        fileName = file;
    }

    // use DataOutputStream to write primitive data types
    public void writeDouble(double value) {
        DataOutputStream stream;
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            stream = new DataOutputStream(new BufferedOutputStream(fos));
            stream.writeDouble(value);
            stream.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    // use RandomAccessFile to write data at specific position in the file
    public void writeToPosition(int data, long position) {
        try {
            RandomAccessFile writer = new RandomAccessFile(fileName, "rw");
            writer.seek(position);
            writer.writeInt(data);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    // use ObjectOutputStream to write object
    public void writeObject(Foo foo) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            ObjectOutputStream writer = new ObjectOutputStream(fos);
            writer.writeObject(foo);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    // use CSVWriter to write CSV data
    public void writeCSVReport(Foo[] array) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(fileName, true), ',');
            int len = array.length;
            for (int i = 0; i < len; i++) {
                writer.writeNext(array[i].toStringArray());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
