package com.example.summer.logic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MySort {
    public static int [] argsort(final double[] a,final boolean ascending){
        Integer[] indexes = new Integer[a.length];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, new Comparator<Integer>() {
            public int compare(final Integer i1, final Integer i2) {
                return (ascending ? 1 : -1) * Double.compare(a[i1], a[i2]);
            }
        });
        int[] ret = new int[indexes.length];
        for (int i = 0; i < ret.length; i++)
            ret[i] = indexes[i];

        return ret;
    }

    public static int [] argsort(final List<Double> list, final boolean ascending){
        Integer[] indexes = new Integer[list.size()];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }

        Arrays.sort(indexes, new Comparator<Integer>() {
            public int compare(final Integer i1, final Integer i2) {
                return (ascending ? 1 : -1) * Double.compare(list.get(i1), list.get(i2));
            }
        });
        return asArray(indexes);
    }

    public static <T extends Number> int[] asArray(final T... a) {
        int[] b = new int[a.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = a[i].intValue();
        }
        return b;
    }
}
