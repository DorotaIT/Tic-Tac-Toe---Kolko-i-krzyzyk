package com.company;
import java.util.Scanner;
import java.util.Random;

public class Main {

    static int[][] board = new int[3][3];
    static String userOne;
    static String userTwo;
    static int currentUser; //jeden - userOne, dwa - userTwo
    static boolean gameRun = true;

    public static void main(String[] args) {

        imitBoard();
        randomUser();

     while(gameRun){
         renderBoard();
         getPositionFromUser();
     }

    }

    //tworzy pusty board
    public static void imitBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int cell = 0; cell < board[row].length; cell++)
                board[row][cell] = 0;
        }
    }

    //rysuje board
    public static void renderBoard(){

        for (int row = 0; row < board.length; row++) {
            for (int cell = 0; cell < board[row].length; cell++) {

                //warunki
                // 0 - pusto
                // 1 - x
                // 2 - o
//                System.out.print("[" + board[row][cell] + "]");

                if (board[row][cell] == 0) {
                    System.out.print("[ ]");
                }
                else if (board[row][cell] == 1) {
                    System.out.print("[X]");
                }
                else if (board[row][cell] == 2) {
                    System.out.print("[O]");
                }

                if (cell == (board[row].length - 1)) { //nowy wiersz
                    System.out.println(" ");
                }
            }
        }
        System.out.println(board);

    }

    //losowanie gracza
    public static void randomUser() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(2);

        if (randomInt == 1) {
            currentUser = 1;
            userOne = "X";
            userTwo = "O";
            System.out.println("Zaczyna gracz nr 1");
        }
        else {
            currentUser = 2;
            userOne = "O";
            userTwo = "X";
            System.out.println("Zaczyna gracz nr 2");
        }
    }

    //pobiera miejsce na planszy
    public static void getPositionFromUser() {
        int moveX;
        int moveY;
        Scanner readXY = new Scanner(System.in);

        printWhichUserTurn();

        System.out.println("Wprowadź zakres od 1 do 3");
        System.out.println("Podaj współrzędną X");

        moveX = readXY.nextInt();
        moveX -= 1;
        System.out.println("Podaj współrzędną Y");

        moveY = readXY.nextInt();
        moveY -= 1;

        //sprawdza, czy komórka jest wolna
        if (board[moveX][moveY] != 0){
            System.out.println("Wpisałeś zajętą komórkę. Podaj inne współrzędne.");
            getPositionFromUser();
            return;
        }


        if (currentUser == 1) {
            board[moveX][moveY] = 1;
            winCondition();
            currentUser = 2;
        }
        else if (currentUser == 2) {
            board[moveX][moveY] = 2;
            winCondition();
            currentUser = 1;
        }
    }

    //mówi, który gracz ma aktualnie ruch
    public static void printWhichUserTurn() {
        if (currentUser == 1) {
            System.out.println("Ruch gracza nr 1");
        } else if (currentUser == 2) {
            System.out.println("Ruch gracza nr 2");
        }
    }

    //warunki zwycięstwa
    public static void winCondition() {
        if      ((board[0][0] == currentUser && board[0][1] == currentUser && board[0][2] == currentUser) ||
                (board[1][0] == currentUser && board[1][1] == currentUser && board[1][2] == currentUser)  ||
                (board[2][0] == currentUser && board[2][1] == currentUser && board[2][2] == currentUser)  ||
                (board[0][0] == currentUser && board[1][1] == currentUser && board[2][2] == currentUser)  ||
                (board[2][0] == currentUser && board[1][1] == currentUser && board[0][2] == currentUser)  ||
                (board[0][0] == currentUser && board[1][0] == currentUser && board[2][0] == currentUser)  ||
                (board[0][1] == currentUser && board[1][1] == currentUser && board[2][1] == currentUser)  ||
                (board[0][2] == currentUser && board[1][2] == currentUser && board[2][2] == currentUser)) {


            System.out.println("Wygrał gracz nr " + currentUser);
            gameRun = false;
            return;
        }

    }
}
