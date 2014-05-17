package com.kpfu.itis.calculator.dependency.writer.impl;

import com.kpfu.itis.calculator.dependency.writer.ResultWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriter implements ResultWriter{
    @Override
    public void write(double result){
        try {
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            pw.println(result);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
