/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.player;

import game.board.Board;
import game.board.BoardSymbol;
import java.awt.Point;

/**
 *
 * @author lukstankovic
 */
public class InteligentPlayer implements IMove { 

	@Override
	public Move makeMove(Board board, BoardSymbol yourSymbol) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getName() {
		return "Inteligent player";
	}
	
	private int miniMax(Board board, Point movePoint, int depth, int alpha, int beta, boolean max) {
		if (depth == 0) {
			return 0;
		} else if (depth == -1) { // todo není winner
			return -10;
		}
		
		
		
		return 1;
	}
}
