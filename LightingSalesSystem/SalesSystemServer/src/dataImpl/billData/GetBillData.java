package dataImpl.billData;

import java.io.File;
import java.util.ArrayList;

import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.utility.ReadAndWriteBillPOSerUtility;
import dataService.billDataService.GetBillDataService;
import po.BillPO;

/**
 * @author zhangao 161250193
 * @version 2017.11.19
 * 得到单据
 */

public class GetBillData implements GetBillDataService{

	private static final String ROOT_PATH = "data"+File.separator+"bill"+File.separator;//根目录地址
//	private static final String STATE_PATH = "tbd"+File.separator;//对应的状态文件夹地址
	
	
	/**
	 * 根据单据名称和Id读取一张单据。<br/>
	 * @param billName 要读取的单据名称
	 * @param billId 要读取的单据Id
	 * @return BillPo读取的单据
	 */
	@Override
	public BillPO getBillByNameAndId(String billName, String billId) {
		BillPO billPO=null;
		String path = ROOT_PATH+billName+File.separator;
		for(BillStateEnum state: BillStateEnum.values()){
			String filePath = path + state.toString() + File.separator + billId+".ser";
			billPO = readObj(filePath);
			if(billPO!=null){return billPO;}
		}
		return billPO;
	}
	
	/**
	 * 根据单据状态读取一系列单据。<br/>
	 * @param billState 要读取的单据状态
	 * @return ArrayList<BillPO>读取的单据列表
	 */
	@Override
	public ArrayList<BillPO> getBillListByState(String billState) {
		ArrayList<BillPO> bills = new ArrayList<BillPO>();
		String path = ROOT_PATH;
		for(BillCategoryEnum category:BillCategoryEnum.values()){
			ArrayList<BillPO> categoryBills = getBillListByStateAndName(billState,category.toString());
			bills.addAll(categoryBills);
		}
		return bills;
	}

	/**
	 * 根据单据名称和状态读取一系列单据。<br/>
	 * 专门用于billState部分
	 * @param billState 要读取的单据状态
	 * @param billName 要读取的单据名称
	 * @return ArrayList<BillPO>读取的单据列表
	 */
	@Override
	public ArrayList<BillPO> getBillListByStateAndName(String billState, String billName) {
		ArrayList<BillPO> bills = new ArrayList<BillPO>();
		String path = ROOT_PATH+billName+File.separator+billState+ File.separator;//文件夹的路径，读取该文件夹下所有文件
		File dir = new File(path);
		String [] filesName = dir.list();//得到当前文件名
		//读取这些文件
		for(int i=0;i<filesName.length;i++){
			if(!filesName[i].contains("-")){continue;}
			bills.add(readObj(path+filesName[i]));
		}
		return bills;
	}
	

	/**
	 * 读取一张单据。<br/>
	 * @param path 要读取的单据路径
	 * @return BillPo读取的单据
	 */
	private BillPO readObj(String path){
		return ReadAndWriteBillPOSerUtility.readObj(path);
	}
	
}
