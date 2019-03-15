package vo;

import java.io.Serializable;

/**
 * 用于存储销售明细表中的销售单的信息的VO
 * @author guxinyu
 * @version 2017.12.18
 *
 */
public class SalesDetailsListSalesBillVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6497992795878332476L;
	//界面上要显示的信息
		private String date;//日期
		private String commodityName;//商品名称
		private String commodityType;//商品类型
		private String number;//数量
		private String unitPrice;//单价
		private String sum;//总额
		
		//界面上不显示但筛选条件里要有的信息
		private String commodityId;
		private String customerId;//客户
		private String operatorId;//业务员
		private String ware;//仓库
		
		private String billId;
		
		public SalesDetailsListSalesBillVO(){
			
		}
		
		public SalesDetailsListSalesBillVO(String d, String comName, String comType, 
				String num, String uPrice, String s, String cus, String opter, 
				String w, String bId){
			setDate(d);
			setCommodityName(comName);
			setCommodityType(comType);
			setNumber(num);
			setUnitPrice(uPrice);
			setSum(s);
			setCustomerId(cus);
			setOperatorId(opter);
			setWare(w);
			setBillId(bId);
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getCommodityName() {
			return commodityName;
		}

		public void setCommodityName(String commodityName) {
			this.commodityName = commodityName;
		}

		public String getCommodityType() {
			return commodityType;
		}

		public void setCommodityType(String commodityType) {
			this.commodityType = commodityType;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(String unitPrice) {
			this.unitPrice = unitPrice;
		}

		public String getSum() {
			return sum;
		}

		public void setSum(String sum) {
			this.sum = sum;
		}

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customer) {
			this.customerId = customer;
		}

		public String getOperatorId() {
			return operatorId;
		}

		public void setOperatorId(String operator) {
			this.operatorId = operator;
		}

		public String getWare() {
			return ware;
		}

		public void setWare(String ware) {
			this.ware = ware;
		}

		public String getBillId() {
			return billId;
		}

		public void setBillId(String billId) {
			this.billId = billId;
		}

		public String getCommodityId() {
			return commodityId;
		}

		public void setCommodityId(String commodityId) {
			this.commodityId = commodityId;
		}
}
