package eCom.backEnd.message;

public class ErrorResponse implements ResonseMessage {
	private Object errorResponse;

	public ErrorResponse(Object errorResponse) {
		super();
		this.errorResponse = errorResponse;
	}

	public Object getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(Object errorResponse) {
		this.errorResponse = errorResponse;
	}


}