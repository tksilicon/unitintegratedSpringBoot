package com.unitintegrated.demo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * This exception is thrown when the Department can't be found in the
 * application if searching by ID.
 * 
 * @author ThankGod Ukachukwu
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DepartmentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7870207355373585854L;

	public DepartmentNotFoundException(Integer id) {
		super("Could not find department " + id);
	}

}
