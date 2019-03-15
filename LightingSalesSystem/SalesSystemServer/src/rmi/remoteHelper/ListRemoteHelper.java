package rmi.remoteHelper;

import rmi.register.listRegister.BusinessConditionListRegister;
import rmi.register.listRegister.BusinessProcessListRegister;
import rmi.register.listRegister.SalesDetailsListRegister;

public class ListRemoteHelper {
	private BusinessConditionListRegister businessConditionListRegister=new BusinessConditionListRegister();
	private BusinessProcessListRegister businessProcessListRegister=new BusinessProcessListRegister();
	private SalesDetailsListRegister salesDetailsListRegister=new SalesDetailsListRegister();
	
	public ListRemoteHelper(){
		businessConditionListRegister.register();
		businessProcessListRegister.register();
		salesDetailsListRegister.register();
	}
}
