package vo;

import java.io.Serializable;

public class GiftVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2095587797863781664L;
	private String Id;
	private String name;
	private double price;
	private int giftAmount;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getGiftAmount() {
		return giftAmount;
	}

	public void setGiftAmount(int giftAmount) {
		this.giftAmount = giftAmount;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public double getTotalPrice() {
		return  giftAmount*price;
	}

}
