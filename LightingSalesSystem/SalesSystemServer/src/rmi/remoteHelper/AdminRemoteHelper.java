package rmi.remoteHelper;

import rmi.register.adminRegister.AdminBlRegister;

public class AdminRemoteHelper {
	private AdminBlRegister adminBlRegister = new AdminBlRegister();
	
	public AdminRemoteHelper(){
		adminBlRegister.register();
	}
}
