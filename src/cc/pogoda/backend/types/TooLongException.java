package cc.pogoda.backend.types;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, reason = "Sorry but I am not able to process so long time range")
public class TooLongException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6353846643857395361L;

}
