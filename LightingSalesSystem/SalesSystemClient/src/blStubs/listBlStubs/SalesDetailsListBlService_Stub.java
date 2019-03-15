package blStubs.listBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.type.CustomerLevelEnum;
import blService.listBlService.SalesDetailsListBlService;
import vo.ClassificationVO;
import vo.CustomerInfoVO;
import vo.GoodsVO;
import vo.SalesDetailsListSalesBillVO;
import vo.SalesDetailsListVO;
import vo.UserVO;

/**
 * 查看销售明细表逻辑接口的桩
 * @author guxinyu
 * @version 2017.12.18
 * 
 */
public class SalesDetailsListBlService_Stub implements SalesDetailsListBlService{
	@Override
	public SalesDetailsListVO getSalesDetailsList(){
		
		ArrayList<SalesDetailsListSalesBillVO> sbArray=new ArrayList<SalesDetailsListSalesBillVO>();
		
		SalesDetailsListSalesBillVO bill1=new SalesDetailsListSalesBillVO();
		
		bill1.setBillId("XSJHD-20170811-00001");
		bill1.setCommodityName("客厅大灯");
		bill1.setCommodityType("大灯类");
		bill1.setDate("2017.08.11");
		bill1.setNumber("1");
		bill1.setUnitPrice("5000");
		bill1.setSum("5000");
		bill1.setCustomerId("00002");
		bill1.setOperatorId("00001");
		bill1.setWare("01");
		
		SalesDetailsListSalesBillVO bill2=new SalesDetailsListSalesBillVO();
		
		bill2.setBillId("XSJHD-20170811-00002");
		bill2.setCommodityName("走廊大灯");
		bill2.setCommodityType("大灯类");
		bill2.setDate("2017.08.11");
		bill2.setNumber("2");
		bill2.setUnitPrice("4000");
		bill2.setSum("8000");
		bill2.setCustomerId("00003");
		bill2.setOperatorId("00008");
		bill2.setWare("01");
		
		SalesDetailsListSalesBillVO bill3=new SalesDetailsListSalesBillVO();
		
		bill3.setBillId("XSJHD-20170905-00023");
		bill3.setCommodityName("会议厅灯");
		bill3.setCommodityType("大灯类");
		bill3.setDate("2017.9.5");
		bill3.setNumber("1");
		bill3.setUnitPrice("5000");
		bill3.setSum("5000");
		bill3.setCustomerId("00012");
		bill3.setOperatorId("00010");
		bill3.setWare("02");
		
		SalesDetailsListSalesBillVO bill4=new SalesDetailsListSalesBillVO();
		
		bill4.setBillId("XSJHD-20170905-00001");
		bill4.setCommodityName("床头灯");
		bill4.setCommodityType("小灯类");
		bill4.setDate("2017.9.5");
		bill4.setNumber("1");
		bill4.setUnitPrice("5000");
		bill4.setSum("5000");
		bill4.setCustomerId("00001");
		bill4.setOperatorId("00001");
		bill4.setWare("02");
		
		SalesDetailsListSalesBillVO bill5=new SalesDetailsListSalesBillVO();
		
		bill5.setBillId("XSJHD-20170906-00005");
		bill5.setCommodityName("厕所灯");
		bill5.setCommodityType("小灯类");
		bill5.setDate("2017.9.6");
		bill5.setNumber("1");
		bill5.setUnitPrice("5000");
		bill5.setSum("5000");
		bill5.setCustomerId("00004");
		bill5.setOperatorId("00045");
		bill5.setWare("03");
		
		SalesDetailsListSalesBillVO bill6=new SalesDetailsListSalesBillVO();
		
		bill6.setBillId("XSJHD-20171012-00009");
		bill6.setCommodityName("厨房灯");
		bill6.setCommodityType("小灯类");
		bill6.setDate("2017.10.12");
		bill6.setNumber("1");
		bill6.setUnitPrice("5000");
		bill6.setSum("5000");
		bill6.setCustomerId("00006");
		bill6.setOperatorId("00013");
		bill6.setWare("03");
		
		SalesDetailsListSalesBillVO bill7=new SalesDetailsListSalesBillVO();
		
		bill7.setBillId("XSJHD-20171013-00561");
		bill7.setCommodityName("花纹钓鱼灯");
		bill7.setCommodityType("钓鱼灯类");
		bill7.setDate("2017.10.13");
		bill7.setNumber("1");
		bill7.setUnitPrice("5000");
		bill7.setSum("5000");
		bill7.setCustomerId("00008");
		bill7.setOperatorId("00032");
		bill7.setWare("04");
		
		SalesDetailsListSalesBillVO bill8=new SalesDetailsListSalesBillVO();
		
		bill8.setBillId("XSJHD-20171013-00013");
		bill8.setCommodityName("弧形钓鱼灯");
		bill8.setCommodityType("钓鱼灯类");
		bill8.setDate("2017.10.13");
		bill8.setNumber("1");
		bill8.setUnitPrice("5000");
		bill8.setSum("5000");
		bill8.setCustomerId("00010");
		bill8.setOperatorId("00021");
		bill8.setWare("04");
		
		SalesDetailsListSalesBillVO bill9=new SalesDetailsListSalesBillVO();
		
		bill9.setBillId("XSJHD-20171115-00055");
		bill9.setCommodityName("棱角钓鱼灯");
		bill9.setCommodityType("钓鱼灯类");
		bill9.setDate("2017.11.15");
		bill9.setNumber("1");
		bill9.setUnitPrice("5000");
		bill9.setSum("5000");
		bill9.setCustomerId("00012");
		bill9.setOperatorId("00015");
		bill9.setWare("05");
		
		SalesDetailsListSalesBillVO bill10=new SalesDetailsListSalesBillVO();
		
		bill10.setBillId("XSJHD-20171120-00001");
		bill10.setCommodityName("声控灯");
		bill10.setCommodityType("智能灯类");
		bill10.setDate("2017.11.20");
		bill10.setNumber("1");
		bill10.setUnitPrice("5000");
		bill10.setSum("5000");
		bill10.setCustomerId("00008");
		bill10.setOperatorId("00035");
		bill10.setWare("06");
		
		SalesDetailsListSalesBillVO bill11=new SalesDetailsListSalesBillVO();
		
		bill11.setBillId("XSJHD-20171206-00005");
		bill11.setCommodityName("光控灯");
		bill11.setCommodityType("智能灯类");
		bill11.setDate("2017.12.6");
		bill11.setNumber("1");
		bill11.setUnitPrice("5000");
		bill11.setSum("5000");
		bill11.setCustomerId("00010");
		bill11.setOperatorId("00013");
		bill11.setWare("06");
		
		SalesDetailsListSalesBillVO bill12=new SalesDetailsListSalesBillVO();
		
		bill12.setBillId("XSJHD-20171208-00045");
		bill12.setCommodityName("红外线灯");
		bill12.setCommodityType("智能灯类");
		bill12.setDate("2017.12.8");
		bill12.setNumber("1");
		bill12.setUnitPrice("5000");
		bill12.setSum("5000");
		bill12.setCustomerId("00003");
		bill12.setOperatorId("00035");
		bill12.setWare("06");
		
		sbArray.add(bill1);
		sbArray.add(bill2);
		sbArray.add(bill3);
		sbArray.add(bill4);
		sbArray.add(bill5);
		sbArray.add(bill6);
		sbArray.add(bill7);
		sbArray.add(bill8);
		sbArray.add(bill9);
		sbArray.add(bill10);
		sbArray.add(bill11);
		sbArray.add(bill12);
		
		SalesDetailsListVO toReturn=new SalesDetailsListVO(sbArray);
		
		return toReturn;
	}

