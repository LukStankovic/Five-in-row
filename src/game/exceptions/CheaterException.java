
package game.exceptions;

/**
 *
 * @author lukstankovic
 */
public class CheaterException extends RuntimeException {

	@Override
	public String getMessage() {
		return "You are cheating!"; 
	}
	
}
