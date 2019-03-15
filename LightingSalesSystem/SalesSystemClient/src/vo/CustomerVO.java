package vo;

import java.io.Serializable;

import assistant.type.CustomerLevelEnum;

/**
 * 
 * @author 王宁一 161250140
 * @version 2017.11.27
 */

public class CustomerVO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7412576220981370011L;
	private String Id;//客户编号
	private String type;//类型（销售商/进货商）
	private CustomerLevelEnum level;//级别
	private String name, tel, address, post, email;
	private double shouldReceiveCredit, shouldReceive, shouldPay;//应收额度，应收，应付
	private UserInfoVO defaultSalesman;//默认业务员
	
	public String getId() {
		return Id;
	}
	
	public void setId(String id) {
		Id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CustomerLevelEnum getLevel() {
		return level;
	}

	public void setLevel(CustomerLevelEnum level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getShouldReceiveCredit() {
		return shouldReceiveCredit;
	}

	public void setShouldReceiveCredit(double shouldReceiveCredit) {
		this.shouldReceiveCredit = shouldReceiveCredit;
	}

	public double getShouldReceive() {
		return shouldReceive;
	}

	public void setShouldReceive(double shouldReceive) {
		this.shouldReceive = shouldReceive;
	}

	public double getShouldPay() {
		return shouldPay;
	}

	public void setShouldPay(double shouldPay) {
		this.shouldPay = shouldPay;
	}

	public UserInfoVO getDefaultSalesman() {
		return defaultSalesman;
	}

	public void setDefaultSalesman(UserInfoVO defaultSalesman) {
		this.defaultSalesman = defaultSalesman;
	}
}
