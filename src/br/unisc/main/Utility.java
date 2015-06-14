package br.unisc.main;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class Utility {
    
    public static String humanReadableByteCount(long bytes) {
        int unit = 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        char pre = "KMGTPE".charAt(exp - 1);
        double r = bytes / Math.pow(unit, exp);
        return String.format("%s %sB", doubleToString(r), pre);
    }
    
    public static String doubleToString(double n) {
        NumberFormat nf = new DecimalFormat("##.###");
        return nf.format(n);
    }
    
    public static double log2(double n) {
        return Math.log10(n) / Math.log10(2);
    }
    
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
}
