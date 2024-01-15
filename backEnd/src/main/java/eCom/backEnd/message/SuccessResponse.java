package eCom.backEnd.message;

public class SuccessResponse implements ResonseMessage {
	private Object success;

	public SuccessResponse(Object success) {
		super();
		this.success = success;
	}
	public Object getSuccess() {
		return success;
	}

	public void setSuccess(Object success) {
		this.success = success;
	}
}