package blDrivers.adminBlInteractDrivers;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import assistant.exception.Myexception;
import assistant.type.UserPositionEnum;
import blImpl.adminBl.AdminBlController;
import blService.adminBlService.AdminBlService;
import vo.UserVO;

public class AdminBlService_Driver {
	private AdminBlService service =  new AdminBlController();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void driveAddUser() throws RemoteException, Myexception{
		UserVO user = new UserVO();
		user.setAge(18);
		user.setDescription("新建用户");
		user.setName("张傲");
		user.setPosition(UserPositionEnum.MANAGER);
		String userId = service.addUser(user);
		assertEquals(userId,service.getUser(userId).getId());
	}
	
	@Test
	public void driveGetUser() throws RemoteException, Myexception{
		String userId = "12345";
		UserVO userVO = service.getUser(userId);
		assertEquals("12345", userVO.getId());
	}
	
	@Test 
	public void driveGetUserList() throws RemoteException, Myexception{
		ArrayList<UserVO> users = service.getUserList();
		for(UserVO user: users){
			System.out.println(user.getName()+" " +user.getId());
		}
	}
	
	@Test 
	public void driveUpdateUser() throws RemoteException, Myexception{
		UserVO user = new UserVO();
		user.setAge(18);
		user.setDescription("新建用户");
		String userId = "12345";
		user.setId(userId);
		user.setName("张傲");
		user.setPosition(UserPositionEnum.SALESMAN);
		service.updateUser(user);
		assertEquals(UserPositionEnum.SALESMAN, service.getUser(userId).getPosition());
	}
	
	@Test
	public void driveDeleteUser() throws RemoteException, Myexception{
		assertEquals(true, service.deleteUser("12345"));
	} 
	
	
}