	@Override
	public ArrayList<UserVO> getWareKeeperList() throws RemoteException {
		ArrayList<UserVO> toReturn=new ArrayList<UserVO>();
		
		UserVO user1=new UserVO();
		user1.setName("一");
		user1.setId("00001");
		
		UserVO user2=new UserVO();
		user2.setName("二");
		user2.setId("00002");
		
		UserVO user3=new UserVO();
		user3.setName("三");
		user3.setId("00003");
		
		UserVO user4=new UserVO();
		user4.setName("四");
		user4.setId("00004");
		
		UserVO user5=new UserVO();
		user5.setName("五");
		user5.setId("00005");
		
		UserVO user6=new UserVO();
		user6.setName("六");
		user6.setId("00006");
		
		UserVO user7=new UserVO();
		user7.setName("七");
		user7.setId("00007");
		
		UserVO user8=new UserVO();
		user8.setName("八");
		user8.setId("00008");
		
		UserVO user9=new UserVO();
		user9.setName("九");
		user9.setId("00009");
		
		UserVO user10=new UserVO();
		user10.setName("十");
		user10.setId("00010");
		
		UserVO user11=new UserVO();
		user11.setName("十一");
		user11.setId("00011");
		
		UserVO user12=new UserVO();
		user12.setName("十二");
		user12.setId("00012");
		
		UserVO user13=new UserVO();
		user13.setName("十三");
		user13.setId("00013");
		
		UserVO user14=new UserVO();
		user14.setName("十四");
		user14.setId("00014");
		
		toReturn.add(user1);
		toReturn.add(user2);
		toReturn.add(user3);
		toReturn.add(user4);
		toReturn.add(user5);
		toReturn.add(user6);
		toReturn.add(user7);
		toReturn.add(user8);
		toReturn.add(user9);
		toReturn.add(user10);
		toReturn.add(user11);
		toReturn.add(user12);
		toReturn.add(user13);
		toReturn.add(user14);
		
		return toReturn;
	}

