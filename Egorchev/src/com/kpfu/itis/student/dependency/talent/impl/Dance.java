package com.kpfu.itis.student.dependency.talent.impl;

import com.kpfu.itis.student.dependency.talent.Talent;

public class Dance implements Talent {
    @Override
    public void showTalent() {
        System.out.println("dance");
    }
}
