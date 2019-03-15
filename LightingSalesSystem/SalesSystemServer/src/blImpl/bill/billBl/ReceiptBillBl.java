package blImpl.bill.billBl;

import java.util.ArrayList;
import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blInteract.billBlInteract.ReceiptBillBlService;
import dataImpl.billData.CreateBillData;
import dataImpl.billData.SaveAsDraftBillData;
import dataService.billDataService.CreateBillDataService;
import dataService.billDataService.SaveAsDraftBillDataService;
import po.BillPO;
import po.ReceiptBillPO;
import po.UserInfoPO;

/**
 * ReceiptBillBl逻辑类，提供增删改查，草稿创建等相关收款单操作
 * @author 张傲  161250193
 * @version 2017.12.1
 */

public class ReceiptBillBl implements ReceiptBillBlService{
	
	static final BillCategoryEnum BILL_CATEGORY_ENUM = BillCategoryEnum.RECEIPT_BILL;//当前单据的种类
	static final String BILL_NAME = BILL_CATEGORY_ENUM.toString();//当前单据的名称
	
	GetBillBl getBillBl = new GetBillBl();//查询单据的逻辑层操作
	
	private CreateBillDataService createBillDataService =  new CreateBillData();//数据层对象
	private SaveAsDraftBillDataService saveAsDraftBillDataService = new SaveAsDraftBillData();//数据层对象
	
	/**
	 * 创建一张新的收款单。<br/>
	 * @param ReceiptBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	 @Override
	public boolean createBill(ReceiptBillPO bill) {
		Date nowDate =new Date();
		bill.setCreateDate(nowDate);//设置创建日期
		bill.setCategoryEnum(BILL_CATEGORY_ENUM);//设置单据类型，以防万一
		bill.setBillStateEnum(BillStateEnum.TBD);//设置单据状态为未审批
		SetBillIdBl.getInstance().setBillIdAndUpdateRecord(bill);//设置Id并更新Id记录
		boolean isSuccessedCreation = createBillDataService.createReceiptBill(bill);
		return isSuccessedCreation;
	}
	
	/**
	 * 创建一张新的收款单草稿。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param ReceiptBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	 @Override
	public boolean saveAsDraftBill(ReceiptBillPO bill) {
		Date nowDate =new Date();
		bill.setCreateDate(nowDate);//设置创建日期
		bill.setCategoryEnum(BILL_CATEGORY_ENUM);//设置单据类型，以防万一
		bill.setBillStateEnum(BillStateEnum.DRAFT);//设置单据状态为草稿
		SetBillIdBl.getInstance().setDraftBillId(bill);//设置草稿的Id
		return saveAsDraftBillDataService.saveAsDraftReceiptBill(bill);
	}
	
	/**
	 * 对已经存在草稿进行内容更新
	 * @param ReceiptBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	 @Override
	public boolean updateDraftBill(ReceiptBillPO bill){
		return saveAsDraftBill(bill);
	}
	
	/**
	 * 通过Id删除一张收款单
	 * @param String billId 需要删除的草稿单据Id
	 * @return boolean 是否删除成功
	 */
	 @Override
	 public boolean deleteDraftBill(String billId) {
		 System.out.println(billId);
		return DeleteDraftBillBl.getInstance().deleteDraftBill(BILL_NAME, billId);
	}
	 
	 /**
	  * 注意：如果不存在该单据草稿，则会提交失败
	  * 根据单据Id提交一张已存在的草稿收款单去审批
	 * @param String billId 需要由草稿提交为审批模式的单据Id
	 * @return boolean 是否提交成功
	 * @
	 */
	 @Override
	public boolean submitBill(String billId){
		ReceiptBillPO bill = getBillById(billId);
		if(bill==null){return false;}
		deleteDraftBill(billId);//删除该草稿
		createBill(bill);//创建该收款单的未审批状态单据
		return true;
	}
		
