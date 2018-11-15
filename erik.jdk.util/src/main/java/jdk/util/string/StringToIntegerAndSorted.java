package jdk.util.string;

import java.util.HashSet;
import java.util.Set;

public class StringToIntegerAndSorted {

    public static void main(String[] args) {

        String albumString1="16252742,16547362,14665363,16616539,16644208,16644593,16613292,16387389,16375411,16575340,14496021,16613209,16487369,15021958,16104746,16604065,15873196,11903420,16519254,16603682,16603026,16366261,16617358,16628809,16163951";
        String albumString2="14496021,14665363,14665363,15873196,16104746,16163951,16252742,16366261,16375411,16387389,16487369,16519254,16547362,16575340,16603026,16603682,16604065,16613209,16613292,16616539,16617358,16628809,16644208,16644593";

        Set<String> spuIdStringSet1 = new HashSet<>();
        Set<String> spuIdStringSet2 = new HashSet<>();

        String[] spuIdStringArray1 = albumString1.split(",");
        String[] spuIdStringArray2 = albumString2.split(",");

        for (String s : spuIdStringArray1) {
            spuIdStringSet1.add(s);
        }

        for (String s : spuIdStringArray2) {
            spuIdStringSet2.add(s);
        }

        spuIdStringSet1.removeAll(spuIdStringSet2);

        System.out.println(spuIdStringSet1);

    }

}
