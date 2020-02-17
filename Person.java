package com.example.popGame;
//Data for the object Person
public class Person {
    private String name;
    private String sdate;
    private String score;

    public Person(String name, String sdate, String score) {
        this.name = name;
        this.sdate = sdate;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
