package dataImpl.userData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.exception.CantFindUserException;
import assistant.exception.Myexception;
import assistant.utility.DeleteFileUtility;
import assistant.utility.ReadAndWriteTxtUtility;
import assistant.utility.ReadAndWriteUserPOSerUtility;
import dataService.userDataService.UserDataService;
import po.UserPO;

/**
 * 用户注册和登录
 * @author 王宁一 161250140
 * @version 2017.11.23
 */
public class UserData implements UserDataService{
	
	private static final String ROOT_PATH = "data" + File.separator + "user" + File.separator;//根路径
	private static final String RECORD_PATH="data"+File.separator+"user"+File.separator+"record.txt";//存放用户名和密码的文件
	
	/**
	 * 查询用户人员信息
	 * @param userId 用户的账户Id
	 * @return UserPO 得到的用户信息PO
	 */
	@Override
	public UserPO getUser(String userId) throws RemoteException,Myexception{
		String path = ROOT_PATH + userId;
		UserPO po = ReadAndWriteUserPOSerUtility.readObj(path);
		if(po==null){throw new CantFindUserException();}
		return po;
	}

	/**
	 * 得到所有人员信息
	 * @param userId 用户的账户Id
	 * @return UserPO 得到的用户信息PO
	 */
	@Override
	public ArrayList<UserPO> getUserList() throws RemoteException{
		ArrayList<UserPO> userPOs = new ArrayList<>();
		String path = ROOT_PATH;
		File file = new File(path);
		String [] usersNames = file.list();
		for(String userPath : usersNames){
			if("record.txt".equals(userPath)){continue;}
			String filePath = path+ userPath;
			UserPO po = ReadAndWriteUserPOSerUtility.readObj(filePath);
			userPOs.add(po);
		}
		return userPOs;
	}
	
