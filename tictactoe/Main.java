package tictactoe;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static int countO;
    static int countX;
    static int count_;

    final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Map<String, Integer> positionIndex = new HashMap<>();
        positionIndex.put("11", 6);
        positionIndex.put("12", 3);
        positionIndex.put("13", 0);
        positionIndex.put("21", 7);
        positionIndex.put("22", 4);
        positionIndex.put("23", 1);
        positionIndex.put("31", 8);
        positionIndex.put("32", 5);
        positionIndex.put("33", 2);

        //begin game
        String pattern = "_________";
        generatePlayground(pattern);
        int countPlayer = 0;
        boolean endGame = false;
        while (!endGame) {
            String position = getCoordinate();
            char player = countPlayer % 2 == 0 ? 'X' : 'O';
            while (true) {
                if (pattern.charAt(positionIndex.get(position)) == '_') {
                    pattern = pattern.substring(0, positionIndex.get(position)) + player + pattern.substring(positionIndex.get(position) + 1);
                    generatePlayground(pattern);
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
                position = getCoordinate();
            }
            String state = getState(pattern);
            if (state != "Game not finished") {
                endGame = true;
            }
            System.out.println(state);
            countPlayer++;
        }
    }

    static String getCoordinate() {

        while(true) {
            System.out.println("Enter the coordinates:");
            String position = sc.nextLine();
            try {
                Integer.parseInt(position.replaceAll("\\s",""));
                int x = Integer.parseInt(position.split(" ")[0]);
                int y = Integer.parseInt(position.split(" ")[1]);
                if (x > 3 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    return position.replaceAll("\\s", "");
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    static void generatePlayground(String pattern) {
        System.out.println("---------");
        for (int i = 1; i <= 9; i++) {
            if (i % 3 == 1) {
                System.out.print("|");
            }
            System.out.print(" " + pattern.charAt(i - 1));
            if (i % 3 == 0) {
                System.out.print(" |");
                System.out.println();
            }

            if (pattern.charAt(i - 1) == 'O') {
                countO++;
            }
            if (pattern.charAt(i - 1) == 'X') {
                countX++;
            }
            if (pattern.charAt(i - 1) == '_') {
                count_++;
            }
        }
        System.out.println("---------");
    }

    static String getState(String pattern) {
        String state = "";
        if (Math.abs(countO - countX) > 1) {
            state = "Impossible";
        } else if (isCharWinner('X', pattern) && isCharWinner('O', pattern)) {
            state = "Impossible";
        } else if (isCharWinner('X', pattern)) {
            state = "X wins";
        } else if (isCharWinner('O', pattern)) {
            state = "O wins";
        } else if (count_ > 0) {
            state = "Game not finished";
        } else {
            state = "Draw";
        }
        count_ = 0;
        countX = 0;
        countO = 0;
        return state;
    }

    static boolean isCharWinner(char c, String pattern) {
        if (pattern.charAt(0) == c && pattern.charAt(1) == c && pattern.charAt(2) == c ||
                pattern.charAt(3) == c && pattern.charAt(4) == c && pattern.charAt(5) == c ||
                pattern.charAt(6) == c && pattern.charAt(7) == c && pattern.charAt(8) == c ||
                pattern.charAt(0) == c && pattern.charAt(3) == c && pattern.charAt(6) == c ||
                pattern.charAt(1) == c && pattern.charAt(4) == c && pattern.charAt(7) == c ||
                pattern.charAt(2) == c && pattern.charAt(5) == c && pattern.charAt(8) == c ||
                pattern.charAt(0) == c && pattern.charAt(4) == c && pattern.charAt(8) == c ||
                pattern.charAt(2) == c && pattern.charAt(4) == c && pattern.charAt(6) == c
        ) {
            return true;
        }
        return false;
    }
}
