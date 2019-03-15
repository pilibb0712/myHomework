package blStubs.financeBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;
import blService.financeBlService.InOutBlService;
import vo.InOutDetailsVO;
import vo.InitialInfoVO;
import vo.SingleBankAccountInitialInfoVO;
import vo.SingleCommodityInitialInfoVO;
import vo.SingleCustomerInitialInfoVO;
import vo.SingleEntryVO;

/**
 * 账单管理逻辑接口的桩
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class InOutBlService_Stub implements InOutBlService{

	@Override
	public boolean newInOut(InitialInfoVO initialInfo) throws RemoteException {
		System.out.println("Bill established!");
		ArrayList<SingleCommodityInitialInfoVO> com=initialInfo.getCommodityInfos();
		ArrayList<SingleCustomerInitialInfoVO> cus=initialInfo.getCustomerInfos();
		ArrayList<SingleBankAccountInitialInfoVO> bank=initialInfo.getBankAccountInfos();
		
		for(int i=0;i<com.size();i++){
			SingleCommodityInitialInfoVO each=com.get(i);
			System.out.println(each.getCommodityClass()+","
					+each.getCommodityName()+","
					+each.getCommodityType()+","
					+each.getPurchasePrice()+","
					+each.getSellingPrice()+","
					+each.getRecentPurchasePrice()+","
					+each.getRecentSellingPrice()+",");
		}
		
		for(int i=0;i<cus.size();i++){
			SingleCustomerInitialInfoVO each=cus.get(i);
			System.out.println(each.getCustomerClass()+","
					+each.getCustomerName()+","
					+each.getCustomerContact()+","
					+each.getCustomerPay()+","
					+each.getCustomerReceive()+","
					);
		}
		
		for(int i=0;i<bank.size();i++){
			SingleBankAccountInitialInfoVO each=bank.get(i);
			System.out.println(each.getBankAccountName()+","
					+each.getBankAccountSum()+","
					);
		}
		return true;
	}

	@Override
	public InitialInfoVO viewInitialInformation(String inOutYear) throws RemoteException {
		String goodsClass="大灯类";
		String goodsName="客厅大灯";
		String goodsType="xxx-yyyyy";
		String purPrice="4500.0";
		String sellPrice="5000.0";
		String rePurPrice="0.0";
		String reSellPrice="0.0";
		String customerClass="VIP";
		String customerName="李四";
		String customerContact="185564654";
		String customerReceive="0.0";
		String customerPayment="5000.0";
		String accountName="account1";
		String accountSum="45615.2";
		
		SingleCommodityInitialInfoVO comSingle1=new SingleCommodityInitialInfoVO(goodsClass,goodsName,
				goodsType,purPrice,sellPrice,rePurPrice,reSellPrice);
		
		SingleCommodityInitialInfoVO comSingle2=new SingleCommodityInitialInfoVO(goodsClass,goodsName,
				goodsType,purPrice,sellPrice,rePurPrice,reSellPrice);
		SingleCustomerInitialInfoVO cusSingle1=new SingleCustomerInitialInfoVO(customerClass,
				customerName,customerContact,customerReceive,customerPayment);
		
		SingleCustomerInitialInfoVO cusSingle2=new SingleCustomerInitialInfoVO(customerClass,
				customerName,customerContact,customerReceive,customerPayment);
		SingleBankAccountInitialInfoVO bankSingle1=new SingleBankAccountInitialInfoVO(accountName,accountSum);
		
		SingleBankAccountInitialInfoVO bankSingle2=new SingleBankAccountInitialInfoVO(accountName,accountSum);
		
		ArrayList<SingleCommodityInitialInfoVO> com=new ArrayList<SingleCommodityInitialInfoVO>();
		com.add(comSingle1);
		com.add(comSingle2);
		ArrayList<SingleCustomerInitialInfoVO> cus=new ArrayList<SingleCustomerInitialInfoVO>();
		cus.add(cusSingle1);
		cus.add(cusSingle2);
		ArrayList<SingleBankAccountInitialInfoVO> bank=new ArrayList<SingleBankAccountInitialInfoVO>();
		bank.add(bankSingle1);
		bank.add(bankSingle2);
		
		InitialInfoVO toReturn=new InitialInfoVO(com, cus, bank);
		
		return toReturn;
	}

	@Override
	public InOutDetailsVO viewInOutDetails(String inOutYear) throws RemoteException {
		SingleEntryVO entry1=new SingleEntryVO("2017","2017-12-6","售出床头大灯","+5000.0");
		SingleEntryVO entry2=new SingleEntryVO("2017","2017-12-6","进货走廊灯","-3000.0");
		
		ArrayList<SingleEntryVO> de=new ArrayList<SingleEntryVO>();
		de.add(entry1);
		de.add(entry2);
		InOutDetailsVO details=new InOutDetailsVO(de);
		
		return details;
	}

	@Override
	public boolean autoAddEntry(SingleEntryVO toAdd) throws RemoteException {
		System.out.println("Is added.");
		return true;
	}

	@Override
	public String[] viewInOutList() throws RemoteException {
		String[] list={"2010","2011","2012","2013","2014","2015","2016","2017"};
		
		return list;
	}

}
