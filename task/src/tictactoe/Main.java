package tictactoe;
import java.util.Scanner;

public class Main {
    //private static Scanner sc = new Scanner(System.in);
    private static String[][] grid = new String[3][3];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        fillGrid();
        showGrid();
        //System.out.println(analyzeGame());
        String result;
        int turn = 0;
        do {
            getInput(sc, turn);
            turn++;
            showGrid();
            result = analyzeGame();
        }while(result.equals("Game not finished"));
        System.out.println(result);
    }

        private static void fillGrid(){
            //System.out.print("Enter cells: ");
            String inputLine = "_________";//sc.nextLine();
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    int beg = 3*i + j;
                    grid[i][j] = beg<inputLine.length()? inputLine.substring(beg, beg+1).replace('_', ' ') : " ";
                }
            }
    }

    private static void getInput(Scanner sc, int turn) {
        boolean isCorrect = false;
        boolean areInts = true;
        while (!isCorrect) {
            System.out.print("Enter the coordinates: ");
            int x = 0;
            int y = 0;
            try {
                x = sc.nextInt();
                y = sc.nextInt();
            } catch (Exception e) {
                areInts = false;
                // the private static Scanner sc (see line 5) does not work here for whatever reason
            }
            isCorrect = areInts && x >= 1 && x <= 3 && y >= 1 && y <= 3 && grid[3-y][x-1].equals(" ");
            if (isCorrect) {
                grid[3-y][x-1] = turn%2 == 0 ? "X" : "O";
            }else if (areInts && !(x >= 1 && x <= 3 && y >= 1 && y <= 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
            }else if(areInts){
                System.out.println("This cell is occupied! Choose another one!");
            }else{
                System.out.println("You should enter numbers!");
            }
        }
    }

    private static void showGrid(){
        System.out.println("---------");
        for(int i=0; i<3; i++){
            System.out.print("| ");
            for(int j=0; j<3; j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }
    
    private static String analyzeGame(){
        String answer="";
        int empty1 = countCar(' ');
        int empty2 = countCar('_');
        boolean isImpossible = Math.abs(countCar('X') - countCar('O')) > 1 || isAWinner('X') && isAWinner('O');
        if(isImpossible){
            answer = "Impossible";
        }else if( empty1 + empty2 >0 && !(isAWinner('X') || isAWinner('O'))){
            answer = "Game not finished";
        } else {
            answer = isAWinner('X') ? "X wins" : isAWinner('O') ? "O wins" : "Draw";
        }
        return answer;
    }
    
    private static int countCar(char c){
        int count = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(grid[i][j].charAt(0)==c){
                    count++;
                }
            }
        }
        return count;
    }
    private static boolean checkRows(char c){
        boolean winner = false;
        for(int i=0; i<3; i++){
            winner = true;
            for(int j=0; j<3; j++){
                winner =  winner && grid[i][j].charAt(0)==c;
            }
            if(winner){
                break;
            }        
        }
        return winner;
    }
    private static boolean checkCols(char c){
        boolean winner = false;
        for(int j=0; j<3; j++){
            winner = true;
            for(int i=0; i<3; i++){
                winner =  winner && grid[i][j].charAt(0)==c;
            }
            if(winner){
                break;
            }
        }
        return winner;
    }
    private static boolean checkDiags(char c){
        boolean winner1 = true;
        boolean winner2 = true;
            for(int i=0; i<3; i++){
                winner1 =  winner1 && grid[i][i].charAt(0)==c;
                winner2 =  winner2 && grid[i][2-i].charAt(0)==c;
            }

        return winner1||winner2;
    }

    private static boolean isAWinner(char c){
        return checkRows(c) || checkCols(c) || checkDiags(c);
    }
}
/* STAGE 4
package tictactoe;
import java.util.Scanner;

public class Main {
    //private static Scanner sc = new Scanner(System.in);
    private static String[][] grid = new String[3][3];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        fillGrid(sc);
        showGrid();
        //System.out.println(analyzeGame());
        getInput(sc);
        showGrid();
    }

        private static void fillGrid(Scanner sc){
            System.out.print("Enter cells: ");
            String inputLine = sc.nextLine();
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    int beg = 3*i + j;
                    grid[i][j] = beg<inputLine.length()? inputLine.substring(beg, beg+1).replace('_', ' ') : " ";
                }
            }
    }

    private static void getInput(Scanner sc) {
        boolean isCorrect = false;
        boolean areInts = true;
        while (!isCorrect) {
            System.out.print("Enter the coordinates: ");
            int x = 0;
            int y = 0;
            try {
                x = sc.nextInt();
                y = sc.nextInt();
            } catch (Exception e) {
                areInts = false;
                // the private static Scanner sc (see line 5) does not work here for whatever reason
            }
            isCorrect = areInts && x >= 1 && x <= 3 && y >= 1 && y <= 3 && grid[3-y][x-1].equals(" ");
            if (isCorrect) {
                grid[3-y][x-1] = "X";
            }else if (areInts && !(x >= 1 && x <= 3 && y >= 1 && y <= 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
            }else if(areInts){
                System.out.println("This cell is occupied! Choose another one!");
            }else{
                System.out.println("You should enter numbers!");
            }
        }
    }

    private static void showGrid(){
        System.out.println("---------");
        for(int i=0; i<3; i++){
            System.out.print("| ");
            for(int j=0; j<3; j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    private static String analyzeGame(){
        String answer="";
        int empty1 = countCar(' ');
        int empty2 = countCar('_');
        boolean isImpossible = Math.abs(countCar('X') - countCar('O')) > 1 || isAWinner('X') && isAWinner('O');
        if(isImpossible){
            answer = "Impossible";
        }else if( empty1 + empty2 >0 && !(isAWinner('X') || isAWinner('O'))){
            answer = "Game not finished";
        } else {
            answer = isAWinner('X') ? "X wins" : isAWinner('O') ? "O wins" : "Draw";
        }
        return answer;
    }

    private static int countCar(char c){
        int count = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(grid[i][j].charAt(0)==c){
                    count++;
                }
            }
        }
        return count;
    }
    private static boolean checkRows(char c){
        boolean winner = false;
        for(int i=0; i<3; i++){
            winner = true;
            for(int j=0; j<3; j++){
                winner =  winner && grid[i][j].charAt(0)==c;
            }
            if(winner){
                break;
            }
        }
        return winner;
    }
    private static boolean checkCols(char c){
        boolean winner = false;
        for(int j=0; j<3; j++){
            winner = true;
            for(int i=0; i<3; i++){
                winner =  winner && grid[i][j].charAt(0)==c;
            }
            if(winner){
                break;
            }
        }
        return winner;
    }
    private static boolean checkDiags(char c){
        boolean winner1 = true;
        boolean winner2 = true;
            for(int i=0; i<3; i++){
                winner1 =  winner1 && grid[i][i].charAt(0)==c;
                winner2 =  winner2 && grid[i][2-i].charAt(0)==c;
            }

        return winner1||winner2;
    }

    private static boolean isAWinner(char c){
        return checkRows(c) || checkCols(c) || checkDiags(c);
    }
}

 */

