/**
 * 
 */
package blInteract.customerInteract;

import java.util.ArrayList;

import vo.CustomerInfoVO;

/**
 * 得到所有客户的部分信息（供财务人员）
 * @author 王宁一
 * @version 2017.12.18
 *
 */
public interface GetCustomerService {

	/**
	 * 给财务人员，得到所有的客户的部分信息
	 * @return ArrayList<CustomerInfoVO>
	 */
	public ArrayList<CustomerInfoVO> GetCustomers();
}
