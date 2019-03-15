package blDrivers.financeBlDrivers;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blImpl.financeBl.InOutBlController;
import blInteract.financeInteract.AutoAddEntryService;
import blService.financeBlService.InOutBlService;
import vo.InOutDetailsVO;
import vo.InitialInfoVO;
import vo.SingleBankAccountInitialInfoVO;
import vo.SingleCommodityInitialInfoVO;
import vo.SingleCustomerInitialInfoVO;
import vo.SingleEntryVO;

/**
 * ’Àµ•π‹¿Ì¬ﬂº≠≤„«˝∂Ø
 * @author guxinyu
 * @version 2017.12.4
 * 
 */
public class InOutBlService_Driver {
	
	private static InOutBlService bl=new InOutBlController();
	private static AutoAddEntryService auto=new InOutBlController();
	
	public static void main(String[] args){
		try {
			//test_newInOut();
			test_autoAddEntry();
			//test_viewInitialInformation();
			test_viewInOutDetails();
			test_viewInOutList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void test_newInOut() throws RemoteException{
		SingleCommodityInitialInfoVO com1=new SingleCommodityInitialInfoVO(
				"µˆ”„µ∆","Œ‘ “µˆ”„µ∆","123-wsdyd","2000","3000","0","0"
				);
		SingleCustomerInitialInfoVO cus1=new SingleCustomerInitialInfoVO(
				"VIP","’≈»˝","123456789","0","3000"
				);
		SingleBankAccountInitialInfoVO bank1=new SingleBankAccountInitialInfoVO(
				"account1","45615.5"
				);
		
		ArrayList<SingleCommodityInitialInfoVO> comList=new ArrayList<SingleCommodityInitialInfoVO>();
		comList.add(com1);
		
		ArrayList<SingleCustomerInitialInfoVO> cusList=new ArrayList<SingleCustomerInitialInfoVO>();
		cusList.add(cus1);
		
		ArrayList<SingleBankAccountInitialInfoVO> bankList=new ArrayList<SingleBankAccountInitialInfoVO>();
		bankList.add(bank1);
		
		InitialInfoVO initialInfo=new InitialInfoVO(comList, cusList, bankList);
		initialInfo.setYear("2017");
		
		
			if(bl.newInOut(initialInfo))
				System.out.println("A new InOut is created successfully.");
			else
				System.out.println("Fail to created a new InOut.");
		
	}
	
	public static void test_viewInitialInformation() throws RemoteException{
		InitialInfoVO initial=bl.viewInitialInformation("2017");
		
		ArrayList<SingleCommodityInitialInfoVO> comList=initial.getCommodityInfos();
		ArrayList<SingleCustomerInitialInfoVO> cusList=initial.getCustomerInfos();
		ArrayList<SingleBankAccountInitialInfoVO> bankList=initial.getBankAccountInfos();
		
		for(int i=0;i<comList.size();i++){
			SingleCommodityInitialInfoVO each=comList.get(i);
			
			System.out.println(each.getCommodityClass()+","
					+each.getCommodityName()+","
					+each.getCommodityType()+","
					+each.getPurchasePrice()+","
					+each.getSellingPrice()+","
					+each.getRecentPurchasePrice()+","
					+each.getRecentSellingPrice()
					);
		}
		
		for(int i=0;i<cusList.size();i++){
			SingleCustomerInitialInfoVO each=cusList.get(i);
			
			System.out.println(each.getCustomerClass()+","
					+each.getCustomerName()+","
					+each.getCustomerContact()+","
					+each.getCustomerPay()+","
					+each.getCustomerReceive()+","
					);
		}
		
		for(int i=0;i<bankList.size();i++){
			SingleBankAccountInitialInfoVO each=bankList.get(i);
			
			System.out.println(each.getBankAccountName()+","
					+each.getBankAccountSum()+","
					);
		}
	}
	
	public static void test_viewInOutDetails() throws RemoteException{
		InOutDetailsVO details=bl.viewInOutDetails("2017");
		ArrayList<SingleEntryVO> entries=details.getEntries();
		
		for(int i=0;i<entries.size();i++){
			SingleEntryVO each=entries.get(i);
			System.out.println(
					each.getBelongedBill()+","
					+each.getDate()+","
					+each.getEvent()+","
					+each.getAmount()
					);
		}
	}
	
	public static void test_autoAddEntry() throws RemoteException{
		SingleEntryVO toAdd=new SingleEntryVO("2017","2017-12-4"," €≥ˆµˆ”„µ∆","+4000");
		if(auto.autoAddEntry(toAdd))
			System.out.println("An entry is added successfully.");
		else
			System.out.println("Fail to add an entry.");
	}
	
	public static void test_viewInOutList() throws RemoteException{
		String[] list=bl.viewInOutList();
		for(int i=0;i<list.length;i++)
			System.out.println(list[i]);
	}
}
