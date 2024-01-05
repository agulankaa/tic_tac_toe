package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    final int SIZE = 3;
    char[][] grid = new char[SIZE][SIZE];
    char player;

    public TicTacToe(String gridStatus){
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                if(gridStatus == null || gridStatus.length() != SIZE * SIZE) {
                    this.grid[i][j] = '_';
                }
                else{
                    this.grid[i][j] = gridStatus.charAt(i*SIZE +j);
                }
            }
        }
        this.player = 'X';
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe(null);
        game.printGrid();

        boolean correctInput = false;
        boolean gameEnd = false;
        while(!gameEnd){
            while(!correctInput){
                String coordinateX = scanner.next();
                boolean isXCorrect = checkInput(coordinateX);
                String coordinateY = scanner.next();
                boolean isYCorrect = checkInput(coordinateY);
                if(isXCorrect && isYCorrect){
                    correctInput = game.insertMove(Integer.parseInt(coordinateX), Integer.parseInt(coordinateY));
                }
            }
            correctInput = false;
            game.printGrid();
            gameEnd = game.printResults();
        }
    }

    public void printGrid(){
        System.out.println("---------");
        for(int i=0; i<SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%c ", this.grid[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean printResults() {
        int countX = countChar('X', this.grid);
        int countO = countChar('O', this.grid);
        int countFree = countChar('_', this.grid);

        boolean winX = charWins('X', this.grid);
        boolean winO = charWins('O', this.grid);

        if ((Math.abs(countX - countO) > 1) || (winX && winO)) {
            System.out.println("Impossible");
            return true;
        } else if (winX) {
            System.out.println("X wins");
            return true;
        } else if (winO) {
            System.out.println("O wins");
            return true;
        } else if (countFree == 0) {
            System.out.println("Draw");
            return true;
        } else{
            return false;
        }

    }

    public static int countChar(char character, char[][] array){
        int count =0;
        for (char[] charRow : array) {
            for (char c : charRow) {
                if (c == character) count++;
            }
        }
        return count;
    }

    public static boolean charWins(char character, char[][]array){
        int winSum = character * array.length;
        int row = 0;
        int col = 0;
        int diagRight = 0;
        int diagLeft = 0;

        for(int i=0; i<array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                row += array[i][j];
                col += array[j][i];
            }
            if(row == winSum || col == winSum){
                return true;
            }
            row = 0;
            col = 0;
            diagRight += array[i][i];
            diagLeft += array[i][array.length-i-1];
        }
        return diagRight == winSum || diagLeft == winSum;
    }

    public static boolean checkInput(String coordinate){
        final int MIN = 1;
        final int MAX = 3;
        try {
            int number = Integer.parseInt(coordinate);
            if (number >= MIN && number <= MAX) {return true;}
            else{
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("You should enter numbers!");
            return false;
        }
    }

    public boolean insertMove(int x, int y){
        if (this.grid[x-1][y-1] == '_'){
            this.grid[x-1][y-1] = this.player;

            if(this.player == 'X'){ this.player = 'O';}
            else {this.player = 'X';}

            return true;
        }else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }
}
