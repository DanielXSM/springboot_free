package com.free.zdp.test;

import sun.applet.Main;

import java.math.BigDecimal;

public class testClass {
    public static void main(String[] args) {
        String amount="-0.000011";
        BigDecimal earn_bigDecimal=new BigDecimal(amount);
        BigDecimal earn_scale = earn_bigDecimal.setScale(4, BigDecimal.ROUND_DOWN).stripTrailingZeros();//出去多余的0
        String plainString = earn_scale.toPlainString();
        System.out.println(plainString);
    }
}
