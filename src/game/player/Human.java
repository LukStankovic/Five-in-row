/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.player;

import game.board.Board;
import game.board.BoardSymbol;
import java.util.Scanner;

/**
 *
 * @author lukas
 */
public class Human implements IMove{

    @Override
    public Move makeMove(Board board, BoardSymbol yourSymbol) {
        int pozX, pozY;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Enter the coordinates of your move: ");
            pozX = sc.nextInt();
            pozY = sc.nextInt();
        }while(board.getSymbolAtPosition(pozX, pozY) != null);
        return new Move(pozX, pozY, yourSymbol);
    }

    @Override
    public String getName() {
        return "Human";
    }
    
}
