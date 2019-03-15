package blMocks.financeBlMocks;

import java.util.ArrayList;

import blImpl.financeBl.InOutBl;
import vo.InOutDetailsVO;
import vo.InitialInfoVO;
import vo.SingleEntryVO;

public class MockInOut extends InOutBl{
	public boolean saveInOut(InitialInfoVO toSave){
		System.out.println("Bill established!");
		return true;
	}
	
	public InitialInfoVO getInitialInformation(String inOutYear){
		InitialInfoVO toReturn=new InitialInfoVO("大灯类","床头大灯",
				"xxx-yyyyy",4500.0,5000.0,0.0,0.0,"VIP","李四",
				"185851321",0.0,5000.0,"account1",45651.4);
		
		return toReturn;
	}
	
	public InOutDetailsVO getInOutDetails(String inOutYear){
		SingleEntryVO entry1=new SingleEntryVO("2017","售出床头大灯","+5000.0");
		SingleEntryVO entry2=new SingleEntryVO("2017","进货走廊灯","-3000.0");
		
		ArrayList<SingleEntryVO> de=new ArrayList<SingleEntryVO>();
		de.add(entry1);
		de.add(entry2);
		InOutDetailsVO details=new InOutDetailsVO(de);
		
		return details;
	}
	
	public boolean addEntry(SingleEntryVO toAdd){
		System.out.println("Is added.");
		return true;
	}
	
	public String[] getInOutList(){
		String[] list={"2010","2011","2012","2013","2014","2015","2016","2017"};
		
		return list;
	}
}