	@Override
	public ArrayList<UserVO> getSalesmanList() throws RemoteException {
		ArrayList<UserVO> toReturn=new ArrayList<UserVO>();
		
		UserVO user1=new UserVO();
		user1.setName("一");
		user1.setId("00015");
		
		UserVO user2=new UserVO();
		user2.setName("二");
		user2.setId("00016");
		
		UserVO user3=new UserVO();
		user3.setName("三");
		user3.setId("00017");
		
		UserVO user4=new UserVO();
		user4.setName("四");
		user4.setId("00018");
		
		UserVO user5=new UserVO();
		user5.setName("五");
		user5.setId("00019");
		
		UserVO user6=new UserVO();
		user6.setName("六");
		user6.setId("00020");
		
		UserVO user7=new UserVO();
		user7.setName("七");
		user7.setId("00021");
		
		UserVO user8=new UserVO();
		user8.setName("八");
		user8.setId("00022");
		
		UserVO user9=new UserVO();
		user9.setName("九");
		user9.setId("00023");
		
		UserVO user10=new UserVO();
		user10.setName("十");
		user10.setId("00024");
		
		UserVO user11=new UserVO();
		user11.setName("十一");
		user11.setId("00025");
		
		UserVO user12=new UserVO();
		user12.setName("十二");
		user12.setId("00026");
		
		UserVO user13=new UserVO();
		user13.setName("十三");
		user13.setId("00027");
		
		UserVO user14=new UserVO();
		user14.setName("十四");
		user14.setId("00028");
		
		toReturn.add(user1);
		toReturn.add(user2);
		toReturn.add(user3);
		toReturn.add(user4);
		toReturn.add(user5);
		toReturn.add(user6);
		toReturn.add(user7);
		toReturn.add(user8);
		toReturn.add(user9);
		toReturn.add(user10);
		toReturn.add(user11);
		toReturn.add(user12);
		toReturn.add(user13);
		toReturn.add(user14);
		
		return toReturn;
	}

	@Override
	public ArrayList<UserVO> getFinancialStaffList() throws RemoteException {
		ArrayList<UserVO> toReturn=new ArrayList<UserVO>();
		
		UserVO user1=new UserVO();
		user1.setName("一");
		user1.setId("00029");
		
		UserVO user2=new UserVO();
		user2.setName("二");
		user2.setId("00030");
		
		UserVO user3=new UserVO();
		user3.setName("三");
		user3.setId("00031");
		
		UserVO user4=new UserVO();
		user4.setName("四");
		user4.setId("00032");
		
		UserVO user5=new UserVO();
		user5.setName("五");
		user5.setId("00033");
		
		UserVO user6=new UserVO();
		user6.setName("六");
		user6.setId("00034");
		
		UserVO user7=new UserVO();
		user7.setName("七");
		user7.setId("00035");
		
		UserVO user8=new UserVO();
		user8.setName("八");
		user8.setId("00036");
		
		UserVO user9=new UserVO();
		user9.setName("九");
		user9.setId("00037");
		
		UserVO user10=new UserVO();
		user10.setName("十");
		user10.setId("00038");
		
		UserVO user11=new UserVO();
		user11.setName("十一");
		user11.setId("00039");
		
		UserVO user12=new UserVO();
		user12.setName("十二");
		user12.setId("00040");
		
		UserVO user13=new UserVO();
		user13.setName("十三");
		user13.setId("00041");
		
		UserVO user14=new UserVO();
		user14.setName("十四");
		user14.setId("00042");
		
		toReturn.add(user1);
		toReturn.add(user2);
		toReturn.add(user3);
		toReturn.add(user4);
		toReturn.add(user5);
		toReturn.add(user6);
		toReturn.add(user7);
		toReturn.add(user8);
		toReturn.add(user9);
		toReturn.add(user10);
		toReturn.add(user11);
		toReturn.add(user12);
		toReturn.add(user13);
		toReturn.add(user14);
		
		return toReturn;
	}
	
