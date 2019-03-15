package rmi.remoteHelper;

import rmi.register.promotionRegister.ComboStrategyRegister;
import rmi.register.promotionRegister.TotalStrategyRegister;
import rmi.register.promotionRegister.UserStrategyRegister;

public class PromotionRemoteHelper {
	private UserStrategyRegister userStrategyRegister = new UserStrategyRegister();
	private ComboStrategyRegister comboStrategyRegister = new ComboStrategyRegister();
	private TotalStrategyRegister totalStrategyRegister = new TotalStrategyRegister();
	
	public PromotionRemoteHelper(){
		userStrategyRegister.register();
		comboStrategyRegister.register();
		totalStrategyRegister.register();
		System.out.println("promotion服务注册成功");
	}
}
