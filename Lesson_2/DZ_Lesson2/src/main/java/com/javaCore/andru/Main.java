package com.javaCore.andru;

import java.util.Random;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static final int WIN_COUNT = 3;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';
    private static  int fieldSizeX;
    private static int fieldSizeY;
    private static char[][] field;


    public static void main(String[] args) {
        /**
         * Task 1 - ok
         */

//        while (true) {
////            initialize();
////            printField();
////            while (true) {
////                humanTurn();
////                printField();
////                if (checkState(DOT_HUMAN, "Вы победили!"))
////                    break;
////                aiTurn();
////                printField();
////                if (checkState(DOT_AI, "Победил компьютер!"))
////                    break;
////            }
////            System.out.println("Хотите сыграть еще? - (Y-да) .....");
////            if (!scanner.next().equalsIgnoreCase("y"))
////                break;
////        }

        /**
         * Task 2 - ok
         */
        while (true){
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkStateTask2(DOT_HUMAN, "Вы победили!", WIN_COUNT))
                    break;
                aiTurn();
                printField();
                if (checkStateTask2(DOT_AI, "Победил компьютер!", WIN_COUNT))
                    break;
            }
        }


    }

    /**
     * Создание пустого игрового поля
     */
    static void initialize(){
        fieldSizeX = 5;
        fieldSizeY = 5 ;
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Вывод текущего состояния поля
     *
     * +-1-2-3-
     * 1|*|*|*|
     * 2|*|*|*|
     * 3|*|*|*|
     * #########
     *
     */
    static void printField(){
        System.out.print("+");
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print("-"+(i+1));

        }
        System.out.println("-");
        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print(x+1+"|");
            for (int y = 0; y < fieldSizeY; y++) {
                System.out.print(field[x][y]+"|");
            }
            System.out.println();
        }
        for (int x = 0; x < fieldSizeX*2+2; x++) {
            System.out.print("#");
        }
        System.out.println();
    }

    /**
     * Ход человека
     */
    static void humanTurn(){
        int x;
        int y;
        do {
            System.out.println("Введите коардинаты хода X Y через пробел: ....");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }while (!isCellValid(x,y) || !isCellEmpty(x,y));
        field[x][y] = DOT_HUMAN;

//        System.out.println(check3(x,y,DOT_HUMAN,WIN_COUNT));

    }

    /**
     * Ход компьютера
     */
    static void aiTurn(){
       int x;
       int y;
        do {
             x = random.nextInt(fieldSizeX);
             y = random.nextInt(fieldSizeY);
        }while (!isCellEmpty(x,y));
        field[x][y] = DOT_AI;
    }

    /**
     * Проверка хада на свобоную ячейку поля
     * @param x
     * @param y
     * @return
     */
    static boolean isCellEmpty(int x, int y){
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка хада на попадание в граници игрового поля
     * @param x
     * @param y
     * @return
     */
    static boolean isCellValid(int x, int y){
        return x >= 0 && x < fieldSizeX && y >= 0 && x < fieldSizeY;
    }


    static boolean checkDraw(){
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if(isCellEmpty(x,y)) return false;
            }
            System.out.println();
        }
        return true;
    }

    /**
     * Метод проверки победы
     * @param dot ход игрока
     * @return
     */
    static boolean checkWin(char dot){
        //Проверка по гаризонтале
        if (field[0][0] == dot && field[0][1] == dot && field[0][2] == dot) return true;
        if (field[1][0] == dot && field[1][1] == dot && field[1][2] == dot) return true;
        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;
        //Проверка по вертикале
        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
        if (field[0][1] == dot && field[1][1] == dot && field[2][1] == dot) return true;
        if (field[0][2] == dot && field[1][2] == dot && field[2][2] == dot) return true;
        //Проверка по диагонале
        if (field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) return true;
        if (field[0][2] == dot && field[1][1] == dot && field[2][0] == dot) return true;

        return false;

    }

    /**
     * Проверка условий победы по горизонтали
     * @param x коардинана точки Х
     * @param y коардинана точки У
     * @param dot вишка игрока
     * @param win исловие победы
     * @return
     */
    static boolean check1(int x, int y, char dot, int win){
        int count = 0;
        for (int i = y; i < fieldSizeX-1; i++) {
            if (field[x][i] == dot && field[x][i+1] == dot){
                count++;
            }
        }
        if (count == win-1)return true;
        return false;
    }

    /**
     * Проверка условий победы по вертикали
     * @param x коардинана точки Х
     * @param y коардинана точки У
     * @param dot вишка игрока
     * @param win исловие победы
     * @return
     */
    static boolean check2(int x, int y, char dot, int win){
        int count = 0;
        for (int i = x; i < fieldSizeY-1; i++) {
            if (field[i][y] == dot && field[i+1][y] == dot ){
               count = count +1;
            }
        }
        if (count == win-1)return true;
        return false;
    }

    /**
     * * Проверка условий победы по диоганали (прямой (а[2][1] а[3][2] а[4][3]
     * @param x коардинана точки Х
     * @param y коардинана точки У
     * @param dot вишка игрока
     * @param win исловие победы
     * @return
     */
    static boolean check3(int x, int y, char dot, int win){
        int count = 0;
        int c = 0;
        int t = 0;
        while (!(c == win)){
            if (x+t >= fieldSizeX || y+t >= fieldSizeY){
                break;
            }
            if (field[x][y]==dot && field[x+t][y+t]==dot){
                count++;
            }
            t++;
            c++;
        }
        if (count == win)return true;
        return false;
    }

    /**
     * * Проверка условий победы по диоганали (прямой (а[2][1] а[3][2] а[4][3]
     * @param x коардинана точки Х
     * @param y коардинана точки У
     * @param dot вишка игрока
     * @param win исловие победы
     * @return
     */
    static boolean check4(int x, int y, char dot, int win){
        int count = 0;
        int c = 0;
        int t = 0;
        while (!(c == win)){
            if (!(x+t >= fieldSizeX || y-t < 0)){
                if (field[x+t][y-t]==dot){
                    count++;
                }
            }
            t++;
            c++;
        }
        if (count == win)return true;
        return false;
    }


    /**
     * Проверка состояния игры
     * @param dot фишка игрока
     * @param s сообщение победы
     * @return
     */
    static boolean checkState(char dot, String s){
        if (checkWin(dot)){
            System.out.println(s);
            return true;
        } else if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    static boolean checkStateTask2(char dot, String s, int win){
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                if (check1(x,y,dot,win)){
                    System.out.println(s);
                    return true;
                }
                if (check2(x,y,dot,win)){
                    System.out.println(s);
                    return true;
                }
                if (check3(x,y,dot,win)){
                    System.out.println(s);
                    return true;
                }
                if (check4(x,y,dot,win)){
                    System.out.println(s);
                    return true;
                }
            }
        }
        return false;
    }


}
