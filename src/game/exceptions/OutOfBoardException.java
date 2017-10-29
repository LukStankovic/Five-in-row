
package game.exceptions;

/**
 *
 * @author lukstankovic
 */
public class OutOfBoardException extends RuntimeException {

	@Override
	public String getMessage() {
		return "You are outside of the board!"; 
	}
	
}
