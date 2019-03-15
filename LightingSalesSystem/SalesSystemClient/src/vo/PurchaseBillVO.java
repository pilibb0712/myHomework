package vo;

import java.util.ArrayList;

/**
 * gxy改了
 * @author Lenovo
 *
 */
public class PurchaseBillVO extends BillVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7908590039834527558L;
	private CustomerVO supplier;//供应商
	private int ware;//仓库
	private ArrayList<SalesGoodsVO> goodsList;//入库商品列表
	private double money;//总额合计
	
	public void setSupplier(CustomerVO supplier) {
		this.supplier = supplier;
	}
	public CustomerVO getSupplier() {
		return supplier;
	}
	public int getWare() {
		return ware;
	}
	public void setWare(int ware) {
		this.ware = ware;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public ArrayList<SalesGoodsVO> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(ArrayList<SalesGoodsVO> goodsList) {
		this.goodsList = goodsList;
	}
}
