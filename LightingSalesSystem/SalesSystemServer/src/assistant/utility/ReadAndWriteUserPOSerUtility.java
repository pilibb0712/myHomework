package assistant.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import po.UserPO;

/**
 * @author zhangao 161250193
 * @version 2017.12.5
 * 读写UserPO的ser文件的utility辅助类
 */
public class ReadAndWriteUserPOSerUtility {
	/**
	 * 写用户PO。<br/>
	 * @param path 要写的用户PO的路径
	 * @return boolean是否写成功
	 */
	public static boolean writeObj(UserPO user,String path){
		ObjectOutputStream oStream ;
//		System.out.println(path);
		try {
			oStream = new ObjectOutputStream(new FileOutputStream(path));
			oStream.writeObject(user);
			oStream.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 读用户PO。<br/>
	 * @param path 要读的用户PO的路径
	 * @return UserPO读取的用户PO
	 */
	public static UserPO readObj(String path) {
//		System.out.println("path: "+path);
		UserPO user = null ;
		ObjectInputStream oInputStream;
		try {
			oInputStream = new ObjectInputStream(new FileInputStream(path));
			user = (UserPO)oInputStream.readObject();
			oInputStream.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
}
