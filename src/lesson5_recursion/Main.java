package lesson5_recursion;

public class Main {
    //TODO
    // 1. Написать программу по возведению числа в степень с помощью рекурсии.
    // 2. * Задача о шахматном короле
    // 3. * Задача о восьми ферзях
    public static void main(String[] args) {

        System.out.println(exponentiation(2,4));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%6d", chessKing(i, j));
            }
            System.out.println();
        }
    }

    /*
    F(x, 0) = 1
    F(0, y) = 1
    F(x, y) = F(x - 1, y) + F(x, y - 1)
     */
    private static int chessKing(int i, int j) {
        if (i ==0 || j == 0)
            return 1;
        else
            return chessKing(i - 1, j) + chessKing(i, j - 1);
    }

    public static int exponentiation (int base, int exponent){
        return (exponent == 0) ? 1 : exponentiation (base, exponent - 1) * base;
    }
}