	@Override
	public ArrayList<UserVO> getManagerList() throws RemoteException {
		ArrayList<UserVO> toReturn=new ArrayList<UserVO>();
		
		UserVO user1=new UserVO();
		user1.setName("一");
		user1.setId("00043");
		
		UserVO user2=new UserVO();
		user2.setName("二");
		user2.setId("00044");
		
		UserVO user3=new UserVO();
		user3.setName("三");
		user3.setId("00045");
		
		UserVO user4=new UserVO();
		user4.setName("四");
		user4.setId("00046");
		
		UserVO user5=new UserVO();
		user5.setName("五");
		user5.setId("00047");
		
		UserVO user6=new UserVO();
		user6.setName("六");
		user6.setId("00048");
		
		UserVO user7=new UserVO();
		user7.setName("七");
		user7.setId("00049");
		
		UserVO user8=new UserVO();
		user8.setName("八");
		user8.setId("00050");
		
		UserVO user9=new UserVO();
		user9.setName("九");
		user9.setId("00051");
		
		UserVO user10=new UserVO();
		user10.setName("十");
		user10.setId("00052");
		
		UserVO user11=new UserVO();
		user11.setName("十一");
		user11.setId("00053");
		
		UserVO user12=new UserVO();
		user12.setName("十二");
		user12.setId("00054");
		
		UserVO user13=new UserVO();
		user13.setName("十三");
		user13.setId("00055");
		
		UserVO user14=new UserVO();
		user14.setName("十四");
		user14.setId("00056");
		
		toReturn.add(user1);
		toReturn.add(user2);
		toReturn.add(user3);
		toReturn.add(user4);
		toReturn.add(user5);
		toReturn.add(user6);
		toReturn.add(user7);
		toReturn.add(user8);
		toReturn.add(user9);
		toReturn.add(user10);
		toReturn.add(user11);
		toReturn.add(user12);
		toReturn.add(user13);
		toReturn.add(user14);
		
		return toReturn;
	}
	
