/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.player;

import game.exceptions.CheaterException;
import game.board.Board;
import game.board.BoardSymbol;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukas
 */
public class Player3 implements IMove{

    @Override
    public Move makeMove(Board board, BoardSymbol yourTurn) {
        int pozX, pozY;
        Random rand = new Random();
        for(int i = 0; i < 5; i++){
            pozX = rand.nextInt(board.getBoardSize());
            pozY = rand.nextInt(board.getBoardSize());
            if(board.getSymbolAtPosition(pozX, pozY) == null) {
				board.setSymbolAccordingToMove(new Move(pozX, pozY, yourTurn), board.getCountOfSymbols());
			}
        }
        do{
            pozX = rand.nextInt(board.getBoardSize());
            pozY = rand.nextInt(board.getBoardSize());
        }while(board.getSymbolAtPosition(pozX, pozY) != null);
        return new Move(pozX, pozY, yourTurn);
    }

    @Override
    public String getName() {
        return "CheaterException";
    }
    
}
