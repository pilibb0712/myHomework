package blImpl.bill.approveBillBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.convertors.FinanceBillsPOVOConvertor;

import assistant.convertors.UserInfoPOVOConvertor;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import blInteract.billBlInteract.BillBlInteractServiceFactory;
import blInteract.billBlInteract.PaymentBillBlService;
import blInteract.financeInteract.AutoAddEntryService;
import blInteract.financeInteract.BankAccountSumUpdateBlService;
import blInteract.financeInteract.FinanceBlFactory;
import blService.billService.approveBillBlService.ApprovePaymentBillBlService;
import po.PaymentBillPO;
import po.UserInfoPO;
import vo.PaymentBillVO;
import vo.SingleEntryVO;
import vo.UserInfoVO;

/**
 * 单据的审批，包括得到需要审批单据列表，和pass deny单据
 * @author 张傲  161250193
 * @version 2017.12.3
 */

public class ApprovePaymentBillBlController implements ApprovePaymentBillBlService{

    private PaymentBillBlService service 
    	= new BillBlInteractServiceFactory().getPaymentBillBlService();//现金费用单相关操作
	
    /**
	 * 得到需要审批的单据列表
	 * @return ArrayList<PaymentBillVO> 需要审批的单据列表
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<PaymentBillVO> getBillsList() throws RemoteException {
		ArrayList<PaymentBillPO> list = service.getBillListByState(BillStateEnum.TBD);
		ArrayList<PaymentBillVO> targetList =new ArrayList<PaymentBillVO>();
		for(PaymentBillPO po: list){
			PaymentBillVO vo = FinanceBillsPOVOConvertor.paymentBillPOtoVO(po) ;
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
	public boolean passBill(PaymentBillVO billVO) throws RemoteException {
		PaymentBillPO billPO = FinanceBillsPOVOConvertor.paymentBillVOtoPO(billVO);
		service.passBill(billPO);
		
		//修改银行账户
		BankAccountSumUpdateBlService bankAccountSumUpdateBlService= new FinanceBlFactory().getBankAccountSumUpdateBlService();
		bankAccountSumUpdateBlService.updateRemainingSum(billPO.getBankAccount(),-billPO.getSum());
		
		AutoAddEntryService entryService=new FinanceBlFactory().getAutoAddEntryService();
		Date date=new Date();
		String year=date.getDate().substring(0, 4);
		SingleEntryVO single=new SingleEntryVO(year, date.getDate(),"付款","-"+String.valueOf(billPO.getSum()));
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
	public boolean denyBill(PaymentBillVO billVO) throws RemoteException {
		PaymentBillPO billPO = FinanceBillsPOVOConvertor.paymentBillVOtoPO(billVO);
		service.denyBill(billPO);
		return true;
	}

}
