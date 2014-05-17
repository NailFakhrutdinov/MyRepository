package com.kpfu.itis.calculator.dependency.writer.impl;

import com.kpfu.itis.calculator.dependency.writer.ResultWriter;

public class ConsoleWriter implements ResultWriter {
    @Override
    public void write(double result) {
        System.out.println(result);
    }
}
