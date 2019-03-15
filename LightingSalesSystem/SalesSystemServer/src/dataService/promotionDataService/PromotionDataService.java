package dataService.promotionDataService;

import java.util.ArrayList;

import po.ComboStrategyPO;
import po.PromotionStrategyPO;
import po.TotalStrategyPO;
import po.UserStrategyPO;


public interface PromotionDataService{
	
	 public ArrayList<UserStrategyPO> getUserStrategyList();
	 public ArrayList<TotalStrategyPO> getTotalStrategyList();
	 public ArrayList<ComboStrategyPO> getComboStrategyList();
	 
	 public boolean deleteStrategy(String strategyType ,String strategyId);
	 
	 public boolean updateStrategy(String strategyType
			 , String strategyId
			 ,PromotionStrategyPO newPromotionStrategyPO);
	 
	 public boolean addUserStrategy(UserStrategyPO strategy);
	 public boolean addTotalStrategy(TotalStrategyPO strategy);
	 public boolean addComboStrategy(ComboStrategyPO strategy);
}
