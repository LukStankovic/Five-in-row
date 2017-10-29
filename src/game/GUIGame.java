/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.exceptions.CheaterException;
import game.board.Board;
import game.board.BoardSymbol;
import game.exceptions.OutOfBoardException;
import game.exceptions.SymbolAlreadyThereException;
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
		try {
			board.setSymbolAccordingToMove(move, board.getCountOfSymbols());
			return isWinner(board, move, player2);
		} catch (OutOfBoardException e) {
			System.err.println(e.getMessage());
		} catch (SymbolAlreadyThereException e) {
			System.err.println(e.getMessage());
		} catch (CheaterException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public boolean makeMove(BoardSymbol symbol) {
		try {
			return makeMove(player1, symbol);
		} catch (OutOfBoardException e) {
			System.err.println(e.getMessage());
		} catch (SymbolAlreadyThereException e) {
			System.err.println(e.getMessage());
		} catch (CheaterException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public Board getBoard() {
		return board;
	}
	
	
}
