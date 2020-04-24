package jar;

import java.util.List;



import com.google.gson.JsonObject;

public class ErrorMessages {
	
	String Code;
	public String getCode() {
		return Code;
	}


	public void setCode(String code) {
		Code = code;
	}


	public String getMessage() {
		return Message;
	}


	public void setMessage(String message) {
		Message = message;
	}


	String Message;
	
	
	public Object createErrorResponse(String code ,String msg) {
		JsonObject error= new JsonObject();
		error.addProperty("FaultCode", code);
		error.addProperty("FaultMessage", msg);
		
		JsonObject Fault= new JsonObject();
		Fault.add("FaultList", error);
		
		
		return Fault;
		
	}
	

}