/* STAGE 3
package tictactoe;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static String[][] grid = new String[3][3];
    public static void main(String[] args) {
        fillGrid();
        showGrid();
        System.out.println(analyzeGame());
    }

    private static void fillGrid(){
        String inputLine = sc.nextLine();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                int beg = 3*i + j;
                grid[i][j] = inputLine.substring(beg, beg+1);
            }
        }


    }

    private static void showGrid(){
        System.out.println("---------");
        for(int i=0; i<3; i++){
            System.out.print("| ");
            for(int j=0; j<3; j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    private static String analyzeGame(){
        String answer="";
        int empty1 = countCar(' ');
        int empty2 = countCar('_');
        boolean isImpossible = Math.abs(countCar('X') - countCar('O')) > 1 || isAWinner('X') && isAWinner('O');
        if(isImpossible){
            answer = "Impossible";
        }else if( empty1 + empty2 >0 && !(isAWinner('X') || isAWinner('O'))){
            answer = "Game not finished";
        } else {
            answer = isAWinner('X') ? "X wins" : isAWinner('O') ? "O wins" : "Draw";
        }
        return answer;
    }

    private static int countCar(char c){
        int count = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(grid[i][j].charAt(0)==c){
                    count++;
                }
            }
        }
        return count;
    }
    private static boolean checkRows(char c){
        boolean winner = false;
        for(int i=0; i<3; i++){
            winner = true;
            for(int j=0; j<3; j++){
                winner =  winner && grid[i][j].charAt(0)==c;
            }
            if(winner){
                break;
            }
        }
        return winner;
    }
    private static boolean checkCols(char c){
        boolean winner = false;
        for(int j=0; j<3; j++){
            winner = true;
            for(int i=0; i<3; i++){
                winner =  winner && grid[i][j].charAt(0)==c;
            }
            if(winner){
                break;
            }
        }
        return winner;
    }
    private static boolean checkDiags(char c){
        boolean winner1 = true;
        boolean winner2 = true;
            for(int i=0; i<3; i++){
                winner1 =  winner1 && grid[i][i].charAt(0)==c;
                winner2 =  winner2 && grid[i][2-i].charAt(0)==c;
            }

        return winner1||winner2;
    }

    private static boolean isAWinner(char c){
        return checkRows(c) || checkCols(c) || checkDiags(c);
    }
}
 */
/* STAGE 2
package tictactoe;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static String[][] grid = new String[3][3];
    public static void main(String[] args) {
        fillGrid();
        showGrid();
    }

    private static void fillGrid(){
        String inputLine = sc.nextLine();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                int beg = 3*i + j;
                grid[i][j] = inputLine.substring(beg, beg+1);
            }
        }


    }

    private static void showGrid(){
        System.out.println("---------");
        for(int i=0; i<3; i++){
            System.out.print("| ");
            for(int j=0; j<3; j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }
}
 */
/* STAGE 1
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
 */
