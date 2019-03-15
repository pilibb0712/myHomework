package assistant.type;
/**
 * @author zhangao 161250193
 * @version 2017.12.12
 * 单据类型的枚举类型
 */
public enum BillCategoryEnum {
	CASH_EXPENSE_BILL//现金费用单
	,GIFT_BILL//库存赠送单
	,INVENTORY_LOSS_BILL//库存报损单
	,INVENTORY_OVER_BILL//库存报溢单
	,INVENTORY_WARNING_BILL//库存报警单
	,PAYMENT_BILL//付款单
	,PURCHASE_BILL//进货单
	,PURCHASE_RETURN_BILL//进货退货单
	,RECEIPT_BILL//收款单
	,SALES_BILL//销售单
	,SALES_RETURN_BILL;//销售退货单
	
	/**
	 * 返回当前单据名称(对应数据层地址名)。<br/>
	 * @return String 当前的单据的名称(小写字母，对应存的文件夹名称)
	 */
	public String toString(){
		return this.name().toLowerCase();
	}
	
	/**
     * 得到该单据的Id部分
     * 例如JHTHD-yyyyMMdd-中的JHTHD部分
     * @return 单据的Id部分
     */
	public String toBillIdPart(){
		switch(this){
		case GIFT_BILL:
			return "KCZSD";
		case CASH_EXPENSE_BILL:
			return "XJFYD";
		case INVENTORY_LOSS_BILL:
			return "KCBSD";
		case INVENTORY_OVER_BILL:
			return "KCBYD";
		case INVENTORY_WARNING_BILL:
			return "KCBJD";
		case PAYMENT_BILL:
			return "FKD";
		case RECEIPT_BILL:
			return "SKD";
		case PURCHASE_BILL:
			return "JHD";
		case PURCHASE_RETURN_BILL:
			return "JHTHD";
		case SALES_BILL:
			return "XSD";
		case SALES_RETURN_BILL:
			return "XSTHD";
		default :
			return "wrong";
		}
	}
	
	/**
	 * 返回当前单据中文名称
	 * @return 中文名称
	 */
	public String toChineseString(){
		switch(this){
		case GIFT_BILL:
			return "库存赠送单";
		case CASH_EXPENSE_BILL:
			return "现金费用单";
		case INVENTORY_LOSS_BILL:
			return "库存报损单";
		case INVENTORY_OVER_BILL:
			return "库存报溢单";
		case INVENTORY_WARNING_BILL:
			return "库存报警单";
		case PAYMENT_BILL:
			return "付款单";
		case RECEIPT_BILL:
			return "收款单";
		case PURCHASE_BILL:
			return "进货单";
		case PURCHASE_RETURN_BILL:
			return "进货退货单";
		case SALES_BILL:
			return "销售单";
		case SALES_RETURN_BILL:
			return "销售退货单";
		default :
			return "错误";
		}
	}
}
