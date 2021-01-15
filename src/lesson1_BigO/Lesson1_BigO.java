package lesson1_BigO;

import java.util.Arrays;

public class Lesson1_BigO {
    public static void main(String[] args) {

//        TODO:
//        1. Описать простейшие алгоритмы
//        1.1. Возведение в степень *используя чётность степени*
        System.out.println("Exponentiation:  " + еxponentiation(2, 3));

//        1.2. Поиск минимального элемента в массиве
        int[] a = new int[]{2, 7, 3, 1, 4, 4};
        System.out.println("Arrays: " + Arrays.toString(a));
        arrMin(a);
        System.out.println("Minimum array value " + arrMin(a));

//        1.3. Нахождение среднего арифметического массива
        arrAver(a);
        System.out.println("Average of the array " + arrAver(a));
//        2. Подсчитать сложность описанных алгоритмов
//        3. Какие правила подсчёта применялись, оставьте комментарии в коде
    }

    // Сложность O(n)
    private static int еxponentiation(int base, int exponent){
        for (int i = 1; i < exponent; i++) {
            base *= base;
        }
        return base;
    }

    // Сложность O(n)
    // По замыслу хотел сравнить первый элемент массива с остальными, но не успел к началу занятия
    // Поулчилось, что бегу по массиву 2 раза
    private static int arrMin(int[] arr){
        int min = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (arr[i] < arr[j]) {
                    min = arr[i];
                }
            }
        }
        return min;
    }

    // Сложность O(n)
    private static float arrAver (int[] arr){
        float av = 0;
        for (int i = 0; i < arr.length; i++) {
            av += arr[i];
        }
        return av / arr.length;
    }
}
