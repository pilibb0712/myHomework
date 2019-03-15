package dataService.customerDataService;

import java.util.ArrayList;

import po.CustomerPO;
/**
 * 
 * @author 王宁一 161250140
 * @version 2017.11.7
 */

public interface CustomerDataService{

	/**
	 * 新增客户
	 * @param CustomerPO po需要新增的客户
	 * @return boolean true表示成功
	 */
	public boolean insert(CustomerPO po);
	
	/**
	 * 删除一个客户，要求姓名和ID完全匹配
	 * @param number
	 * @param name
	 * @return boolean true表示成功
	 */
	public boolean delete(String number, String name);
	
	/**
	 * 更新一个客户的信息
	 * @param po
	 * @return boolean true表示成功
	 */
	public boolean update(CustomerPO po);
	
	/**
	 * 查询客户信息，支持关键字查找
	 * @param name
	 * @return ArrayList<CustomerPO>
	 */
	public ArrayList<CustomerPO> find(String name);
	
	/**
	 * 得到所有客户信息
	 * @return ArrayList<CustomerPO>
	 */
	public ArrayList<CustomerPO> getAll();
}