
package exceptions;

/**
 *
 * @author lukstankovic
 */
public class SymbolAlreadyThereException extends RuntimeException {

	@Override
	public String getMessage() {
		return "There is already a symbol."; 
	}
	
}
