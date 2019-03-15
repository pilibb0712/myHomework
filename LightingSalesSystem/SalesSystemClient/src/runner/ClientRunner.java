package runner;

import rmi.remoteHelper.RemoteHelperFactory;

public class ClientRunner {
	public static void main(String [] args){
		//RemoteHelperFactory.getApproveBillRemoteHelper().getApproveGiftBillBlService();
		RemoteHelperFactory.getPromotionRemoteHelper().getUserStrategyBlService();
		System.out.println("promotionlinked");
	}
}
