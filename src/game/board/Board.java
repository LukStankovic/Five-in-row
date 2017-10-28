/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.board;

import exceptions.CheaterException;
import exceptions.OutOfBoardException;
import exceptions.SymbolAlreadyThereException;
import game.player.Move;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukas
 */
public class Board {
    private int boardSize;
    private BoardSymbol[][] board;
	private BoardSymbol latestSymbol;
	private int countSymbols = 0;

	public int getCountSymbols() {
		return countSymbols;
	}

    public Board(int boardSize) {
        this.boardSize = boardSize;
        board = new BoardSymbol[boardSize][boardSize];
    }

    public int getBoardSize() {
        return boardSize;
    }
    
    public BoardSymbol getSymbolAtPosition(int x, int y){
        return board[x][y];
    }
    
    public boolean setSymbolAccordingToMove(Move move) {
		if (!isInBoard(move.getPositionX(), move.getPositionY())) {
			throw new OutOfBoardException();
		}
		
        if(board[move.getPositionX()][move.getPositionY()] == null){
			
			try {
				if (move.getSymbol() == this.latestSymbol) {
					throw new CheaterException();
				}
			} catch (CheaterException e) {
				System.err.println(e.getMessage());
				return false;
			}
			
            board[move.getPositionX()][move.getPositionY()] = move.getSymbol();
			this.latestSymbol = move.getSymbol();
			return true;
        }else{
			throw new SymbolAlreadyThereException();
        }
    }
    
    private boolean isInBoard(int posX, int posY) {
		return posX >= 0 && posY >= 0 && posX < getBoardSize() && posY < getBoardSize();
	}
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if(board[j][i] == BoardSymbol.CIRCLE){
                    sb.append('O');
                }else if (board[j][i] == BoardSymbol.CROSS){
                    sb.append('X');
                }else{
                    sb.append('.');
                } 
            }
            sb.append('\n');
        }
        return sb.toString(); 
    } 
	
}