	/**
	 * 根据Id通过一份收款单的审批
	 * @param String billId 需要审批通过的单据Id
	 * @return boolean 是否通过审批成功
	 */
	 @Override
	public boolean passBill(ReceiptBillPO bill) {
		return ProcessBillBl.getInstance().passBill(bill);
	}

	/**
	 * @param String billId 需要拒绝审批通过的单据Id
	 * @return boolean 是否拒绝成功
	 */
	 @Override
	public boolean denyBill(ReceiptBillPO bill) {
		return ProcessBillBl.getInstance().denyBill(bill);
	}

	/**
	 * @param String billId 被完成的单据Id
	 * @return boolean 是否成功标记成完成状态
	 */
	 @Override
	public boolean doneBill(ReceiptBillPO bill) {
		return ProcessBillBl.getInstance().doneBill(bill);
	}

	/**
	 * @param String billId 需要被假删的单据Id
	 * @return boolean 是否成功假删
	 */
	 @Override
	public boolean hideBill(ReceiptBillPO bill) {
		return	ProcessBillBl.getInstance().hideBill(bill);
	}
	
	/**
	 * 根据单据Id读取一张收款单。<br/>
	 * @param billId 要读取的收款单Id
	 * @return BillPo读取的单据
	 */
	 @Override
	public ReceiptBillPO getBillById(String billId) {
		ReceiptBillPO bill = (ReceiptBillPO) getBillBl.getBillByNameAndId(BILL_NAME, billId);
		return bill;
	} 
	
	/**
	 * 根据单据名称和状态读取一系列收款单。<br/>
	 * @param billState 要读取的收款单状态
	 * @return ArrayList<ReceiptBillPO>读取的收款单列表
	 */
	 @Override
	public ArrayList<ReceiptBillPO> getBillListByState(BillStateEnum billState) {
		
		ArrayList<BillPO> bills = getBillBl.getBillListByStateAndName(billState.toString(),BILL_NAME);
		ArrayList<ReceiptBillPO> targetBills = new ArrayList<ReceiptBillPO>();//需要返回的单据清单
		//显式强制类型转换并筛选
		for(BillPO billPO : bills){
			targetBills.add((ReceiptBillPO)billPO);
		}
		return targetBills;
	}
	
	/**
	 * 根据单据创建者读取一系列收款单草稿。<br/>
	 * 专门用于billState部分，比如财务人员读取自己制定的现金费用单草稿
	 * @param userName 筛选的单据创建者的名字
	 * @param userId 筛选的单据创建者的Id
	 * @param userRole 筛选的单据创建者的职位
	 * @return ArrayList<ReceiptBillPO>读取的收款单草稿列表
	 */
	 @Override
	public ArrayList<ReceiptBillPO> getDraftBillsListByCreater(UserInfoPO targetUserInfo) {
		ArrayList<ReceiptBillPO> bills = getBillListByState(BillStateEnum.DRAFT);
		ArrayList<ReceiptBillPO> targetBills = new ArrayList<ReceiptBillPO>();//需要返回的单据清单
		//显式强制类型转换并筛选
		for(ReceiptBillPO billPO : bills){
			UserInfoPO user = billPO.getCreater();
			if(user==null){
				System.out.println("不存在创建者");
				continue;
			}
			if(user.equals(targetUserInfo)){
				targetBills.add((ReceiptBillPO)billPO);
			}
		}
		return targetBills;
	}
	
	/**
	 * 根据单据创建者读取一系列未审批收款单。<br/>
	 * 专门用于billState部分，比如财务人员读取自己制定但是未被审批的单据
	 * @param userName 筛选的单据创建者的名字
	 * @param userId 筛选的单据创建者的Id
	 * @param userRole 筛选的单据创建者的职位
	 * @return ArrayList<ReceiptBillPO>需要读取的未审批收款单列表
	 */
	 @Override
	public ArrayList<ReceiptBillPO> getTBDBillsListByCreater(UserInfoPO targetUserInfo) {
		ArrayList<ReceiptBillPO> bills = getBillListByState(BillStateEnum.TBD);
		ArrayList<ReceiptBillPO> targetBills = new ArrayList<ReceiptBillPO>();//需要返回的单据清单
		//显式强制类型转换并筛选
		for(ReceiptBillPO billPO : bills){
			UserInfoPO user = billPO.getCreater();
			if(user==null){
				System.out.println("不存在创建者");
				continue;
			}
			if(user.equals(targetUserInfo)){
				targetBills.add((ReceiptBillPO)billPO);
			}
		}
		return targetBills;
	}
	
