package main.game;

import java.util.Scanner;

public class SnakeGame {

    public static void main(String[] args) {

        int boardSize = 0;
        int scale = 0;
        int wallElements = 0;
        int speed = 0;

        System.out.println("How big do you want the board to be? (small/medium/large)");
        Scanner scanner = new Scanner(System.in);
        String input;

        do{
            input = scanner.next();

            switch (input) {
                case "s":
                case "small":
                    boardSize = 10;
                    scale = 30;
                    break;
                case "m":
                case "medium":
                    boardSize = 20;
                    scale = 20;
                    break;
                case "l":
                case "large":
                    boardSize = 40;
                    scale = 10;
                    break;
                default:
                    System.out.println("Could not understand input.");
            }
        }while(boardSize == 0);

        System.out.println("Set the game difficulty (low/medium/high)");

        do{
            input = scanner.next();

            switch (input) {
                case "l":
                case "low":
                    wallElements = 0;
                    speed = 7;
                    break;
                case "m":
                case "medium":
                    wallElements = boardSize*boardSize/32;
                    speed = 5;
                    break;
                case "h":
                case "high":
                    wallElements = boardSize*boardSize/16;
                    speed = 3;
                    break;
                default:
                    System.out.println("Could not understand input.");
            }
        }while(speed == 0);

        Board board = new Board(boardSize,boardSize,scale,wallElements, speed);

        System.out.println("Level setup. Press space to start.");

        board.run();
    }

}