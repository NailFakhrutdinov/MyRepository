package com.kpfu.itis.calculator;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCalculator {
    public static void main(String[] args) {
        ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
        Calculator calculator = (Calculator)ap.getBean("calculator");
        double result = calculator.calculate(6,4);
        System.out.println(result);
        calculator.result(result);
    }
}
