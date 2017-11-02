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
import game.player.IMove;
import game.player.Move;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukas
 */
public class Game {
    protected Board board;
    protected IMove player1;
    protected IMove player2;
	
    public Game(int boardSize, IMove player1, IMove player2) {
        board = new Board(boardSize);
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public IMove playGame(){
        for(;;){
            System.out.println(board);
			try {
				if (makeMove(player1, BoardSymbol.CIRCLE)) {
					return player1;
				}
			} catch (OutOfBoardException e) {
				e.getMessage();
				return player2;
			} catch (SymbolAlreadyThereException e) {
				e.getMessage();
				return player2;
			} catch (CheaterException e) {
				e.getMessage();
				return player2;
			}
			if (board.getSymbolCounts() == board.getBoardSize() * board.getBoardSize()) {
				return null;
			}
			System.out.println(board);
			try {
				if (makeMove(player2, BoardSymbol.CROSS)) {
					return player2;
				}
			} catch (OutOfBoardException e) {
				e.getMessage();
				return player1;
			} catch (SymbolAlreadyThereException e) {
				e.getMessage();
				return player1;
			} catch (CheaterException e) {
				e.getMessage();
				return player1;
			}
        }
    }
	
	protected boolean makeMove(IMove player, BoardSymbol symbol) {
		int count = board.getCountOfSymbols();
        Move move = player.makeMove(board, symbol);
		board.setSymbolAccordingToMove(move, count);
		return isWinner(board, move, player2);
	}

	private int[][] move = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}};
	
    protected boolean isWinner(Board board, Move move1, IMove player1) {
		for (int i = 0; i < 4; i++) {
			int symbolCount = 1;
			
			for (int j = 1; j < 5; j++) {
				if (!isSame(board, move1.getPositionX() + move[i][0] * j, move1.getPositionY() + move[i][1] * j, move1.getSymbol())) {
					break;
				}
				symbolCount++;
			}
			
			for (int j = -1; j > -5; j--) {
				if (!isSame(board, move1.getPositionX() + move[i][0] * j, move1.getPositionY() + move[i][1] * j, move1.getSymbol())) {
					break;
				}
				symbolCount++;
			}
			
			if (symbolCount == 5) {
				return true;
			}
		}
	
    	return false;
    }
    
	private boolean isSame(Board board, int x, int y, BoardSymbol symbol) {
		if (x < 0 || y < 0 || x >= board.getBoardSize() || y >= board.getBoardSize()) {
			return false;
		}
		
		return board.getSymbolAtPosition(x, y) == symbol;
	}

    
}
