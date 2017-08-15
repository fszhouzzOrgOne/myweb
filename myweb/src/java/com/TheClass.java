package com;

public class TheClass {
    public static void main(String[] args) {
        amethod();
        
        testFlayweight();
        
    }
    
    private static void testFlayweight() {
        String str1 = "和諧";
        String str2 = "社會";
        String str3 = "和諧社會";
        String str4;
        str4  = str1 + str2;
        System.out.println(str3 == str4);
        str4 = (str1 + str2).intern();
        System.out.println(str3 == str4);
    }

    public static void amethod() {
        Long a = 1L;
        Long b = 2L;
        if (a > b) {
            System.out.println("Long a > b");
        } else {
            System.out.println("no >.");
        }
    }
}
