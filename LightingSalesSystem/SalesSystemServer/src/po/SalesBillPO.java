package po;

import java.util.ArrayList;

/**
 * gxy改了
 * @author Lenovo
 *
 */
public class SalesBillPO extends BillPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerPO customer;
	private UserInfoPO businessman;
	int ware;
	private ArrayList<SalesGoodsPO> goodsList;
	private double oriMoney;//折让前总额
	private double discount;//折让
	private double coupon;//代金券
	private	double money;//折让后总额
	private double actualMoney;//实收金额
	
//	0 String commodityNum;
//	1 String commodityName;
//	2 String model;
//	3 String amount;
//	4 String (double) univalent;
//	5 String (double) commodityMoney;
//	6 String commodityComment;
	
	public SalesBillPO(){
		
	}
	
	public SalesBillPO(CustomerPO cus, UserInfoPO bus, int w, ArrayList<SalesGoodsPO> l, 
			double ori, double dis, double cou, double m) {
		setCustomer(cus);
		setBusinessman(bus);
		setWare(w);
		setGoodsList(goodsList);
		setOriMoney(ori);
		setDiscount(dis);
		setCoupon(cou);
		setMoney(m);
	}



	public void setWare(int n) {
		ware=n;
	}
	public int getWare() {
		return ware;
	}
	
	public void setOriMoney(double n) {
		oriMoney=n;
	}
	public double getOriMoney() {
		return oriMoney;
	}
	public void setDiscount(double n) {
		discount=n;
	}
	public double getDiscount() {
		return discount;
	}
	public void setCoupon(double n) {
		coupon=n;
	}
	public double getCoupon() {
		return coupon;
	}
	public void setMoney(double n) {
		money=n;
	}
	public double getMoney() {
		return money;
	}
	
	public ArrayList<SalesGoodsPO> getGoodsList() {
		return goodsList;
	}
	
	public void setGoodsList(ArrayList<SalesGoodsPO> goodsList) {
		this.goodsList = goodsList;
	}

	public CustomerPO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerPO customer) {
		this.customer = customer;
	}

	public UserInfoPO getBusinessman() {
		return businessman;
	}

	public void setBusinessman(UserInfoPO businessman) {
		this.businessman = businessman;
	}

	public double getActualMoney() {
		return actualMoney;
	}

	public void setActualMoney(double actualMoney) {
		this.actualMoney = actualMoney;
	}
}
