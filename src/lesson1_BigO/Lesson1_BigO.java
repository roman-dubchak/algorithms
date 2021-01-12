package lesson1_BigO;

import java.sql.Array;
import java.util.Arrays;

public class Lesson1_BigO {
    public static void main(String[] args) {

//        TODO:
//        1. Описать простейшие алгоритмы

//        1.1. Возведение в степень *используя чётность степени*
        int[] a = new int[]{2, 7, 3, 1, 4, 4};
        System.out.println("Before Exponentiation " + Arrays.toString(a));
        arrExponentiation(a);
        System.out.println("Before Exponentiation " + Arrays.toString(a));

//        1.2. Поиск минимального элемента в массиве
        arrMin(a);
        System.out.println("Minimum array value " + arrMin(a));

//        1.3. Нахождение среднего арифметического массива
        arrAver(a);
        System.out.println("Average of the array " + arrAver(a));
//        2. Подсчитать сложность описанных алгоритмов
//        3. Какие правила подсчёта применялись, оставьте комментарии в коде
    }
    public static void arrExponentiation(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= arr[i];
        }
    }

    public static int arrMin(int[] arr){
        int min = 0;
        int halfArr = arr.length / 2;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (arr[i] < arr[j]) {
                    min = arr[i];
                }
            }
        }
        return min;
    }

    public static float arrAver (int[] arr){
        int av = 0;
        for (int i = 0; i < arr.length; i++) {
            av += arr[i];
        }
        return (float) av / arr.length;
    }

}
