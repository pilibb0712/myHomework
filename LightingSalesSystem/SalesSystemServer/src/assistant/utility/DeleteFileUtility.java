package assistant.utility;

import java.io.File;

/**
 * @author zhangao 161250193
 * @version 2017.11.22
 * 删除文件的utility辅助类
 */

public class DeleteFileUtility {
	
	/**
	 * 删除文件。<br/>
	 * @param String 要删除的文件的地址
	 * @return boolean 是否删除成功
	 */
	public static boolean deleteFile(String path){
		File file = new File(path);
		return file.delete();
	}
}
