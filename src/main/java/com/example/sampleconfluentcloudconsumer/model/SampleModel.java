package com.example.sampleconfluentcloudconsumer.model;

public class SampleModel{

    int a;
    String b;
    int c;

    public SampleModel() {
    }

    public SampleModel(int a, String b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "SampleModel{" +
                "a : " + a +
                ", b : '" + b + '\'' +
                ", c : " + c +
                '}';
    }
}