package assistant.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import po.BankAccountPO;

/**
 * 序列化和反序列化银行账户对象的类
 * @author guxinyu
 * @version 2017.12.2
 *
 */
public class ReadAndWriteBankAccountPOSerUtility {
	/**
	 * 序列化保存银行账户对象
	 * @param toWrite 要保存的对象
	 * @param path 要保存的对象的路劲
	 * @return 是否保存成功
	 */
	public static boolean writeObj(BankAccountPO toWrite, String path){
		ObjectOutputStream os;
		
		try{
			os=new ObjectOutputStream(new FileOutputStream(path));
			os.writeObject(toWrite);
			os.close();
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 反序列化读取一个银行账户对象
	 * @param path 要读的对象的路径
	 * @return 被读取的对象
	 */
	public static BankAccountPO readObj(String path){
		BankAccountPO account=null;
		ObjectInputStream is;
		
		try{
			is=new ObjectInputStream(new FileInputStream(path));
			account=(BankAccountPO)is.readObject();
			is.close();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return account;
	}
}