	/**
	 * 新增用户人员信息
	 * @param UserPO user 新增的用户信息
	 * @return boolean 是否新增成功
	 * @throws Myexception 
	 */
	@Override
	public boolean addUser(UserPO user) throws RemoteException{
		try {
			adminRegister(user.getId());
		} catch (Myexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = ROOT_PATH + user.getId();
		return ReadAndWriteUserPOSerUtility.writeObj(user, path);
	}

	/**
	 * 删除用户信息
	 * @param String Id 要删除的用户信息
	 * @return boolean 是否删除成功
	 */
	@Override
	public boolean deleteUser(String Id) throws RemoteException{
		String path = ROOT_PATH + Id;
		//待写 删除用户名和密码
		return DeleteFileUtility.deleteFile(path);
	}

	/**
	 * 更新用户信息
	 * @param UserPO user要更新的用户信息
	 * @return boolean 是否更新成功
	 * @throws Myexception 
	 */
	@Override
	public boolean updateUser(UserPO user) throws RemoteException, Myexception{
		String path = ROOT_PATH + user.getId();
		//不存在该用户不能够更新
		//if(getUser(path)==null){
		//	return false;
		//}
		return addUser(user);
	}

	/**
	 * 工作人员更改密码（非第一次登录）
	 * @see dataService.userDataService.UserDataService#changePassword(java.lang.String, java.lang.String, java.lang.String)
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean changePassword(String userId, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		String waitToCompare=userId+">>"+oldPassword;
		String waitToUpdate=userId+">>"+newPassword;
		
		if(!fetch(userId, oldPassword)) {//旧密码不正确
			return false;
		}
		
		if(oldPassword.equals(newPassword)) {//新密码和旧密码相同
			return false;
		}
		
		File file=new File(RECORD_PATH);
		ArrayList<String> content=new ArrayList<String>();
		
		try {
			FileInputStream iStream=new FileInputStream(file);
			BufferedReader bf=new BufferedReader(new InputStreamReader(iStream));
			String str=null;
			
			while((str=bf.readLine())!=null) {
				if(!str.equals(waitToCompare)) {
					content.add(str);
				}
			}
			iStream.close();
			bf.close();
			
			FileWriter fw=new FileWriter(file);
			fw.write("");
			fw.flush();
			fw.close();
			
			content.add(waitToUpdate);
			String input="";
			for(int i=0;i<content.size();i++) {
				input=content.get(i)+System.lineSeparator();
				ReadAndWriteTxtUtility.writeTxtAppend(RECORD_PATH, input);
			}
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 工作人员注册：默认密码和用户名相同，但是不能登录，必须更改密码
	 * @see dataService.userDataService.UserDataService#registerUser(java.lang.String, java.lang.String)
	 * @param userId
	 * @param password
	 * @return boolean, true表示操作成功, 随后需要界面跳转至更改密码
	 */
	@Override
	public boolean registerUser(String userId, String password) {
		// TODO Auto-generated method stub
		if(!userId.equals(password)) {
			return false;
		}
		
		boolean valid=fetch(userId, password);
		if(valid) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 判断是否合法用户
	 * @see dataService.userDataService.UserDataService#isValidUser(java.lang.String, java.lang.String)
	 * @param userId
	 * @param password
	 * @return boolean, true表示操作成功
	 */
	@Override
	public boolean isValidUser(String userId, String password) {
		// TODO Auto-generated method stub
		if(userId.equals(password)) {//用户名和密码相同 一定是错的
			return false;
		}
		
		boolean valid=fetch(userId, password);
		if(valid) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 单纯判断记录中是否有用户以及用户密码是否正确
	 * @param userId
	 * @param password
	 * @return
	 */
	private boolean fetch(String userId, String password) {
		
		File file=new File(RECORD_PATH);
		try {
			FileInputStream inputStream=new FileInputStream(file);
			BufferedReader bf=new BufferedReader(new InputStreamReader(inputStream));
			
			String str=null;
			String waitToCompare=userId+">>"+password;
			while((str=bf.readLine())!=null) {
				if(str.equals(waitToCompare)) {
					inputStream.close();
					bf.close();
					return true;
				}
			}
			inputStream.close();
			bf.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 管理员注册 新用户的账号密码
	 * @see dataService.userDataService.UserDataService#adminRegister(java.lang.String, java.lang.String)
	 * @param userId
	 * @param password
	 * @return
	 * @throws RemoteException
	 * @throws Myexception
	 */
	@Override
	public boolean adminRegister(String userId) throws RemoteException, Myexception {
		try {
			String content=userId+">>"+userId+System.lineSeparator();
			ReadAndWriteTxtUtility.writeTxtAppend(RECORD_PATH, content);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	
	/*public static void main(String[] args) {
		UserData data=new UserData();
		try {
			data.adminRegister("00000");
			data.adminRegister("00001");
			data.adminRegister("00002");
			data.adminRegister("00003");
			data.adminRegister("00004");
			
			UserPO  user0=new UserPO();
			user0.setId("00000");
			user0.setName("an_admin");
			user0.setPosition(UserPositionEnum.ADMIN);
			UserPO user1=new UserPO();
			user1.setId("00001");
			user1.setName("a_manager");
			user1.setPosition(UserPositionEnum.MANAGER);
			UserPO user2=new UserPO();
			user2.setId("00002");
			user2.setName("a_warekeeper");
			user2.setPosition(UserPositionEnum.WAREKEPPER);
			UserPO user3=new UserPO();
			user3.setId("00003");
			user3.setName("a_finance");
			user3.setPosition(UserPositionEnum.FINANCE);
			UserPO user4=new UserPO();
			user4.setId("00004");
			user4.setName("a_salseman");
			user4.setPosition(UserPositionEnum.SALESMAN);
			
			data.addUser(user0);
			data.addUser(user1);
			data.addUser(user2);
			data.addUser(user3);
			data.addUser(user4);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Myexception e) {
			e.printStackTrace();
		}
		
		
	}*/
}
