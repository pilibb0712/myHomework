package assistant.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import po.PromotionStrategyPO;

/**
 * @author zhangao 161250193
 * @version 2017.11.24
 * 读写PromotionPO的ser文件的utility辅助类
 */

public class ReadAndWritePromotionPOSerUtility {
	
	/**
	 * 写promotion的ser文件。<br/>
	 * @param PromotionStrategyPO promotion 需要写的促销策略 
	 * @return boolean 是否写文件成功
	 */
	public static boolean writeObj(PromotionStrategyPO promotion,String path){
		ObjectOutputStream oStream ;
//		System.out.println(path);
		try {
			oStream = new ObjectOutputStream(new FileOutputStream(path));
			oStream.writeObject(promotion);
			oStream.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 读取一个促销策略。<br/>
	 * @param path 要读取的促销策略路径
	 * @return PromotionStrategyPO读取的促销策略
	 */
	public static PromotionStrategyPO readObj(String path){
//		System.out.println("path: "+path);
		PromotionStrategyPO promotion= null;
		ObjectInputStream oInputStream;
		try {
			oInputStream = new ObjectInputStream(new FileInputStream(path));
			promotion = (PromotionStrategyPO)oInputStream.readObject();
			oInputStream.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return promotion;
	}
}
