package main;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.List;
import au.com.bytecode.opencsv.CSVReader;

// used to read file in multiple ways to test WriteManager

public class ReadManager {
    private String fileName;

    public ReadManager(String file) {
        fileName = file;
    }

    // use DataInputStream
    public double readDouble() {
        DataInputStream stream;
        double result = 0;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            stream = new DataInputStream(new BufferedInputStream(fis));
            result = stream.readDouble();
            stream.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return result;
    }

    // use RandomAccessFile to read data from specific position in the file
    public int readFromPosition(long position) {
        int result = 0;
        try {
            RandomAccessFile reader = new RandomAccessFile(fileName, "r");
            reader.seek(position);
            result = reader.readInt();
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return result;
    }

    // use ObjectInputStream to read object
    public Foo readObject() {
        FileInputStream fis;
        Foo result = null;
        try {
            fis = new FileInputStream(fileName);
            ObjectInputStream reader = new ObjectInputStream(fis);
            result = (Foo) reader.readObject();
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return result;
    }

    // use CSVReader to read CSV data
    public Foo[] readCSVReport() {
        CSVReader reader;
        Foo[] result = null;
        try {
            reader = new CSVReader(new FileReader(fileName));
            List list = reader.readAll();
            int size = list.size();
            result = new Foo[size];
            Foo temp;
            String[] currentItem;
            for (int i = 0; i < size; i++) {
                temp = new Foo();
                currentItem = (String[]) list.get(i);
                temp.setId(Long.parseLong(currentItem[0]));
                temp.setName(currentItem[1]);
                result[i] = temp;
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return result;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
