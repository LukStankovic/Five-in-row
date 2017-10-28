/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.player;

import game.board.Board;
import game.board.BoardSymbol;

/**
 *
 * @author lukas
 */
public class Player1 implements IMove{

    @Override
    public Move makeMove(Board board, BoardSymbol yourTurn) {
        return new Move(0, 0, yourTurn);
    }

    @Override
    public String getName() {
        return "SymbolAlreadyThereException";
    }
    
}
