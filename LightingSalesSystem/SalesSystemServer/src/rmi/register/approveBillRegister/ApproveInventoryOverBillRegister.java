 package rmi.register.approveBillRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.approveBillRemoteObject.ApproveInventoryOverBillRemoteObject;
/**
 * 网络通信的注册器
 * @author zhangao
 * @version 2017.12.28
 * */
public class ApproveInventoryOverBillRegister {
	public ApproveInventoryOverBillRegister(){
		
	}
	
	/**
	 * 该服务的注册方法
	 * */
	public void register(){
		ApproveInventoryOverBillRemoteObject remoteObject;
		try {
			remoteObject = new ApproveInventoryOverBillRemoteObject();
			LocateRegistry.createRegistry(7704);
			Naming.bind(RmiPort.PATH+"7704/ApproveInventoryOverBillRemoteObject",
					remoteObject);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
