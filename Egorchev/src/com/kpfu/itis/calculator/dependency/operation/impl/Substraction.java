package com.kpfu.itis.calculator.dependency.operation.impl;

import com.kpfu.itis.calculator.dependency.operation.Operation;

public class Substraction implements Operation{
    @Override
    public Double calculate(double firstOperand, double secondOperand) {
        return firstOperand - secondOperand;
    }
}
