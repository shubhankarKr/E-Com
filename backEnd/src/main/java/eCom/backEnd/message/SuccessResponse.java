package eCom.backEnd.message;

public class SuccessResponse implements ResonseMessage {
	private Object successResponse;

	public SuccessResponse(Object successResponse) {
		super();
		this.successResponse = successResponse;
	}

	public Object getSuccessResponse() {
		return successResponse;
	}

	public void setSuccessResponse(Object successResponse) {
		this.successResponse = successResponse;
	}
	
}