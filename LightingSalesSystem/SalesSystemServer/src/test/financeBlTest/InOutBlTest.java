package test.financeBlTest;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;
import blImpl.financeBl.InOutBl;
import po.InitialInfoPO;
import po.SingleBankAccountInitialInfoPO;
import po.SingleCommodityInitialInfoPO;
import po.SingleCustomerInitialInfoPO;
import po.SingleEntryPO;


public class InOutBlTest {
	InOutBl bl=InOutBl.getInstance();

	@Test
	public void driveSaveInOut(){
		SingleCommodityInitialInfoPO com1=new SingleCommodityInitialInfoPO(
				"钓鱼灯","卧室钓鱼灯","123-wsdyd","2000","3000","0","0"
				);
		SingleCustomerInitialInfoPO cus1=new SingleCustomerInitialInfoPO(
				"VIP","张三","123456789","0","3000"
				);
		SingleBankAccountInitialInfoPO bank1=new SingleBankAccountInitialInfoPO(
				"account1","45615.5"
				);
		
		ArrayList<SingleCommodityInitialInfoPO> comList=new ArrayList<SingleCommodityInitialInfoPO>();
		comList.add(com1);
		
		ArrayList<SingleCustomerInitialInfoPO> cusList=new ArrayList<SingleCustomerInitialInfoPO>();
		cusList.add(cus1);
		
		ArrayList<SingleBankAccountInitialInfoPO> bankList=new ArrayList<SingleBankAccountInitialInfoPO>();
		bankList.add(bank1);
		
		InitialInfoPO initialInfo=new InitialInfoPO(comList, cusList, bankList);
		initialInfo.setYear("1990");
		
		assertEquals(true, bl.saveInOut(initialInfo));
		
	}
	
	@Test
	public void driveGetInitialInformation(){
		InitialInfoPO po=bl.getInitialInformation("1990");
		assertEquals("1990", po.getYear());
	}
	
	@Test
	public void driveAddEntry(){
		SingleEntryPO toAdd=new SingleEntryPO("1990","1990-12-4","销售","+4000");
		assertEquals(true, bl.addEntry(toAdd));
	}
	
	@Test
	public void driveGetInOutDetails(){
		//assertEquals("销售",bl.getInOutDetails("1990").getEntries().get(0).getEvent());
	}

	@Test
	public void driveGetInOutList(){
		String[] list=bl.getInOutList();
		assertEquals("1990", list[0]);
	}
}
