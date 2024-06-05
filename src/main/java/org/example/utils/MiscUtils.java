package org.example.utils;

import java.awt.*;

public class MiscUtils {
    private static int average(int a, int b){
        return (a+b)/2;
    }
    public static Color colorAverage(Color a, Color b){
        int red = average(a.getRed(),b.getRed());
        int green = average(a.getGreen(),b.getGreen());
        int blue = average(a.getBlue(),b.getBlue());
        return new Color(red,green,blue);
    }
}
