/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import exceptions.CheaterException;
import game.board.Board;
import game.board.BoardSymbol;
import game.player.Human;
import game.player.IMove;
import game.player.Move;

/**
 *
 * @author lukstankovic
 */
public class GUIGame extends Game {

	public GUIGame(int boardSize, IMove player1) {
		super(boardSize, player1, new Human());
	}
	
	public boolean makeMove(Move move) {
		if (!board.setSymbolAccordingToMove(move)) {
			return false;
		}
		return isWinner(board, move, player2);
	}
	
	public boolean makeMove(BoardSymbol symbol) {
		return makeMove(player1, symbol);
	}

	public Board getBoard() {
		return board;
	}
	
	
}
