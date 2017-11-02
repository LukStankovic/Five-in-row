/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.board;

import game.exceptions.CheaterException;
import game.exceptions.OutOfBoardException;
import game.exceptions.SymbolAlreadyThereException;
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
	private int symbolCounts = 0;

	public int getSymbolCounts() {
		return symbolCounts;
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

    
	
    public void setSymbolAccordingToMove(Move move, int count) {
		if (!isInBoard(move.getPositionX(), move.getPositionY())) {
			throw new OutOfBoardException();
		} else if(board[move.getPositionX()][move.getPositionY()] != null){
            throw new SymbolAlreadyThereException();
		} else if (count != symbolCounts) {
			throw new CheaterException();
        }else{
			board[move.getPositionX()][move.getPositionY()] = move.getSymbol();
			symbolCounts++;
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
