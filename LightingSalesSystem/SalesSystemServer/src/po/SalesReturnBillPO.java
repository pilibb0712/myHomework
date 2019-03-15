package po;

import java.util.ArrayList;


/**
 * gxy改了
 * @author Lenovo
 *
 */
public class SalesReturnBillPO extends BillPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerPO customer;
	private UserInfoPO businessman;
	int ware;
	private ArrayList<SalesGoodsPO> goodsList;
	double money;//折让后总额
	
	public SalesReturnBillPO(){
		
	}
	
	public SalesReturnBillPO(CustomerPO cus, UserInfoPO bus, int w,  ArrayList<SalesGoodsPO> l,
			 double m) {
		setCustomer(cus);
		setBusinessman(bus);
		setWare(w);
		setWare(w);
		setGoodsList(l);
		setMoney(m);
	}
	public void setWare(int n) {
		ware=n;
	}
	public int getWare() {
		return ware;
	}
	public void setGoodsList(ArrayList<SalesGoodsPO> list){
		goodsList=list;
	}
	public ArrayList<SalesGoodsPO> getGoodsList(){
		return goodsList;
	}
	public void setMoney(double n) {
		money=n;
	}
	public double getMoney() {
		return money;
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
}
