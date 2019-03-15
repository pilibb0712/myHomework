package assistant.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import po.BillPO;

/**
 * @author zhangao 161250193
 * @version 2017.11.8
 * 读写BillPO的ser文件的utility辅助类
 */
public class ReadAndWriteBillPOSerUtility {
	/**
	 * 写bill的ser文件。<br/>
	 * @param GiftBillPO bill 需要新建的单据 
	 * @return boolean 是写文件成功
	 */
	public static boolean writeObj(BillPO bill,String path){
		ObjectOutputStream oStream ;
//		System.out.println(path);
		try {
			oStream = new ObjectOutputStream(new FileOutputStream(path));
			oStream.writeObject(bill);
			oStream.close();
			return true;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * 读取一张单据。<br/>
	 * @param path 要读取的单据路径
	 * @return BillPo读取的单据
	 */
	public static BillPO readObj(String path){
//		System.out.println("path: "+path);
		File file = new File(path);
		if(!file.exists()){return null;}
		BillPO bill= null;
		ObjectInputStream oInputStream;
		try {
			oInputStream = new ObjectInputStream(new FileInputStream(path));
			bill = (BillPO)oInputStream.readObject();
			oInputStream.close();
		}catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			//e.printStackTrace();
			return null;
		}
		return bill;
	}
}
