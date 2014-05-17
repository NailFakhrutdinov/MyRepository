package com.kpfu.itis.student;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStudent {
    public static void main(String[] args) {
        ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
        Student st = (Student)ap.getBean("student");
        st.showMood();
        st.showTalent();
    }
}
