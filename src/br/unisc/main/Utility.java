/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unisc.main;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

/**
 *
 * @author guilhermesehn
 */
public class Utility {
    
    public static String humanReadableByteCount(long bytes) {
        int unit = 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        char pre = "KMGTPE".charAt(exp - 1);
        double r = bytes / Math.pow(unit, exp);
        NumberFormat nf = new DecimalFormat("##.###");
        String n = nf.format(r);
        return String.format("%s %sB", n, pre);
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
