package tech.reliab.course.polyvyanyLab.utils;

public class Random {
    public static int getRandomNumber(int max) {
        return (int)(Math.random() * max + 1);
    }
}
