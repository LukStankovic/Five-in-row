/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.player;

import game.board.Board;
import game.board.BoardSymbol;
import java.util.Random;

/**
 *
 * @author lukas
 */
public class StupidPlayer implements IMove{

    @Override
    public Move makeMove(Board board, BoardSymbol yourSymbol) {
        int pozX, pozY;
        Random rand = new Random();
        do{
            pozX = rand.nextInt(board.getBoardSize());
            pozY = rand.nextInt(board.getBoardSize());
        }while(board.getSymbolAtPosition(pozX, pozY) != null);
        return new Move(pozX, pozY, yourSymbol);
    }

    @Override
    public String getName() {
        return "Stupid player";
    }
    
}
