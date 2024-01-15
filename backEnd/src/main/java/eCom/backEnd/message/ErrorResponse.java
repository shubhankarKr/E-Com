package eCom.backEnd.message;

public class ErrorResponse implements ResonseMessage {
	private Object error;

	public ErrorResponse(Object error) {
		super();
		this.error = error;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

}