package com.lavieennoir.qa.lab2.models;

public class RandomParameters {
    private int seed;
    private int count;
    private int skip;

    public RandomParameters() {
        this.seed = 0;
        this.count = 1;
        this.skip = 0;
    }
    public RandomParameters(int seed, int count, int skip) {
        this.seed = seed;
        this.count = count;
        this.skip = skip;
    }

    public int getSeed() {return seed;}
    public int getCount() {return count;}
    public int getSkip() {return skip;}
    public void setSeed(int value) {seed = value;}
    public void setCount(int value) {count = value;}
    public void setSkip(int value) {skip = value;}
}
