package blInteract.userInteract;

import blImpl.adminBl.GetUserBl;
import blImpl.adminBl.GetUserInfoByUserNameBl;

public class GetUserServiceFactory {
	public GetUserService getGetUserService(){
		GetUserService service = new GetUserBl();
		return service;
	}
	
	public GetUserInfoByUserNameService getUserInfoByUserNameService(){
		GetUserInfoByUserNameService service = new GetUserInfoByUserNameBl();
		return service;
	}
}
