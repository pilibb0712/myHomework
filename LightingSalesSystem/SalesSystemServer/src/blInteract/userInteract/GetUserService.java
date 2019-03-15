package blInteract.userInteract;

import java.util.ArrayList;
import vo.UserVO;

public interface GetUserService {
	public ArrayList<UserVO> getManager();
	public ArrayList<UserVO> getWarekeeper();
	public ArrayList<UserVO> getFianceStaff();
	public ArrayList<UserVO> getAdmin();
	public ArrayList<UserVO> getSalesman();
}
