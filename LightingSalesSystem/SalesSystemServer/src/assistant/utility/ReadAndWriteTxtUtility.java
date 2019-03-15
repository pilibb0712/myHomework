package assistant.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhangao 161250193
 * @version 2017.11.8
 * 读写txt文件的utility辅助类
 */
public class ReadAndWriteTxtUtility {
	/**
	 * 读取txt文件的内容。<br/>
	 * @param String path 读取的文件的路径
	 * @return String 文件的内容
	 */
	public static String readTxt(String path){
		File file = new File(path);
		String content = "";
		try{
			InputStreamReader read=new InputStreamReader(new FileInputStream(file));
			BufferedReader bf=new BufferedReader(read);
			String line=null;
			while((line=bf.readLine())!=null){
				content+=line+System.lineSeparator();
			}
			bf.close();
			return content;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 写txt文件。<br/>
	 * @param String path 要写的文件的路径
	 * @param String content 要写的文件的内容
	 * @return boolean 是否写文件成功
	 */
	public static boolean writeTxtAppend(String path,String content){
		File file;
		file = new File(path);
		try {
			FileWriter fw = new FileWriter(file, true);
			fw.write(content);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
