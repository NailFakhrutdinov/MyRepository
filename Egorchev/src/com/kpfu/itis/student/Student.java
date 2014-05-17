package com.kpfu.itis.student;

import com.kpfu.itis.student.dependency.mood.Mood;
import com.kpfu.itis.student.dependency.talent.Talent;

public class Student {

    Mood mood;
    Talent talent;

     public void showTalent() {
        talent.showTalent();
     }
     public void showMood() {
         mood.showMood();
     }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public Talent getTalent() {
        return talent;
    }

    public void setTalent(Talent talent) {
        this.talent = talent;
    }
}
