package blImpl.bill.approveBillBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.FinanceBillsPOVOConvertor;
import assistant.convertors.UserInfoPOVOConvertor;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.ReceiptBillBlService;
import blInteract.financeInteract.AutoAddEntryService;
import blInteract.financeInteract.BankAccountSumUpdateBlService;
import blInteract.financeInteract.FinanceBlFactory;
import blService.billService.approveBillBlService.ApproveReceiptBillBlService;
import po.ReceiptBillPO;
import po.UserInfoPO;
import vo.ReceiptBillVO;
import vo.SingleEntryVO;
import vo.UserInfoVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */

public class ApproveReceiptBillBlController implements ApproveReceiptBillBlService{

    private ReceiptBillBlService service 
    	= new BillBlInteractServiceFactory().getReceiptBillBlService();//现金费用单相关操作
	
    /**
	 * 得到需要审批的单据列表
	 * @return ArrayList<ReceiptBillVO> 需要审批的单据列表
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<ReceiptBillVO> getBillsList() throws RemoteException {
		ArrayList<ReceiptBillPO> list = service.getBillListByState(BillStateEnum.TBD);
		ArrayList<ReceiptBillVO> targetList =new ArrayList<ReceiptBillVO>();
		for(ReceiptBillPO po: list){
			ReceiptBillVO vo = FinanceBillsPOVOConvertor.receiptBillPOtoVO(po) ;
			targetList.add(vo);
		}
		return targetList;
	}

	  /**
	  * 根据Id通过单据的审批
	  * @param String billId单据的Id
	  * @param UserInfoVO approver 审批人信息
	  * @param String approverComment 审批人的批注 
	  * @throws RemoteException
	  */
	@Override
	public boolean passBill(ReceiptBillVO billVO) throws RemoteException {
		ReceiptBillPO billPO = FinanceBillsPOVOConvertor.receiptBillVOtoPO(billVO);
		service.passBill(billPO);
		
		//修改银行账户
		BankAccountSumUpdateBlService bankAccountSumUpdateBlService= new FinanceBlFactory().getBankAccountSumUpdateBlService();
		bankAccountSumUpdateBlService.updateRemainingSum(billPO.getBankAccount(),billPO.getSum());
		
		AutoAddEntryService entryService=new FinanceBlFactory().getAutoAddEntryService();
		Date date=new Date();
		String year=date.getDate().substring(0, 4);
		SingleEntryVO single=new SingleEntryVO(year, date.getDate(),"收款","+"+String.valueOf(billPO.getSum()));
		entryService.autoAddEntry(single);
		
		return true;
	}

	 /**
	  * 根据Id拒绝通过单据的审批
	  * @param String billId单据的Id
	  * @param UserInfoVO approver 审批人信息
	  * @param String approverComment 审批人的批注 
	  * @throws RemoteException
	  */
	@Override
	public boolean denyBill(ReceiptBillVO billVO) throws RemoteException {
		ReceiptBillPO billPO = FinanceBillsPOVOConvertor.receiptBillVOtoPO(billVO);
		service.denyBill(billPO);
		return true;
	}

}
