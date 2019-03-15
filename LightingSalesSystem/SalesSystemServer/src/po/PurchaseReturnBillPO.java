package po;

import java.util.ArrayList;

/**
 * gxy改了
 * @author Lenovo
 *
 */
public class PurchaseReturnBillPO extends BillPO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerPO supplier;//供应商
	private int ware;//仓库
	private ArrayList<SalesGoodsPO> goodsList;//入库商品列表
	private double money;//总额合计
	
	public PurchaseReturnBillPO(){
		
	}
	
	public PurchaseReturnBillPO(CustomerPO cus, int w, ArrayList<SalesGoodsPO> list,
			double m){
		setSupplier(cus);
		setWare(w);
		setGoodsList(list);
		setMoney(m);
	}

	public CustomerPO getSupplier() {
		return supplier;
	}

	public void setSupplier(CustomerPO supplier) {
		this.supplier = supplier;
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

	public ArrayList<SalesGoodsPO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(ArrayList<SalesGoodsPO> goodsList) {
		this.goodsList = goodsList;
	}
}
