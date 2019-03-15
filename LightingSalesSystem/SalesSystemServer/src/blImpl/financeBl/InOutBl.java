package blImpl.financeBl;

import dataImpl.financeData.InOutData;
import dataService.financeDataService.InOutDataService;
import po.InOutDetailsPO;
import po.InitialInfoPO;
import po.SingleEntryPO;


/**
 * 账单管理相关操作具体的逻辑实现
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class InOutBl {
	
	private static InOutBl inOutBl=new InOutBl();//单件实例
	
	private InOutDataService inOutData=new InOutData();
	
	private InOutBl(){
		
	}
	
	public static InOutBl getInstance(){//对外提供单件实例
		return inOutBl;
	}
	
	//保存新建的账单
	public boolean saveInOut(InitialInfoPO toSave){
		if(inOutData.insert(toSave))
			return true;
		else
			return false;
	}
	
	//获取期初信息
	public InitialInfoPO getInitialInformation(String inOutYear){
		InitialInfoPO po=inOutData.readInitialInfo(inOutYear);
		return po;
	}
	
	//获取账单明细
	public InOutDetailsPO getInOutDetails(String inOutYear){
		InOutDetailsPO po=inOutData.readDetails(inOutYear);
		return po;
	}
	
	//在一套账单上增加条目
	public boolean addEntry(SingleEntryPO toAdd){
		if(inOutData.insert(toAdd))
			return true;
		else
			return false;
	}
	
	//获取账单列表
	public String[] getInOutList(){
		String[] list=inOutData.readInOutList();
		return list;
	}
}
