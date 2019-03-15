package blImpl.listBl;

import java.util.ArrayList;
import assistant.type.BillStateEnum;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.SalesBillBlService;
import blInteract.commodityInteract.ClassificationListService;
import blInteract.commodityInteract.CommodityBlInteractServiceFactory;
import blInteract.commodityInteract.GoodsListService;
import blInteract.customerInteract.CustomerServiceFactory;
import blInteract.customerInteract.GetCustomerService;
import blInteract.userInteract.GetUserService;
import blInteract.userInteract.GetUserServiceFactory;
import po.SalesBillPO;
import po.SalesDetailsListPO;
import vo.ClassificationVO;
import vo.CustomerInfoVO;
import vo.GoodsVO;
import vo.UserVO;

/**
 * 销售明细表逻辑实现
 * @author guxinyu
 * @version 12.23
 *
 */
public class SalesDetailsListBl {
	private static SalesDetailsListBl bl;//单件对象
	
	private BillBlInteractServiceFactory billFactory=new BillBlInteractServiceFactory();
	private SalesBillBlService billService=billFactory.getSalesBillBlService();
	
	private GetUserServiceFactory userFactory=new GetUserServiceFactory();
	private GetUserService userService=userFactory.getGetUserService();
	
	private CustomerServiceFactory customerFactory=new CustomerServiceFactory();
	private GetCustomerService customerService=customerFactory.getGetCustomerService();
	
	private CommodityBlInteractServiceFactory commodityFactory=new CommodityBlInteractServiceFactory();
	private ClassificationListService commodityClassificationService=commodityFactory.getClassificationListBlService();
	private GoodsListService commodityService=commodityFactory.getGoodsListBlService();
	
	private SalesDetailsListBl(){
		
	}
	
	public static SalesDetailsListBl getInstance(){
		if(bl==null)
			bl=new SalesDetailsListBl();
		
		return bl;
	}
	
	public SalesDetailsListPO getSalesDetailsList(){
		ArrayList<SalesBillPO> billPOs=billService.getBillListByState(BillStateEnum.DONE);
		
		SalesDetailsListPO po=new SalesDetailsListPO(billPOs);
		
		return po;
	}
	
	public ArrayList<UserVO> getWareKeeperList(){
		ArrayList<UserVO> vo=userService.getWarekeeper();
		
		return vo;
	}
	
	public ArrayList<UserVO> getSalesmanList(){
		ArrayList<UserVO> vo=userService.getSalesman();
		
		return vo;
	}
	
	public ArrayList<UserVO> getFinancialStaffList(){
		ArrayList<UserVO> vo=userService.getFianceStaff();
		
		return vo;
	}
	
	public ArrayList<UserVO> getManagerList(){
		ArrayList<UserVO> vo=userService.getManager();
		
		return vo;
	}
	
	public ArrayList<CustomerInfoVO> getCustomers(){
		ArrayList<CustomerInfoVO> vo=customerService.GetCustomers();
		
		return vo;
	}
	
	public ArrayList<ClassificationVO> getCommodityClassificationList(){
		ArrayList<ClassificationVO> classificationVOs=commodityClassificationService.getClassificationList();
		
		return classificationVOs;
	}
	
	public ArrayList<GoodsVO> getCommodityList(){
		ArrayList<GoodsVO> commodityVOs=commodityService.getGoodsList();
		
		return commodityVOs;
	}
}
