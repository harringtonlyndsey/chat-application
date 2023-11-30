import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> computerPositions = new ArrayList<Integer>();

    public static void main (String[] args){
        //game board set up
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}, };
        printGameBoard(gameBoard);

        //player enters a character
                while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter a number 1-9");
            int playerPos = scan.nextInt();

            while(playerPositions.contains(playerPos) || computerPositions.contains(playerPos)){
                        System.out.println("Spot taken, try again");
                        playerPos = scan.nextInt();
                    }

            placePiece(gameBoard, playerPos, "Player");
            String result = checkWinner();

                    if (result.length() > 0){
                        System.out.println(result);
                        break;
                    }
            Random rand = new Random();

            int compPos = rand.nextInt(9) + 1;
                    while(playerPositions.contains(compPos) || computerPositions.contains(compPos)){
                        compPos = rand.nextInt(9) + 1;
                    }
            placePiece(gameBoard, compPos, "Computer");

            printGameBoard(gameBoard);

            if (result.length() > 0){
                System.out.println(result);
                break;
            }

        }

    }
}
public static void printGameBoard(char[][] gameBoard){
    //prints game board
    for(char[] row : gameBoard){
        for(char c : row){
            System.out.print(c);
        }
        System.out.println();
    }
}
public static void placePiece(char[][] gameBoard, int pos, String user){
    char symbol = ' ';
    if(user.equals("Player")){
        symbol = 'X';
        playerPositions.add(pos);
    } else if (user.equals("Computer")){
        symbol = 'O';
        computerPositions.add(pos);
    }

    switch(pos) {
        case 1:
            gameBoard[0][0] = symbol;
            break;
        case 2:
            gameBoard[0][2] = symbol;
            break;
        case 3:
            gameBoard[0][4] = symbol;
            break;
        case 4:
            gameBoard[2][0] = symbol;
            break;
        case 5:
            gameBoard[2][2] = symbol;
            break;
        case 6:
            gameBoard[2][4] = symbol;
            break;
        case 7:
            gameBoard[4][0] = symbol;
            break;
        case 8:
            gameBoard[4][2] = symbol;
            break;
        case 9:
            gameBoard[4][4] = symbol;
            break;
        default:
            break;
    }
}
public static String checkWinner(){
    List topRow = Arrays.asList(1, 2, 3);
    List midRow = Arrays.asList(4, 5, 6);
    List botRow = Arrays.asList(7, 8, 9);
    List leftCol = Arrays.asList(1, 4,7);
    List midCol = Arrays.asList(2, 5, 8);
    List rightCol = Arrays.asList(3, 6, 9);
    List crossOne = Arrays.asList(1, 5, 9);
    List crossTwo = Arrays.asList(7, 5, 3);

    List<List> winningConditions = new ArrayList<List>();
    winningConditions.add(topRow);
    winningConditions.add(midRow);
    winningConditions.add(botRow);
    winningConditions.add(leftCol);
    winningConditions.add(midCol);
    winningConditions.add(rightCol);
    winningConditions.add(crossOne);
    winningConditions.add(crossTwo);

    for(List l : winningConditions){
        if(playerPositions.containsAll(l)){
            return "You are the winner";
        } else if (computerPositions.containsAll(l)){
            return "Computer wins";
        } else if (playerPositions.size() + computerPositions.size() == 9){
            return "It's a draw";
        }
    }
    return "";
}