	@Override
	public ArrayList<CustomerInfoVO> getCustomerList() throws RemoteException {
		ArrayList<CustomerInfoVO> toReturn=new ArrayList<CustomerInfoVO>();
		
		CustomerInfoVO cus1=new CustomerInfoVO();
		cus1.setId("00001");
		cus1.setName("张三");
		cus1.setType("供应商");
		cus1.setLevel(CustomerLevelEnum.VIP1);
		
		CustomerInfoVO cus2=new CustomerInfoVO();
		cus2.setId("00002");
		cus2.setName("李四");
		cus2.setType("供应商");
		cus2.setLevel(CustomerLevelEnum.VIP2);
		
		CustomerInfoVO cus3=new CustomerInfoVO();
		cus3.setId("00003");
		cus3.setName("王二");
		cus3.setType("供应商");
		cus3.setLevel(CustomerLevelEnum.VIP3);
		
		CustomerInfoVO cus4=new CustomerInfoVO();
		cus4.setId("00004");
		cus4.setName("黑五");
		cus4.setType("供应商");
		cus4.setLevel(CustomerLevelEnum.VIP4);
		
		CustomerInfoVO cus5=new CustomerInfoVO();
		cus5.setId("00005");
		cus5.setName("白痴");
		cus5.setType("供应商");
		cus5.setLevel(CustomerLevelEnum.VIP5);
		
		CustomerInfoVO cus6=new CustomerInfoVO();
		cus6.setId("00006");
		cus6.setName("金三角");
		cus6.setType("销售商");
		cus6.setLevel(CustomerLevelEnum.VIP1);
		
		CustomerInfoVO cus7=new CustomerInfoVO();
		cus7.setId("00007");
		cus7.setName("绿巨人");
		cus7.setType("销售商");
		cus7.setLevel(CustomerLevelEnum.VIP2);
		
		CustomerInfoVO cus8=new CustomerInfoVO();
		cus8.setId("00008");
		cus8.setName("蓝精灵");
		cus8.setType("销售商");
		cus8.setLevel(CustomerLevelEnum.VIP3);
		
		CustomerInfoVO cus9=new CustomerInfoVO();
		cus9.setId("00009");
		cus9.setName("小红帽");
		cus9.setType("销售商");
		cus9.setLevel(CustomerLevelEnum.VIP4);
		
		CustomerInfoVO cus10=new CustomerInfoVO();
		cus10.setId("00010");
		cus10.setName("灰太狼");
		cus10.setType("销售商");
		cus10.setLevel(CustomerLevelEnum.VIP5);
		
		CustomerInfoVO cus11=new CustomerInfoVO();
		cus11.setId("00011");
		cus11.setName("水玲龙");
		cus11.setType("供应商");
		cus11.setLevel(CustomerLevelEnum.VIP2);
		
		CustomerInfoVO cus12=new CustomerInfoVO();
		cus12.setId("00012");
		cus12.setName("大白兔");
		cus12.setType("供应商");
		cus12.setLevel(CustomerLevelEnum.VIP1);
		
		CustomerInfoVO cus13=new CustomerInfoVO();
		cus13.setId("00013");
		cus13.setName("狗蛋");
		cus13.setType("销售商");
		cus13.setLevel(CustomerLevelEnum.VIP3);
		
		CustomerInfoVO cus14=new CustomerInfoVO();
		cus14.setId("00014");
		cus14.setName("孔傻傻");
		cus14.setType("销售商");
		cus14.setLevel(CustomerLevelEnum.VIP1);
		
		toReturn.add(cus1);
		toReturn.add(cus2);
		toReturn.add(cus3);
		toReturn.add(cus4);
		toReturn.add(cus5);
		toReturn.add(cus6);
		toReturn.add(cus7);
		toReturn.add(cus8);
		toReturn.add(cus9);
		toReturn.add(cus10);
		toReturn.add(cus11);
		toReturn.add(cus12);
		toReturn.add(cus13);
		toReturn.add(cus14);
		
		return toReturn;
	}

	@Override
	public ArrayList<ClassificationVO> getCommodityClassification() throws RemoteException {
		ArrayList<ClassificationVO> vo=new ArrayList<ClassificationVO>();
		
		ClassificationVO class1=new ClassificationVO();
		class1.setName("大灯类");
		ArrayList<String> goodsList1=new ArrayList<String>();
		goodsList1.add("客厅大灯");
		goodsList1.add("走廊大灯");
		goodsList1.add("会议厅灯");
		class1.setGoodsList(goodsList1);
		
		ClassificationVO class2=new ClassificationVO();
		class2.setName("小灯类");
		ArrayList<String> goodsList2=new ArrayList<String>();
		goodsList2.add("床头灯");
		goodsList2.add("厕所灯");
		goodsList2.add("厨房灯");
		class2.setGoodsList(goodsList2);
		
		ClassificationVO class3=new ClassificationVO();
		class3.setName("钓鱼灯类");
		ArrayList<String> goodsList3=new ArrayList<String>();
		goodsList3.add("花纹钓鱼灯");
		goodsList3.add("弧形钓鱼灯");
		goodsList3.add("棱角钓鱼灯");
		class3.setGoodsList(goodsList3);
		
		ClassificationVO class4=new ClassificationVO();
		class4.setName("智能灯类");
		ArrayList<String> goodsList4=new ArrayList<String>();
		goodsList4.add("声控灯");
		goodsList4.add("光控灯");
		goodsList4.add("红外线灯");
		class4.setGoodsList(goodsList4);
		
		vo.add(class1);
		vo.add(class2);
		vo.add(class3);
		vo.add(class4);
		
		return vo;
	}

	@Override
	public ArrayList<GoodsVO> getCommodities() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
