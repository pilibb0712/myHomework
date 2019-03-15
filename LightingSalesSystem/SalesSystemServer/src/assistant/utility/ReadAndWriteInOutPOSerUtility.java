package assistant.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import po.InOutDetailsPO;
import po.InitialInfoPO;

/**
 * 序列化和反序列化与账单有关的PO
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class ReadAndWriteInOutPOSerUtility {
	
	public static boolean writeInitialInfoObj(InitialInfoPO toWrite, String path){
		ObjectOutputStream os;
		
		try {
			os=new ObjectOutputStream(new FileOutputStream(path));
			os.writeObject(toWrite);
			os.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
			
		}
	}
	
	public static InitialInfoPO readInitialInfoObj(String path){
		try {
			ObjectInputStream is=new ObjectInputStream(new FileInputStream(path));
			InitialInfoPO po=(InitialInfoPO) is.readObject();
			is.close();
			return po;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static boolean writeDetailsObj(InOutDetailsPO toWrite, String path){
		try {
			ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream(path));
			os.writeObject(toWrite);
			os.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
			
		}
	}
	
	public static InOutDetailsPO readDetailsObj(String path){
		try {
			ObjectInputStream is=new ObjectInputStream(new FileInputStream(path));
			InOutDetailsPO po=(InOutDetailsPO) is.readObject();
			is.close();
			return po;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
