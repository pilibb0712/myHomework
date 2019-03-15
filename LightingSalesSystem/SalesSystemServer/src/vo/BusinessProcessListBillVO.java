package vo;

import java.io.Serializable;

/**
 * 经营历程表里要用的单据信息
 * @author guxinyu
 * @version 2017.12.18
 *
 */
public class BusinessProcessListBillVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8348213866950045364L;
	//界面要显示的
	private String date;
	private String billName;
	private String billId;
	private String creator;
		
	//界面不显示但筛选条件要用的
	private String customerId;//客户编号
	private String userId;//业务员
	private String ware;//仓库
	
	public BusinessProcessListBillVO(){
		
	}
	
	public BusinessProcessListBillVO(String d, String bName, String bId, String c,
			String cusId, String uId, String w){
		setDate(d);
		setBillName(bName);
		setBillId(bId);
		setCreator(c);
		setCustomerId(cusId);
		setUserId(uId);
		setWare(w);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWare() {
		return ware;
	}

	public void setWare(String ware) {
		this.ware = ware;
	}
	
}
