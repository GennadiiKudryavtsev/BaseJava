package com.urise.webapp;

import java.io.File;
import java.util.Arrays;

public class MainFile {

    public static void main(String[] args)  {
        MainFile mainFile = new MainFile();
        File file = new File("/Users/gennadykudryavtsev/Desktop/MyCode/MyCourseBJ/basejava");
        mainFile.printFiles(file);
    }
    StringBuilder space = new StringBuilder("");
    public void printFiles(File file) {

        File[] lst = file.listFiles();
        if (lst == null) {
            return;
        }
        Arrays.sort(lst);
        for (File value : lst) {
            if (value.isDirectory()) {
                if (value.getName().equals(".git")) {
                    continue;
                }

                System.out.println(space + value.getName());
                space.append("   ");
                printFiles(value);
            }
        }
        for (File value : lst) {
            if (!value.isDirectory()) {
                System.out.println(space + "   " + value.getName());

            }
        }
        space.replace(0, space.length(), "");
    }
}