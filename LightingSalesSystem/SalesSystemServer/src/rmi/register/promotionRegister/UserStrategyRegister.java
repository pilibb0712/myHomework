package rmi.register.promotionRegister;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.config.RmiPort;
import rmi.dataRemoteObject.promotionRemoteObject.UserStrategyRemoteObject;

public class UserStrategyRegister {
	public UserStrategyRegister(){
		
	}
	
	public void register(){
		UserStrategyRemoteObject remoteObject;
		try {
			remoteObject = new UserStrategyRemoteObject();
			LocateRegistry.createRegistry(7101);
			Naming.bind(RmiPort.PATH+"7101/UserStrategyRemoteObject",
					remoteObject);
			System.out.println("用户促销策略服务注册成功");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
