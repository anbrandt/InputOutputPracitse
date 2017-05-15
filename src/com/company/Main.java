package com.company;

import mytools.RecursiveFolderEnumerator;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


/*
    Windows use back-slash '\' as the directory separator; while Unixes/Mac use forward-slash '/'.
    Windows use semi-colon ';' as path separator to separate a list of paths; while Unixes/Mac use colon ':'.
    Windows use "\r\n" as line delimiter for text file; while Unixes use "\n" and Mac uses "\r".
    The "c:\" or "\" is called the root. Windows supports multiple roots, each maps to a drive (e.g., "c:\", "d:\").
    Unixes/Mac has a single root ("\").
    A path could be absolute (beginning from the root) or relative (which is relative to a reference directory).
    Special notations "." and ".." denote the current directory and the parent directory, respectively.
*/
//c:\\home\\adrian\\Development\\IOexamples\\src\\somefile

        System.out.println(File.separator);

        //Example1 - referring to a file and FILE class
        System.out.println(" --- Example 1 --- ");

        //String fname1 = "/home/adrian/Development/IOexamples/src/somefile";
        String fname1 = "./src/somefile";
        File f1 = new File(fname1);
        //popular method!
        System.out.println("file exists? : " + f1.exists());
//coś tam
        //Example 2 - creating new file

        System.out.println(" --- Example 2 --- ");

        String fname2 = "./src/somefile3";
        File f2 = new File(fname2);
        try {
            f2.createNewFile();
            System.out.println(f2.exists());

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Example 3 - deleting a file
        System.out.println(" --- Example 3 --- ");


        try {
            Thread.sleep(1000); // make it longer and refresh tree to see result
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        f2.delete();

        //Example 4 - test files
        System.out.println(" --- Example 4 --- ");

        System.out.println(String.format("is %s directory? - %b ", fname1, f1.isDirectory()));

        try {
            System.out.println(String.format("file path of %s - %s ", fname1, f1.getCanonicalPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //count the bytes in file to check!
        System.out.println(String.format("space occupied by %s ? - %d ", fname1, f1.length()));

        //Example 5
        System.out.println(" --- Example 5 --- ");

        //enumerating content of a directory - non-recursively
        String fname3 = "./src";
        File f3 = new File(fname3);
        System.out.println(String.format("is %s directory? - %b ", fname3, f3.isDirectory()));

        if (f3.isDirectory()) {
            String myFiles[] = f3.list();  //new String[f3.list().length];
            for (int i = 0; i < myFiles.length; i++) {
                String myFile = myFiles[i];
                System.out.println("file found: " + myFile);
            }
        }

        //Example 6
        System.out.println(" --- Example 6 --- ");

        //Recursive enumeration - recursion with side-effect (printing)
        RecursiveFolderEnumerator rfe = new RecursiveFolderEnumerator();
        rfe.listRecursive(f3);

        System.out.println(" ---- ");

        //Example 7
        System.out.println(" --- Example 7 --- ");
        //Recursion without side effects - how to pass parameter in recursion?
        List<String> fileList = new ArrayList<>();
        rfe.listRecursiveFunction(f3, fileList);
        for (String s : fileList) {
            System.out.println(s);
        }

        //Example 8 - writing to file - using implementations of OutputStream
        System.out.println(" --- Example 8 --- ");

        File f4 = new File("./src/gugu");
        try {
            OutputStream os1 = new FileOutputStream(f4);
            try {

                os1.write(88);
                os1.write(57);
                os1.write(107);
                byte mybytes[] = {98, 117, 98, 117};
                os1.write(mybytes);
                os1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Example 9 - writing string to file using PrintStream class

        System.out.println(" ---Example 9--- ");

        File f5 = new File("./src/gugustrings");

        PrintStream printStream1 = null;
        try {
            printStream1 = new PrintStream(f5);
            printStream1.println(" bubu gaga gagaga");
            printStream1.println(" another test");
            printStream1.println(" 3rd line");


            printStream1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Example 10- writing using buffered stream to output stream to other output stream.
        //the buffering is done automaticaly so IO operations are not that frequent.
        System.out.println(" --- Example 10 --- ");


        File f6 = new File("./src/gugustringsbuffered");
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(f6);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream2);

            try {
                bufferedOutputStream.write(103);

                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Example 11
        System.out.println(" --- Example 11 --- ");
        //example of using StringWriter class to ... just write to it.
        //Useful when, for example reading the files (we want to write to something, right?);

        StringWriter sw = new StringWriter();

        sw.write(" yet another string!");
        sw.write(12);
        System.out.println(sw.toString());


        //Example 12 - reading from a file - raw bytes and converting them to characters.
        System.out.println(" --- Example 12 --- ");
        File f7 = new File("./src/gugustrings");
        try {
            try (FileInputStream fileInputStream1 = new FileInputStream(f7)) {

                char myCharacter = 's';
                byte myNumber = 2;

                while (fileInputStream1.available() > 0) {
                    System.out.println((char)fileInputStream1.read()); //experiment - remove typecasting!
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




        //Example 13
        //What is System.out anyway ? Reveal its type!
        //look how to connect input stream to output stream.
        System.out.println(" --- Example 13 --- ");


        try {
           // System.err.
            System.out.println(" you have entered:" + (char) System.in.read());
            while (System.in.available() > 0) {
                System.out.println(" you have entered:" + (char) System.in.read());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Example 14
        // is it possible to write something else than bytes, or strings to file?

        System.out.println(" ----- Example 14 ------ ");
        DataStore myStore = new DataStore("John", 38);
        File myData = new File("./src/database");
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(myData);
            ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream2);
            objectOutputStream1.writeObject(myStore);
            objectOutputStream1.flush();
            objectOutputStream1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(" ----- Example 15 ------ ");
        //Of course we can actually read objects from files as well!!!

        File myDataR = new File("./src/database");
        try {
            FileInputStream fileInputStream2 = new FileInputStream(myDataR);
            ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream2);
            try {
                DataStore myStoreR = (DataStore) objectInputStream1.readObject();
                System.out.println("  hello , I'm resurrected !!! \n");
                System.out.println(myStoreR.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" ----- Example 16 ------ ");
        //Notes on Files and Paths. A number of useful file manipulation routines
        //are in there...
        //eg. how to create a new folder?

        Path myPath = Paths.get("./src/delme");
        System.out.println(myPath.toString());
        try {
            Files.createDirectory(myPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" ----- Example 17 ------ ");
        //recursive walking but shorter...!

        Path myFolderPath = Paths.get("./src");
        try {
            Files.walkFileTree(myFolderPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println(" file found is: " + file.getFileName().toString());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
