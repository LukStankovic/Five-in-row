/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.player;

import game.board.Board;
import game.board.BoardSymbol;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author lukstankovic
 */
public class Sta0445 implements IMove {

	private Move bestMove;
	
	
	private BoardSymbol intelligentPlayerSymbol;
	private BoardSymbol opponentSymbol;
	
	@Override
	public Move makeMove(Board board, BoardSymbol yourSymbol) {
		bestMove = new Move(0, 0, yourSymbol);
		intelligentPlayerSymbol = yourSymbol;
		opponentSymbol = (intelligentPlayerSymbol == BoardSymbol.CIRCLE) ? BoardSymbol.CROSS : BoardSymbol.CIRCLE;
		
		ArrayList<Move> availablePositions = getAvailablePositions(board);
		
		int max = Integer.MIN_VALUE;

		for (Move availablePosition : availablePositions) {
			int value = miniMax(
					board, 
					new Move(
						availablePosition.getPositionX(), 
						availablePosition.getPositionY(), 
						intelligentPlayerSymbol
					),
					intelligentPlayerSymbol, 
					0
			);
			
			if (value > max) {
				max = value;
			}
		}
		return bestMove;
	}

	@Override
	public String getName() {
		return "STA0445";
	}
	
	private int miniMax(Board board, Move move, BoardSymbol symbol, int depth) {
		if (isWinner(board, move) && move.getSymbol() == intelligentPlayerSymbol) {
			bestMove = new Move(move.getPositionX(), move.getPositionY(), intelligentPlayerSymbol);
			return 1;
		} else if (isWinner(board, move) && move.getSymbol() != intelligentPlayerSymbol) {
			bestMove = new Move(move.getPositionX(), move.getPositionY(), intelligentPlayerSymbol);
			return -1;
		}
		
		ArrayList<Move> availablePositions = getAvailablePositions(board);
	
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for (Move availablePosition : availablePositions) {
			Board deepBoard = boardCopy(board);
			int val;
		
			if (symbol == intelligentPlayerSymbol) {
				Move deepMove = new Move(availablePosition.getPositionX(), availablePosition.getPositionY(), symbol);
				deepBoard.setSymbolAccordingToMove(deepMove, deepBoard.getCountSymbols());	
				val = miniMax(deepBoard, deepMove, opponentSymbol, depth+1);
				if (val > max) {
					max = val;
				}
				return max;
			} else {
				Move deepMove = new Move(availablePosition.getPositionX(), availablePosition.getPositionY(), symbol);
				deepBoard.setSymbolAccordingToMove(deepMove, deepBoard.getCountSymbols());	
				val = miniMax(deepBoard, deepMove, intelligentPlayerSymbol, depth+1);
				if (val < min) {
					min = val;
				}
				return min;
			}
			
		}
		
		return 1; 
	}
	
	private int getScore() {
		return 0;
	}
	
	
	private ArrayList<Move> getAvailablePositions(Board board) {
		ArrayList<Move> availablePositions = new ArrayList<>();
		
		for (int i = 0; i < board.getBoardSize(); i++) {
			for (int j = 0; j < board.getBoardSize(); j++) {
				if (board.getSymbolAtPosition(j, i) == null) {
					availablePositions.add(new Move(j, i, null));
				}
			}
		}
		return availablePositions;
	}
	
	
	
	private Board boardCopy(Board board) {
		Board copiedBoard = new Board(board.getBoardSize());
		for (int i = 0; i < copiedBoard.getBoardSize(); i++) {
			for (int j = 0; j < copiedBoard.getBoardSize(); j++) {
				copiedBoard.setSymbolAccordingToMove(new Move(j, i, board.getSymbolAtPosition(j, i)), copiedBoard.getCountOfSymbols());
			}
		}
		return copiedBoard;
	}
	
	private int[][] movePoints = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}};
	private boolean isWinner(Board board, Move move1) {
		for (int i = 0; i < 4; i++) {
			int symbolCount = 1;
			for (int j = 1; j < 5; j++) {
				if (!isSame(board, move1.getPositionX() + movePoints[i][0] * j, move1.getPositionY() + movePoints[i][1] * j, move1.getSymbol())) {
					break;
				}
				symbolCount++;
			}
			
			for (int j = -1; j > -5; j--) {
				if (!isSame(board, move1.getPositionX() + movePoints[i][0] * j, move1.getPositionY() + movePoints[i][1] * j, move1.getSymbol())) {
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
		
	private Move copyMove(Move move) {
		return new Move(move.getPositionX(), move.getPositionY(), move.getSymbol());
	}
}


