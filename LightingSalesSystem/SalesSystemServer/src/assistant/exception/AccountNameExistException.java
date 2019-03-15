package assistant.exception;

public class AccountNameExistException extends Myexception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9079859136544401289L;
	
	public String getMessage(){
		return "账户名已存在！";
	}
}
