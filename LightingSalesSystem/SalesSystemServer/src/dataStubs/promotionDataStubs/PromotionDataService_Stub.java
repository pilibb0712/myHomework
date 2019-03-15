package dataStubs.promotionDataStubs;

import java.util.ArrayList;

import assistant.type.CustomerLevelEnum;
import assistant.utility.Date;

import dataService.promotionDataService.PromotionDataService;
import po.ComboStrategyPO;
import po.PromotionStrategyPO;
import po.TotalStrategyPO;
import po.UserStrategyPO;
import vo.*;

public class PromotionDataService_Stub implements PromotionDataService {

	@Override
	public ArrayList<UserStrategyPO> getUserStrategyList() {
		return null;
	}

	@Override
	public ArrayList<TotalStrategyPO> getTotalStrategyList() {
		
		return null;
	}

	@Override
	public ArrayList<ComboStrategyPO> getComboStrategyList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteStrategy(String strategyType, String strategyId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStrategy(String strategyType, String strategyId, PromotionStrategyPO newPromotionStrategyPO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUserStrategy(UserStrategyPO strategy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addTotalStrategy(TotalStrategyPO strategy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addComboStrategy(ComboStrategyPO strategy) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
