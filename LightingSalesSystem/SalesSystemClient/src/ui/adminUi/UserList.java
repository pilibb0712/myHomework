package ui.adminUi;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.exception.Myexception;
import blService.adminBlService.AdminBlService;
import blStubs.adminBlStubs.AdminBlService_Stub;
import rmi.remoteHelper.RemoteHelperFactory;
import vo.UserVO;

public class UserList {
	private ArrayList<UserVO> users;
	private AdminBlService service = RemoteHelperFactory.getAdminRemoteHelper().getAdminBlService();
	public UserList() throws RemoteException{
		try {
			users = service.getUserList();
		} catch (Myexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public boolean addUser(UserVO user){
		String id = null;
		try {
			try {
				id = service.addUser(user);
			} catch (Myexception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
		user.setId(id);
		users.add(user);
		return true;
	}
	
	public boolean updateUser(UserVO user,UserVO oldUser){
		try {
			service.updateUser(user);
		} catch (Myexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (RemoteException e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}
	
	public boolean deleteUser(UserVO user){
		try {
			service.deleteUser(user.getId());
		}catch (Myexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		users.remove(user);
		return true;
	}
	
	public ArrayList<UserVO> getUserList(){
		return users;
	}
	
	public ArrayList<UserVO> searchByKeyWords(String key){
		ArrayList<UserVO> targetUsers = new ArrayList<>();
		if(key==null||key.trim().equals("")){
			targetUsers.addAll(users);
			return targetUsers;
		}
		if(key.matches("[0-9]*")){
			System.out.println("id");
			targetUsers = searchById(key);
			return targetUsers;
		}
		targetUsers = searchByName(key);
		return targetUsers;
	}
	
	private ArrayList<UserVO> searchByName(String name){
		ArrayList<UserVO> targetUsers = new ArrayList<>();
		ArrayList<UserVO> firstNameTargetUsers = new ArrayList<>();
		//匹配到唯一的名字
		for(UserVO user:users){
			String userName = user.getName();
			String reg = userName.substring(1);
			char begin = userName.charAt(0);
			if(name.contains(begin+"")){
				firstNameTargetUsers.add(user);
			}
			if(name.contains(reg)){
				targetUsers.add(user);
			}
			if(userName.equals(name.trim())){
				targetUsers.clear();
				targetUsers.add(user);
				return targetUsers;
			}
		}
		if(firstNameTargetUsers.size()>0){
			return firstNameTargetUsers;
		}
		return targetUsers;
	}
	
	private ArrayList<UserVO> searchById(String id){
		ArrayList<UserVO> targetUsers = new ArrayList<>();
		for(UserVO user: users){
			String userId = user.getId();
			if(userId.contains(id)){
				targetUsers.add(user);
			}
		}
		return targetUsers;
	}
}
