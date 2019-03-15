package vo;

import java.util.ArrayList;

/**
 * gxy改了
 * @author Lenovo
 *
 */
public class SalesBillVO extends BillVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7586137846445713003L;
	private CustomerVO customer;
	private UserInfoVO businessman;
	int ware;
	private ArrayList<SalesGoodsVO> goodsList;
	private double oriMoney;//折让前总额
	private double discount;//折让
	private double coupon;//代金券
	private	double money;//折让后总额
	private double actualMoney;//实收金额
	
	public SalesBillVO(){
		
	}
	
	public SalesBillVO(CustomerVO cus, UserInfoVO bus, int w, ArrayList<SalesGoodsVO> l, 
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

	public ArrayList<SalesGoodsVO> getGoodsList() {
		return goodsList;
	}
	
	public void setGoodsList(ArrayList<SalesGoodsVO> goodsList) {
		this.goodsList = goodsList;
	}

	public CustomerVO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVO customer) {
		this.customer = customer;
	}

	public UserInfoVO getBusinessman() {
		return businessman;
	}

	public void setBusinessman(UserInfoVO businessman) {
		this.businessman = businessman;
	}

	public double getActualMoney() {
		return actualMoney;
	}

	public void setActualMoney(double actualMoney) {
		this.actualMoney = actualMoney;
	}
}
