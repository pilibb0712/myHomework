package blInteract.billBlInteract;

import java.util.ArrayList;

import assistant.type.BillStateEnum;
import po.ReceiptBillPO;
import po.UserInfoPO;

public interface ReceiptBillBlService {
	/**
	 * 创建一张新的收款单。<br/>
	 * @param ReceiptBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	public boolean createBill(ReceiptBillPO bill);
	
	/**
	 * 创建一张新的收款单草稿。<br/>
	 * 若已经存在该草稿，则覆盖上次内容
	 * @param ReceiptBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	public boolean saveAsDraftBill(ReceiptBillPO bill) ;
	
	/**
	 * 对已经存在草稿进行内容更新
	 * @param ReceiptBillPO bill 需要新建的单据 
	 * @return boolean 是否增加成功
	 */
	public boolean updateDraftBill(ReceiptBillPO bill);
	
	/**
	 * 通过Id删除一张退货进货单
	 * @param String billId 需要删除的草稿单据Id
	 * @return boolean 是否删除成功
	 */
	 public boolean deleteDraftBill(String billId);
	 
	 /**
	  * 注意：如果不存在该单据草稿，则会提交失败
	  * 根据单据Id提交一张已存在的草稿收款单去审批
	 * @param String billId 需要由草稿提交为审批模式的单据Id
	 * @return boolean 是否提交成功
	 * @
	 */
	public boolean submitBill(String billId);
		
	/**
	 * 根据Id通过一份收款单的审批
	 * @param ReceiptBillPO bill 需要审批通过的单据
	 * @return boolean 是否通过审批成功
	 */
	public boolean passBill(ReceiptBillPO bill) ;

	/**
	 * @param ReceiptBillPO bill 需要拒绝审批通过的单据
	 * @return boolean 是否拒绝成功
	 */
	public boolean denyBill(ReceiptBillPO bill);

	/**
	 * @param ReceiptBillPO bill 被完成的单据
	 * @return boolean 是否成功标记成完成状态
	 */
	public boolean doneBill(ReceiptBillPO bill) ;

	/**
	 * @param ReceiptBillPO bill 需要被假删的单据
	 * @return boolean 是否成功假删
	 */
	public boolean hideBill(ReceiptBillPO bill) ;

	/**
	 * 根据单据Id读取一张收款单。<br/>
	 * @param billId 要读取的进货单Id退货
	 * @return BillPo读取的单据
	 */
	public ReceiptBillPO getBillById(String billId);
	
	/**
	 * 根据单据名称和状态读取一系列收款单。<br/>
	 * @param billState 要读取的收款单状态
	 * @return ArrayList<ReceiptBillPO>读取的进货单列表退货
	 */
	public ArrayList<ReceiptBillPO> getBillListByState(BillStateEnum billState) ;
	
	/**
	 * 根据单据创建者读取一系列收款单草稿。<br/>
	 * 专门用于billState部分，比如财务人员读取自己制定的现金费用单草稿
	 * @param userName 筛选的单据创建者的名字
	 * @param userId 筛选的单据创建者的Id
	 * @param userRole 筛选的单据创建者的职位
	 * @return ArrayList<ReceiptBillPO>读取的进货单草稿列表退货
	 */
	public ArrayList<ReceiptBillPO> getDraftBillsListByCreater(UserInfoPO targetUserInfo);
	
	/**
	 * 根据单据创建者读取一系列未审批收款单。<br/>
	 * 专门用于billState部分，比如财务人员读取自己制定但是未被审批的单据
	 * @param userName 筛选的单据创建者的名字
	 * @param userId 筛选的单据创建者的Id
	 * @param userRole 筛选的单据创建者的职位
	 * @return ArrayList<ReceiptBillPO>需要读取的未审批进货单列表退货
	 */
	public ArrayList<ReceiptBillPO> getTBDBillsListByCreater(UserInfoPO targetUserInfo) ;
	
	/**
	 * 根据单据创建者读取一系列需要执行的收款单。<br/>
	 * 专门用于billState部分,比如财务人员读取需要自己要去执行的单据
	 * @param userName 筛选的单据创建者的名字
	 * @param userId 筛选的单据创建者的Id
	 * @param userRole 筛选的单据创建者的职位
	 * @return ArrayList<ReceiptBillPO>读取的需要执行的进货单列表退货
	 */
	public ArrayList<ReceiptBillPO> getToDoBillsListByExecutor(UserInfoPO targetUserInfo);
	
	/**
	 * 根据单据创建者读取一系列被拒绝的收款单。<br/>
	 * 专门用于billState部分,比如财务人员读取需要自己提交但是审批未通过的单据列表
	 * @param userName 筛选的单据创建者的名字
	 * @param userId 筛选的单据创建者的Id
	 * @param userRole 筛选的单据创建者的职位
	 * @return ArrayList<ReceiptBillPO>读取的审批未通过的进货单列表退货
	 */
	public ArrayList<ReceiptBillPO> getDeniedBillsListByCreater(UserInfoPO targetUserInfo);


	/**
	 * 根据单据执行者读取一系列被完成的收款单。<br/>
	 * 专门用于billState部分,比如财务人员读取已经被自己完成的单据列表
	 * @param userName 筛选的单据创建者的名字
	 * @param userId 筛选的单据创建者的Id
	 * @param userRole 筛选的单据创建者的职位
	 * @return ArrayList<ReceiptBillPO>读取的已经完成的进货单列表退货
	 */
	public ArrayList<ReceiptBillPO> getDoneBillsListByExecutor(UserInfoPO targetUserInfo);
	
}
