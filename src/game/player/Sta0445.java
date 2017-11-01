/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.player;

import game.board.Board;
import game.board.BoardSymbol;
import java.util.ArrayList;

/**
 *
 * @author lukstankovic
 */
public class Sta0445 implements IMove {
	private Move bestMove;
	private BoardSymbol mySymbol;
	private BoardSymbol opponentSymbol;
	
	@Override
	public Move makeMove(Board board, BoardSymbol mySymbol) {
		bestMove = new Move(0, 0, mySymbol);
		this.mySymbol = mySymbol;
		opponentSymbol = (mySymbol == BoardSymbol.CIRCLE) ? BoardSymbol.CROSS : BoardSymbol.CIRCLE;
		
		ArrayList<Move> positions = getAvailablePositions(board);
		int max = Integer.MIN_VALUE;
		
		ArrayList<Move> win = new ArrayList<>();
		
		for (Move position : positions) {
			Board boardCopy = copyBoard(board);
			Move newMove = new Move(position.getPositionX(), position.getPositionY(), mySymbol);
			boardCopy.setSymbolAccordingToMove(newMove, boardCopy.getCountSymbols());
			int val = miniMax(boardCopy, newMove, mySymbol, 0);
			System.out.println(val);
			if (val > max) {
				max = val;
				System.out.println("val: " + val + " > " + max+  " ~ "+ newMove.getPositionX() + " - " + newMove.getPositionY());
				win.add(newMove);
				bestMove = new Move(newMove.getPositionX(), newMove.getPositionY(), mySymbol);
			}
		}
		
		
		for (Move move : win) {
			System.out.println(move.getPositionX() + ", " + move.getPositionY());
		}
		
		return bestMove;
	}

	@Override
	public String getName() {
		return "STA0445";
	}
	
	private int miniMax(Board board, Move move, BoardSymbol symbol, int depth) {
		depth++;
		if (isWinner(board, move) && move.getSymbol() == mySymbol) {
			return 1;
		} else if (isWinner(board, move) && move.getSymbol() != mySymbol) {
			return -1;
		}
		
		ArrayList<Move> positions = getAvailablePositions(board);
		Board boardCopy = copyBoard(board);
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for (Move position : positions) {
			if (symbol == mySymbol) {
				Move newMove = new Move(position.getPositionX(), position.getPositionY(), mySymbol);
				boardCopy.setSymbolAccordingToMove(newMove, boardCopy.getCountSymbols());
				int val = miniMax(boardCopy, newMove, opponentSymbol, depth);
				if (val > max) {
					max = val;
				}
//								System.out.println(board);

				return max;
			} else {
				Move newMove = new Move(position.getPositionX(), position.getPositionY(), opponentSymbol);
				boardCopy.setSymbolAccordingToMove(newMove, boardCopy.getCountSymbols());
				int val = miniMax(boardCopy, newMove, mySymbol, depth);
				if (val < min) {
					min = val;
				}
//				System.out.println(board);
				return min;
			}
		}
		
		return 1;
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
	
	private Board copyBoard(Board board) {
		Board copiedBoard = new Board(board.getBoardSize());
		for (int i = 0; i < copiedBoard.getBoardSize(); i++) {
			for (int j = 0; j < copiedBoard.getBoardSize(); j++) {
				copiedBoard.setSymbolAccordingToMove(new Move(j, i, board.getSymbolAtPosition(j, i)), copiedBoard.getCountOfSymbols());
			}
		}
		return copiedBoard;
	}
}
