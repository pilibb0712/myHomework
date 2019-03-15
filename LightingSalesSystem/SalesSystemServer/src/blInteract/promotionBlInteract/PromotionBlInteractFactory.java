package blInteract.promotionBlInteract;

import blImpl.promotionBl.ComboStrategyForSalesManBl;
import blImpl.promotionBl.TotalStrategyForSalesManBl;
import blImpl.promotionBl.UserStrategyForSalesManBl;

public class PromotionBlInteractFactory {
	public static UserStrategyForSalesManService getUserStrategyForSalesManService(){
		return new UserStrategyForSalesManBl();
	}
	
	public static TotalStrategyForSalesManService getTotalStrategyForSalesManService(){
		return new TotalStrategyForSalesManBl();
	}
	
	public static ComboStrategyForSalesManService getComboStrategyForSalesManService(){
		return  new ComboStrategyForSalesManBl();
	}
}
