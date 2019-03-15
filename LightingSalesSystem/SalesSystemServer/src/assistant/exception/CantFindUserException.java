package assistant.exception;

public class CantFindUserException extends Myexception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1245493963838463918L;

	public String getMessage(){
		return "不存在该用户";
	}
}
