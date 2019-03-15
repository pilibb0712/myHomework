package dataImpl.promotionData;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import assistant.type.StrategyCategoryEnum;
import assistant.utility.DeleteFileUtility;
import assistant.utility.ReadAndWritePromotionPOSerUtility;
import dataService.promotionDataService.PromotionDataService;
import po.BillPO;
import po.ComboStrategyPO;
import po.PromotionStrategyPO;
import po.TotalStrategyPO;
import po.UserStrategyPO;
import vo.ComboStrategyVO;
import vo.PromotionStrategyVO;
import vo.TotalStrategyVO;
import vo.UserStrategyVO;

/**
 * @author zhangao 161250193
 * @version 2017.11.24
 * 促销策略的增删改查
 */

public class PromotionData implements PromotionDataService{
	
	private static final String ROOT_PATH = "data"+File.separator+"promotion"+File.separator;//根目录地址
	private static final String USER_STRATEGY_PATH 
		= ROOT_PATH +StrategyCategoryEnum.USRESTRATEGY.toString()
		+File.separator;//用户促销策略路径
	private static final String TOTAL_STRATEGY_PATH 
		= ROOT_PATH +StrategyCategoryEnum.TOTALSTRATEGY.toString()
		+File.separator;//用户促销策略路径
	private static final String COMBO_STRATEGY_PATH 
		= ROOT_PATH +StrategyCategoryEnum.COMBOSTRATEGY.toString()
		+File.separator;//用户促销策略路径
	private static final String FILE_EXTENSION = ".ser";
	
	
	/**
	 * 得到所有现有的用户促销策略清单
	 * @return ArrayList<UserStrategyPO> 用户促销策略清单
	 */
	@Override
	public ArrayList<UserStrategyPO> getUserStrategyList() {
		ArrayList<UserStrategyPO> list = new ArrayList<UserStrategyPO>();
		String path = USER_STRATEGY_PATH;
		File file = new File(path);
		String[] filesName = file.list();//所用用户促销策略文件名
		for(String eachFileName:filesName){
			String filePath = path + eachFileName;
			UserStrategyPO po = (UserStrategyPO)readObj(filePath);
			list.add(po);
		}
		return list;
	}

	
	/**
	 * 得到所有现有的总价促销策略清单
	 * @return ArrayList<TotalStrategyPO> 总价促销策略清单
	 */
	@Override
	public ArrayList<TotalStrategyPO> getTotalStrategyList() {
		ArrayList<TotalStrategyPO> list = new ArrayList<TotalStrategyPO>();
		String path = TOTAL_STRATEGY_PATH;
		File file = new File(path);
		String[] filesName = file.list();//所用用户促销策略文件名
		for(String eachFileName:filesName){
			String filePath = path + eachFileName;
			TotalStrategyPO po = (TotalStrategyPO)readObj(filePath);
			list.add(po);
		}
		return list;
	}

	
	/**
	 * 得到所有现有的总价促销策略清单
	 * @return ArrayList<TotalStrategyPO> 总价促销策略清单
	 */
	@Override
	public ArrayList<ComboStrategyPO> getComboStrategyList() {
		ArrayList<ComboStrategyPO> list = new ArrayList<ComboStrategyPO>();
		String path = COMBO_STRATEGY_PATH;
		File file = new File(path);
		String[] filesName = file.list();//所用用户促销策略文件名
		for(String eachFileName:filesName){
			String filePath = path + eachFileName;
			ComboStrategyPO po = (ComboStrategyPO)readObj(filePath);
			list.add(po);
		}
		return list;
	}

	/**
	 * 删除某一个促销策略
	 * @param String strategyType 需要更新的促销策略的类型
	 * @param String strategyId 需要更新的促销策略的Id
	 * @return boolean 是否删除成功
	 */
	@Override
	public boolean deleteStrategy(String strategyType, String strategyId) {
		String path = ROOT_PATH + strategyType + File.separator + strategyId + FILE_EXTENSION; 
		return DeleteFileUtility.deleteFile(path);//删除文件
	}

	/**
	 * 更新当前的促销策略
	 * @param String strategyType 需要更新的促销策略的类型
	 * @param String strategyId 需要更新的促销策略的Id
	 * @return boolean 是否更新成功
	 */
	@Override
	public boolean updateStrategy(String strategyType, String strategyId, PromotionStrategyPO newPromotionStrategyPO) {
		String path = ROOT_PATH + strategyType + File.separator + strategyId + FILE_EXTENSION; 
		//System.out.println(path);
		return writeObj(newPromotionStrategyPO, path);
	}

	/**
	 * 增加一份新的用户促销策略
	 * @param UserStrategyPO strategy 用户促销策略
	 * @return boolean 是否新增成功
	 */
	@Override
	public boolean addUserStrategy(UserStrategyPO strategy) {
		String path = "";
		path += USER_STRATEGY_PATH + strategy.getId() + FILE_EXTENSION;
		//System.out.println(path);
		return writeObj(strategy, path);
	}

	/**
	 * 增加一份新的总价促销策略
	 * @param TotalStrategyPO strategy 总价促销策略
	 * @return boolean 是否新增成功
	 */
	@Override
	public boolean addTotalStrategy(TotalStrategyPO strategy) {
		String path = "";
		path += TOTAL_STRATEGY_PATH + strategy.getId() + FILE_EXTENSION;
		return writeObj(strategy, path);
	}

	/**
	 * 增加一份新的组合促销策略
	 * @param ComboStrategyPO strategy 组合促销策略
	 * @return boolean 是否新增成功
	 */
	@Override
	public boolean addComboStrategy(ComboStrategyPO strategy) {
		String path = "";
		path += COMBO_STRATEGY_PATH + strategy.getId() + FILE_EXTENSION;
		return writeObj(strategy, path);
	}
	
	/**
	 * 写promotion的ser文件。<br/>
	 * @param PromotionStrategyPO promotion 需要写的促销策略 
	 * @return boolean 是否写文件成功
	 */
	public boolean writeObj(PromotionStrategyPO promotion,String path){
		return ReadAndWritePromotionPOSerUtility.writeObj(promotion, path);
	}
	
	/**
	 * 读取一个促销策略。<br/>
	 * @param path 要读取的促销策略路径
	 * @return PromotionStrategyPO读取的促销策略
	 */
	public  PromotionStrategyPO readObj(String path){
		return ReadAndWritePromotionPOSerUtility.readObj(path);
	}
}
