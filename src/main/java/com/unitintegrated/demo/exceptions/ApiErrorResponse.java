package com.unitintegrated.demo.exceptions;


import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.FieldError;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public class ApiErrorResponse {

	private String code;

	private String message;

	private String field;

	private String status;

	public ApiErrorResponse() {
		super();
	}

	public ApiErrorResponse(String code, String status) {
		super();
		this.code = code;
		this.status = status;
		this.message = "The field(s) are/is required";
	}

	public ApiErrorResponse(String code, String message, String field, String httpCodeMessage) {
		super();
		this.code = code;
		this.message = message;
		this.field = field;
		this.status = httpCodeMessage;
	}

	public ApiErrorResponse(String code, String message, String field) {
		super();
		this.code = code;
		this.message = message;
		this.field = field;
	}
	
	

	public ApiErrorResponse(String message) {
		super();
		this.message = message;
	}

	private void addValidationError(String field, Object rejectedValue) {
		
		//if (message == null){
			//message = "";
		//}
		if (this.field == null){
			this.field = "";
		}
		//message += "The field " + field + "=" + rejectedValue + " is not valid";
		//message += ", ";
		this.field += field;
		this.field += ",";

	}

	private void addValidationError(String field) {
		message += "The field " + field + " is not valid";
		message += ", ";
		this.field += " " + field;
		this.field += ",";

	}

	private void addValidationError(FieldError fieldError) {
		this.addValidationError(fieldError.getField(), fieldError.getRejectedValue());
		
		if(fieldError.getField().toLowerCase().equals("email")){
			code = "USR_03";
			
		}
		
		if(fieldError.getField().toLowerCase().equals("creditcard")){
			code = "USR_08";
			message = "this is an invalid Credit Card";
			
		}
		
		if(fieldError.getField().toLowerCase().equals("creditcard")){
			code = "USR_08";
			message = "this is an invalid Credit Card";
			
		}
		
		if(fieldError.getField().toLowerCase().equals("dayphone")){
			code = "USR_06";
			message = "this is an invalid phone number.";
			
		}
		
		if(fieldError.getField().toLowerCase().equals("evephone")){
			code = "USR_06";
			message = "this is an invalid phone number.";
			
		}
		
		if(fieldError.getField().toLowerCase().equals("mobphone")){
			code = "USR_06";
			message = "this is an invalid phone number.";
			
		}
		
		
		
		
		
		
	}

	public void addValidationErrors(List<FieldError> fieldErrors) {

		for (FieldError error : fieldErrors) {

			if (!error.toString().isEmpty())
				this.addValidationError(error);

		}

	}

	/**
	 * Utility method for adding error of ConstraintViolation. Usually when
	 * a @Validated validation fails.
	 *
	 * @param cv the ConstraintViolation
	 */
	private void addValidationError(ConstraintViolation<?> cv) {
		this.addValidationError(((PathImpl) cv.getPropertyPath()).getLeafNode().asString());
	}

	public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
		constraintViolations.forEach(this::addValidationError);

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
