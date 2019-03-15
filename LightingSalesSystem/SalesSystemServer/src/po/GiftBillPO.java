package po;

import java.util.ArrayList;

public class GiftBillPO extends BillPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8663480880817844170L;
	
	private double totalPrice;
	private ArrayList<GiftPO> gifts;

	public ArrayList<GiftPO> getGifts() {
		return gifts;
	}

	public void setGifts(ArrayList<GiftPO> gifts) {
		this.gifts = gifts;
		calcTotalPrice();
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	private void calcTotalPrice(){
		double price = 0;
		if(gifts==null){ return; }
		for(GiftPO gift: gifts){
			price += gift.getTotalPrice();
		}
		this.totalPrice = price;
	//	System.out.println("设置价格"+price);
	}
}
