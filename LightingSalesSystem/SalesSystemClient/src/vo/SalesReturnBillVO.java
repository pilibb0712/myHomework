package vo;

import java.util.ArrayList;


/**
 * gxy改了
 * @author Lenovo
 *
 */
public class SalesReturnBillVO extends BillVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4349749143603811472L;
	private CustomerVO customer;
	private UserInfoVO businessman;
	int ware;
	private ArrayList<SalesGoodsVO> goodsList;
	double money;//折让后总额
	
	public SalesReturnBillVO(){
		
	}
	
	public SalesReturnBillVO(CustomerVO cus, UserInfoVO bus, int w,  ArrayList<SalesGoodsVO> l,
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
	public void setGoodsList(ArrayList<SalesGoodsVO> list){
		goodsList=list;
	}
	public ArrayList<SalesGoodsVO> getGoodsList(){
		return goodsList;
	}
	public void setMoney(double n) {
		money=n;
	}
	public double getMoney() {
		return money;
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
}
