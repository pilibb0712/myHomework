package ui.adminUi;

import vo.UserVO;

public interface AdminForUpdateUserController {
	public void updateUser(UserVO user,UserVO oldUser);
}
