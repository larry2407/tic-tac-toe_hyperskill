package tictactoe;

import java.util.Random;

public class Main {
    private static String[][] grid = new String[3][3];
    private static Random rand = new Random();
    public static void main(String[] args) {
        generateRandomGame();
        showGrid();
    }

    private static void generateRandomGame(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                grid[i][j] = rand.nextDouble() > .5 ? "X" : "O";
            }
        }
    }

    private static void showGrid(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }
}
