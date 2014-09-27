package test;

import main.Foo;
import main.ReadManager;
import main.WriteManager;
import junit.framework.TestCase;

public class TestWriteManager extends TestCase {
    private WriteManager writer;
    private ReadManager reader;
    private String fileName1 = "test1.txt";
    private String fileName2 = "test2.txt";
    private String fileName3 = "test3.txt";
    private String fileName4 = "test4.csv";

    public TestWriteManager(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        writer = new WriteManager(fileName1);
        reader = new ReadManager(fileName1);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testWriteDouble() {
        double value = 2.5;
        writer.setFileName(fileName1);
        writer.writeDouble(value);
        reader.setFileName(fileName1);
        assertEquals(value, reader.readDouble(), 0.0000001);
    }

    public void testWriteToPosition() {
        int data1 = 2014;
        int data2 = 1500;
        writer.setFileName(fileName2);
        writer.writeToPosition(data1, 4);
        writer.writeToPosition(data2, 0);
        reader.setFileName(fileName2);
        assertEquals(data1, reader.readFromPosition(4));
        assertEquals(data2, reader.readFromPosition(0));
    }

    public void testWriteObject() {
        Foo foo = new Foo(1, "John");
        writer.setFileName(fileName3);
        writer.writeObject(foo);
        reader.setFileName(fileName3);
        Foo read = reader.readObject();
        assertEquals(foo.getId(), read.getId());
        assertEquals(foo.getName(), read.getName());
    }

    public void testWriteCSVReport() {
        Foo[] arr = new Foo[3];
        arr[0] = new Foo(1, "John");
        arr[1] = new Foo(2, "Adam");
        arr[2] = new Foo(3, "Jane");
        writer.setFileName(fileName4);
        writer.writeCSVReport(arr);
        reader.setFileName(fileName4);
        Foo[] read = reader.readCSVReport();
        assertEquals(arr[0].getId(), read[0].getId());
        assertEquals(arr[2].getName(), read[2].getName());
        assertEquals(arr[1].getId(), read[1].getId());
    }
}
