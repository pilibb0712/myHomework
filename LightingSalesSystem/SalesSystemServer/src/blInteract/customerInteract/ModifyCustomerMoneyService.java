/**
 * 
 */
package blInteract.customerInteract;

/**
 * 修改客户的应收应付的业务逻辑接口
 * @author 王宁一
 * @version 2017.11.7
 *
 */
public interface ModifyCustomerMoneyService {

	/**
	 * 修改客户的应收应付
	 * @param 客户编号（字符串）
	 * @param 修改的数额
	 * @return 返回一个布尔值表示是否操作成功，如果成功则返回True
	 */
	public boolean ModifyCustomerMoney(String number, double amount);
}
