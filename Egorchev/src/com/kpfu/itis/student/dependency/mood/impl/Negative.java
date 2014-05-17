package com.kpfu.itis.student.dependency.mood.impl;

import com.kpfu.itis.student.dependency.mood.Mood;

public class Negative implements Mood {

    @Override
    public void showMood() {
        System.out.println("=(");
    }
}
