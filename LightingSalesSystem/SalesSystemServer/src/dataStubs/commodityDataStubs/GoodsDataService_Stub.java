package dataStubs.commodityDataStubs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataService.commodityDataService.GoodsDataService;
import po.GoodsPO;
import vo.GoodsVO;

public class GoodsDataService_Stub implements GoodsDataService{
	public ArrayList<GoodsPO> finds(){
		ArrayList<GoodsPO> allGoods=new ArrayList<GoodsPO>();
		GoodsPO goods=new GoodsPO();
		goods.setName("AAa");
		goods.setNumber("11001");
		goods.setClassification("AA");
		goods.setType("00001A");
		goods.setAmount(100);
		goods.setCostPrice("100");
		goods.setSellingPrice("110");
		goods.setCurrentCostPrice("100");
		goods.setCurrentSellingPrice("110");
		allGoods.add(goods);
		return allGoods;
	}
	public ArrayList<GoodsPO> fuzzyFinds(String keyWord){
		ArrayList<GoodsPO> relatedGoods=new ArrayList<GoodsPO>();
		GoodsPO goods1=new GoodsPO();
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

		GoodsPO goods2=new GoodsPO();
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

		GoodsPO goods3=new GoodsPO();
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

		GoodsPO goods4=new GoodsPO();
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
	public GoodsPO find(String goodsName,String goodsType){
		GoodsPO goods=new GoodsPO();
		goods.setName("AAa");
		goods.setNumber("11001");
		goods.setClassification("AA");
		goods.setType("00001A");
		goods.setAmount(100);
		goods.setCostPrice("100");
		goods.setSellingPrice("110");
		goods.setCurrentCostPrice("100");
		goods.setCurrentSellingPrice("110");
		return goods;
	}
	public GoodsPO find(String goodsTag){
		GoodsPO goods=new GoodsPO();
		goods.setName("AAa");
		goods.setNumber("11001");
		goods.setClassification("AA");
		goods.setType("00001A");
		goods.setAmount(100);
		goods.setCostPrice("100");
		goods.setSellingPrice("110");
		goods.setCurrentCostPrice("100");
		goods.setCurrentSellingPrice("110");
		return goods;
	}
	public boolean insert(GoodsPO po){
		return true;
	}
	public boolean delete(GoodsPO po){
		return true;
	}
	public boolean update(GoodsPO po){
		return true;
	}

}
