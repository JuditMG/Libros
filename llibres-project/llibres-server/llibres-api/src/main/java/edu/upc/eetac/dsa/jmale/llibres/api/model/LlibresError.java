package edu.upc.eetac.dsa.jmale.llibres.api.model;

public class LlibresError {
	private int status;
	private String message;

	public LlibresError() {
		super();
	}

	public LlibresError(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}