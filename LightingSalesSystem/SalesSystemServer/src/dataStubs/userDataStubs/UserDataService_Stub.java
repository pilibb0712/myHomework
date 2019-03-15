package dataStubs.userDataStubs;

import dataService.userDataService.UserDataService;
import po.UserPO;
import vo.UserVO;

public class UserDataService_Stub implements UserDataService{

	@Override
	public UserPO getUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(UserVO user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String Id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(UserVO user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(String userId, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerUser(String userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidUser(String userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
