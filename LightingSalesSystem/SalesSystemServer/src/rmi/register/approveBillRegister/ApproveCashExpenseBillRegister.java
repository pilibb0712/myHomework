package rmi.register.approveBillRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.approveBillRemoteObject.ApproveCashExpenseBillRemoteObject;
import rmi.dataRemoteObject.approveBillRemoteObject.ApproveGiftBillRemoteObject;

/**
 * 网络通信的注册器
 * @author zhangao
 * @version 2017.12.28
 * */
public class ApproveCashExpenseBillRegister {
	
	public ApproveCashExpenseBillRegister(){
		
	}
	
	/**
	 * 该服务的注册方法
	 * */
	public void register(){
		ApproveCashExpenseBillRemoteObject remoteObject;
		try {
			remoteObject = new ApproveCashExpenseBillRemoteObject();
			LocateRegistry.createRegistry(7701);
			Naming.bind(RmiPort.PATH+"7701/ApproveCashExpenseBillRemoteObject",
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
