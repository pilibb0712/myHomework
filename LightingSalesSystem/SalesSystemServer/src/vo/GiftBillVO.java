package vo;

import java.util.ArrayList;

public class GiftBillVO extends BillVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5260154692847909693L;
	private double totalPrice;
	private ArrayList<GiftVO> gifts;

	public ArrayList<GiftVO> getGifts() {
		return gifts;
	}

	public void setGifts(ArrayList<GiftVO> gifts) {
		this.gifts = gifts;
		calcTotalPrice();
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	private void calcTotalPrice(){
		double price = 0;
		for(GiftVO gift: gifts){
			price += gift.getTotalPrice();
		}
		this.totalPrice = price;
	}
}
