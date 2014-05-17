package com.kpfu.itis.calculator;

import com.kpfu.itis.calculator.dependency.operation.Operation;
import com.kpfu.itis.calculator.dependency.writer.ResultWriter;

public class Calculator {
    Operation operation;
    ResultWriter writer;

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setWriter(ResultWriter writer) {
        this.writer = writer;
    }

    public Operation getOperation() {

        return operation;
    }

    public ResultWriter getWriter() {
        return writer;
    }

    public double calculate(double first,double second) {
        return operation.calculate(first,second);
    }

    public void result(double result) {
        writer.write(result);
    }
}
