package vo;

import java.io.Serializable;

public class SalesManCommodityVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1572403902845664974L;
	/**
	 * 
	 */
	
	private String Id;
	private String name;
	private double price;
	private int amount;
	
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

}
