package cc.pogoda.backend.types;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Station with given name doesn't exist")
public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4459447262988098018L;

}