	/**
	 * 根据单据创建者读取一系列需要执行的收款单。<br/>
	 * 专门用于billState部分,比如财务人员读取需要自己要去执行的单据
	 * @param userName 筛选的单据创建者的名字
	 * @param userId 筛选的单据创建者的Id
	 * @param userRole 筛选的单据创建者的职位
	 * @return ArrayList<ReceiptBillPO>读取的需要执行的收款单列表
	 */
	 @Override
	public ArrayList<ReceiptBillPO> getToDoBillsListByExecutor(UserInfoPO targetUserInfo) {
		ArrayList<ReceiptBillPO> bills = getBillListByState(BillStateEnum.TODO);
		ArrayList<ReceiptBillPO> targetBills = new ArrayList<ReceiptBillPO>();//需要返回的单据清单
		//显式强制类型转换并筛选
		for(ReceiptBillPO billPO : bills){
			UserInfoPO user = billPO.getExecutor();
			if(user==null){
				System.out.println("不存在执行者");
				continue;
			}
			if(user.equals(targetUserInfo)){
				targetBills.add((ReceiptBillPO)billPO);
			}
		}
		return targetBills;
	}
	
	/**
	 * 根据单据创建者读取一系列被拒绝的收款单。<br/>
	 * 专门用于billState部分,比如财务人员读取需要自己提交但是审批未通过的单据列表
	 * @param userName 筛选的单据创建者的名字
	 * @param userId 筛选的单据创建者的Id
	 * @param userRole 筛选的单据创建者的职位
	 * @return ArrayList<ReceiptBillPO>读取的审批未通过的收款单列表
	 */
	 @Override
	public ArrayList<ReceiptBillPO> getDeniedBillsListByCreater(UserInfoPO targetUserInfo) {
		ArrayList<ReceiptBillPO> bills = getBillListByState(BillStateEnum.DENIED);
		ArrayList<ReceiptBillPO> targetBills = new ArrayList<ReceiptBillPO>();//需要返回的单据清单
		//显式强制类型转换并筛选
		for(ReceiptBillPO billPO : bills){
			UserInfoPO user = billPO.getCreater();
			if(user==null){
				System.out.println("不存在创建者");
				continue;
			}
			if(user.equals(targetUserInfo)){
				targetBills.add((ReceiptBillPO)billPO);
			}
		}
		return targetBills;
	}


	/**
	 * 根据单据执行者读取一系列被完成的收款单。<br/>
	 * 专门用于billState部分,比如财务人员读取已经被自己完成的单据列表
	 * @param userName 筛选的单据创建者的名字
	 * @param userId 筛选的单据创建者的Id
	 * @param userRole 筛选的单据创建者的职位
	 * @return ArrayList<ReceiptBillPO>读取的已经完成的收款单列表
	 */
	 @Override
	public ArrayList<ReceiptBillPO> getDoneBillsListByExecutor(UserInfoPO targetUserInfo) {
		ArrayList<ReceiptBillPO> bills = getBillListByState(BillStateEnum.DONE);
		ArrayList<ReceiptBillPO> targetBills = new ArrayList<ReceiptBillPO>();//需要返回的单据清单
		//显式强制类型转换并筛选
		for(ReceiptBillPO billPO : bills){
			UserInfoPO user = billPO.getExecutor();
			if(user==null){
				System.out.println("不存在执行者");
				continue;
			}
			if(user.equals(targetUserInfo)){
				targetBills.add((ReceiptBillPO)billPO);
			}
		}
		return targetBills;
	}
	
}
