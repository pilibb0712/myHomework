package po;

import java.io.Serializable;

public class GiftPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2622991931472970819L;
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
		return giftAmount*price;
	}

}
