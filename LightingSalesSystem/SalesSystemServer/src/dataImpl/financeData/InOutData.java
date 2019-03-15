package dataImpl.financeData;

import java.io.File;
import java.util.ArrayList;

import assistant.utility.ReadAndWriteInOutPOSerUtility;
import dataService.financeDataService.InOutDataService;
import po.InOutDetailsPO;
import po.InitialInfoPO;
import po.SingleEntryPO;

/**
 * 数据库账单管理实现
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class InOutData implements InOutDataService{
	
	private static final String DETAILS_ROOT_PATH="data"+File.separator+"inOuts"+File.separator+"details";
	private static final String INITIALINFO_ROOT_PATH="data"+File.separator+"inOuts"+File.separator+"initialInformation";

	@Override
	public String[] readInOutList() {
		File inOutRoot=new File(INITIALINFO_ROOT_PATH);
		
		File[] fileList=inOutRoot.listFiles();
		String[] list=new String[fileList.length];
		for(int i=0;i<list.length;i++)
			list[i]=fileList[i].getName();
		
		return list;
	}

	@Override
	public boolean insert(SingleEntryPO toInsert) {
		String inOutYear=toInsert.getBelongedBill();
		
		String path=DETAILS_ROOT_PATH+File.separator+inOutYear;
		InOutDetailsPO details=ReadAndWriteInOutPOSerUtility.readDetailsObj(path);
		ArrayList<SingleEntryPO> entries=details.getEntries();
		entries.add(toInsert);
		
		File file=new File(path);
		file.delete();
		
		if(ReadAndWriteInOutPOSerUtility.writeDetailsObj(details, path))
			return true;
		else
			return false;
	}

	@Override
	public boolean insert(InitialInfoPO toInsert) {
		String initialInfoPath=INITIALINFO_ROOT_PATH+File.separator+toInsert.getYear();
		String detailsPath=DETAILS_ROOT_PATH+File.separator+toInsert.getYear();
		
		InOutDetailsPO details=new InOutDetailsPO();
		
		if(ReadAndWriteInOutPOSerUtility.writeInitialInfoObj(toInsert, initialInfoPath)
				&&ReadAndWriteInOutPOSerUtility.writeDetailsObj(details, detailsPath))
			return true;
		else
			return false;
	}

	@Override
	public InitialInfoPO readInitialInfo(String inOutYear) {
		String path=INITIALINFO_ROOT_PATH+File.separator+inOutYear;
		InitialInfoPO initial=ReadAndWriteInOutPOSerUtility.readInitialInfoObj(path);
		return initial;
	}

	@Override
	public InOutDetailsPO readDetails(String inOutYear) {
		String path=DETAILS_ROOT_PATH+File.separator+inOutYear;
		InOutDetailsPO details=ReadAndWriteInOutPOSerUtility.readDetailsObj(path);
		return details;
	}
}
