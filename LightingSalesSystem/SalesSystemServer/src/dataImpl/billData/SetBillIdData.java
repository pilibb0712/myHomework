package dataImpl.billData;

import java.io.File;

import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import assistant.utility.ReadAndWriteTxtUtility;
import dataService.billDataService.SetBillIdDataService;
import po.BillPO;

public class SetBillIdData implements SetBillIdDataService{

	private static final String ROOT_PATH = "data"+File.separator+"bill"+File.separator;//根目录地址
	private static final String STATE_PATH = "tbd"+File.separator;//对应的状态文件夹地址
	public static final String CREATE_RECORD = "create_record.txt" ;
	private static final int BILL_ID_LENGTH=5;//单据编号的长度，现在为5.即KCZSD-20171108-00001中00001部分

	/**
	 * 更新当前的bill生成record。<br/>
	 * @param String path 要存的文件的位置
	 * @param String content 要追加的记录
	 * @return boolean 是否更新成功
	 */
	public boolean updateRecord(BillPO bill){
		BillCategoryEnum billCategoryEnum = bill.getCategoryEnum();
		String path = ROOT_PATH + billCategoryEnum.toString()+ File.separator + STATE_PATH;//更新记录的位置
		String record = bill.getId();
		path += CREATE_RECORD;
		record += System.lineSeparator();
		return	ReadAndWriteTxtUtility.writeTxtAppend(path, record);
	}


	/**
	 * 生成bill的Id。<br/>
	 * @param GiftBillPO bill 需要设定Id的单据
	 * @return void
	 */
	public void setBillId(BillPO bill){
		BillCategoryEnum category = bill.getCategoryEnum();
		String path =  ROOT_PATH + category.toString()+File.separator+STATE_PATH+File.separator ;
		path += CREATE_RECORD;
//		File dir = new File(path);
//		String [] filesName = dir.list();//得到当前文件名

		String content = ReadAndWriteTxtUtility.readTxt(path);//读取创建文件记录
		String [] filesName = content.split(System.lineSeparator());//得到当前文件名

		int newId=1;//生成新Id的编号数值
		Date createDate = bill.getCreateDate();
		String todayDateString=createDate.getBillIdPart();//得到今天的Id应有的日期部分

		for(int i=0;i<filesName.length;i++){
			String fName = filesName[i];
			if(!fName.contains("-")){continue;}
			String dateString = fName.split("-")[1];//得到KCZSD-20171108-00001中的日期部分,即20171108
			if(dateString.equals(todayDateString)){newId++;}
		}

		//生成当前Id的String格式
		String newIdString = ""+newId;
		int len = (BILL_ID_LENGTH-newIdString.length());
		for(int i=0;i<len;i++){
			newIdString="0".concat(newIdString);
		}

		//设置当前单据Id
		bill.setId(category.toBillIdPart()+"-"+todayDateString+"-"+newIdString);
	}
}
