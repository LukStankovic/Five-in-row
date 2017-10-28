
package exceptions;

/**
 *
 * @author lukstankovic
 */
public class CheaterException extends Exception {

	@Override
	public String getMessage() {
		return "You are cheating!"; 
	}
	
}
