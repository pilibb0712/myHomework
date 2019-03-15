package blStubs.commodityBlStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blService.commodityBlService.GoodsInforBlService;
import vo.GoodsVO;

public class GoodsInforService_Stub implements GoodsInforBlService{
	public boolean ifGoodsExist(String goodsName,String goodsType) throws RemoteException{
		return true;
		//???
	}

	public boolean ifGoodsExist(String goodsTag) throws RemoteException{
		return true;
		//???
	}

	public ArrayList<GoodsVO> fuzzyEnquiry(String keyWord) throws RemoteException{
		ArrayList<GoodsVO> relatedGoods=new ArrayList<GoodsVO>();
		GoodsVO goods1=new GoodsVO();
		goods1.setName("æ≠µ‰±¥±¥µ∆");
		goods1.setNumber("G01010101");
		goods1.setClassification("±¥±¥µ∆111∫≈");
		goods1.setType("T0001");
		goods1.setAmount(100);
		goods1.setCostPrice("100");
		goods1.setSellingPrice("110");
		goods1.setCurrentCostPrice("100");
		goods1.setCurrentSellingPrice("110");
		relatedGoods.add(goods1);

		GoodsVO goods2=new GoodsVO();
		goods2.setName("æ≠µ‰±¥±¥µ∆");
		goods2.setNumber("G01010102");
		goods2.setClassification("±¥±¥µ∆111∫≈");
		goods2.setType("T0002");
		goods2.setAmount(100);
		goods2.setCostPrice("100");
		goods2.setSellingPrice("110");
		goods2.setCurrentCostPrice("100");
		goods2.setCurrentSellingPrice("110");
		relatedGoods.add(goods2);

		GoodsVO goods3=new GoodsVO();
		goods3.setName("–«ø’±¥±¥µ∆");
		goods3.setNumber("G01010103");
		goods3.setClassification("±¥±¥µ∆111∫≈");
		goods3.setType("T0001");
		goods3.setAmount(100);
		goods3.setCostPrice("100");
		goods3.setSellingPrice("110");
		goods3.setCurrentCostPrice("100");
		goods3.setCurrentSellingPrice("110");
		relatedGoods.add(goods3);

		GoodsVO goods4=new GoodsVO();
		goods4.setName("Ï≈≤ ±¥±¥µ∆");
		goods4.setNumber("G01010104");
		goods4.setClassification("±¥±¥µ∆111∫≈");
		goods4.setType("T0001");
		goods4.setAmount(100);
		goods4.setCostPrice("100");
		goods4.setSellingPrice("110");
		goods4.setCurrentCostPrice("100");
		goods4.setCurrentSellingPrice("110");
		relatedGoods.add(goods4);

		return relatedGoods;
	}

	public GoodsVO goodsINfor(String goodsTag) throws RemoteException{
		GoodsVO goods=new GoodsVO();
		goods.setName("æ≠µ‰±¥±¥µ∆");
		goods.setNumber("G01010101");
		goods.setClassification("±¥±¥µ∆111∫≈");
		goods.setType("T0001");
		goods.setAmount(100);
		goods.setCostPrice("100");
		goods.setSellingPrice("110");
		goods.setCurrentCostPrice("100");
		goods.setCurrentSellingPrice("110");
		return goods;

	}

	public boolean modifyGoodsInfor(GoodsVO vo) throws RemoteException{
		return true;
	}

}
