package dataImpl.billData;

import java.io.File;

import assistant.utility.DeleteFileUtility;
import dataService.billDataService.DeleteDraftBillDataService;

/**
 * @author zhangao 161250193
 * @version 2017.11.22
 * 草稿单据的删除
 * 用于废弃的草稿状态单据的删除
 */

public class DeleteDraftBillData implements DeleteDraftBillDataService {
	
	private static final String ROOT_PATH = "data"+File.separator+"bill"+File.separator;//根目录地址
	private static final String STATE_PATH = "draft"+File.separator;//对应的状态文件夹地址
	private static final String FILE_EXTENSION = ".ser";
	
	@Override
	public boolean deleteDraftBill(String billName,String billId) {
		String path = ROOT_PATH +billName + File.separator + STATE_PATH + billId + FILE_EXTENSION;
	//	System.out.println(path);
		return DeleteFileUtility.deleteFile(path);//删除文件
	}

}
