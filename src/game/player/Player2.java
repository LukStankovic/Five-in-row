/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.player;

import game.board.Board;
import game.board.BoardSymbol;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author lukas
 */
public class Player2 implements IMove{

    @Override
    public Move makeMove(Board board, BoardSymbol yourTurn) {
        Random r = new Random();
        switch(r.nextInt(2)){
            case 0: return new Move(-5, -5, yourTurn);
            case 1: return new Move(1000, 1000, yourTurn);
        }
        return new Move(1000, -5, yourTurn);
    }

    @Override
    public String getName() {
        return "OutOfBoardException";
    }
	
}
