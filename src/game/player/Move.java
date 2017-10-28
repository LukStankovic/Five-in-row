
package game.player;

import game.board.BoardSymbol;

/**
 *
 * @author lukas
 */
public class Move {
    private int positionX;
    private int positionY;
    private BoardSymbol symbol;
	
    public Move(int positionX, int positionY, BoardSymbol symbol) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.symbol = symbol;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public BoardSymbol getSymbol() {
        return symbol;
    }
    
}
