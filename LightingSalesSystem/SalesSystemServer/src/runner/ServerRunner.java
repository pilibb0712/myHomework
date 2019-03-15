package runner;

import rmi.remoteHelper.AdminRemoteHelper;
import rmi.remoteHelper.ApproveBillRemoteHelper;
import rmi.remoteHelper.BillStateRemoteHelper;
import rmi.remoteHelper.CommodityRemoteHelper;
import rmi.remoteHelper.CustomerRemoteHelper;
import rmi.remoteHelper.FinanceRemoteHelper;
import rmi.remoteHelper.ListRemoteHelper;
import rmi.remoteHelper.LogRemoteHelper;
import rmi.remoteHelper.PromotionRemoteHelper;
import rmi.remoteHelper.SalesRemoteHelper;
import rmi.remoteHelper.ViewBillRemoteHelper;

public class ServerRunner {
	public ServerRunner(){}
	
	public void run(){
		new AdminRemoteHelper();
		new ApproveBillRemoteHelper();
		new BillStateRemoteHelper();
		new CommodityRemoteHelper();
		new CustomerRemoteHelper();
		new FinanceRemoteHelper();
		new ListRemoteHelper();
		new LogRemoteHelper();
		new PromotionRemoteHelper();
		new SalesRemoteHelper();
		new ViewBillRemoteHelper();
	}
	
	public static void main(String [] args){
		ServerRunner serverRunner = new ServerRunner();
		serverRunner.run();
	}
	
}
