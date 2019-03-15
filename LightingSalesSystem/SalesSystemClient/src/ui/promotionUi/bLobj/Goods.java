package ui.promotionUi.bLobj;

import vo.GiftVO;
import vo.GoodsVO;

public class Goods {
	private String name;
	private String id;
	private String classification;
	private String type;
	private int amount;
	private double costPrice;
	private double sellingPrice;
//	private GoodsVO goods;
	private boolean isAddedToGift;
	
	public Goods(GoodsVO vo){
		//goods = vo;
		name = vo.getName();
		id = vo.getNumber();
		classification = vo.getClassification();
		type = vo.getType();
		amount = vo.getAmount();
		costPrice = Double.parseDouble(vo.getCostPrice());
		sellingPrice = Double.parseDouble(vo.getSellingPrice()) ;
		setAddedToGift(false);
	}
	
	
	public boolean isAddedToGift() {
		return isAddedToGift;
	}
	public void setAddedToGift(boolean isAddedToGift) {
		this.isAddedToGift = isAddedToGift;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getClassification() {
		return classification;
	}


	public void setClassification(String classification) {
		this.classification = classification;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public double getCostPrice() {
		return costPrice;
	}


	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}


	public double getSellingPrice() {
		return sellingPrice;
	}


	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	public GiftVO toGiftVO(){
		GiftVO vo = new GiftVO();
		vo.setGiftAmount(0);
		vo.setId(id);
		vo.setName(name);
		vo.setPrice(sellingPrice);
		return vo;
	}
}
