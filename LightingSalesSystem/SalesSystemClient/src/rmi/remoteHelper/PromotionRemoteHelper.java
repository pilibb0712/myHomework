package rmi.remoteHelper;

import blService.promotionBlService.ComboStrategyBlService;
import blService.promotionBlService.TotalStrategyBlService;
import blService.promotionBlService.UserStrategyBlService;
import rmi.linker.promotionServiceLinker.ComboStrategyServiceLinker;
import rmi.linker.promotionServiceLinker.TotalStrategyServiceLinker;
import rmi.linker.promotionServiceLinker.UserStrategyServiceLinker;

public class PromotionRemoteHelper {
	private static PromotionRemoteHelper remoteHelper =new PromotionRemoteHelper();
	private PromotionRemoteHelper(){
		
	}
	
	public static PromotionRemoteHelper getInstance(){
		return remoteHelper;
	}
	
	public UserStrategyBlService getUserStrategyBlService(){
		return UserStrategyServiceLinker.getInstance().getUserStrategyBlService();
	}
	
	public TotalStrategyBlService getTotalStrategyBlService(){
		return TotalStrategyServiceLinker.getInstance().getTotalStrategyBlService();
	}
	
	public ComboStrategyBlService getComboStrategyBlService(){
		return ComboStrategyServiceLinker.getInstance().getComboStrategyBlService();
	}
}
