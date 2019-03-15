package blImpl.promotionBl;

import assistant.utility.Date;
import po.PromotionStrategyPO;

/**
 * 设定促销策略的Id的单间类
 * @author zhangao 161250193
 * @version 2017.12.3
 */

public class SetPromotionIdBl {
	private static SetPromotionIdBl setPromotionIdBl;//单件对象
	
	/**
	 * 得到设置id单件对象
	 * @return SetPromotionBl 单件对象的引用
	 */
	public static SetPromotionIdBl getInstance(){
		if(setPromotionIdBl==null){
			setPromotionIdBl = new SetPromotionIdBl();
		}
		return setPromotionIdBl;
	}
	
	/**
	 * 设置促销策略的ID
	 */
	public void setId(PromotionStrategyPO po){
		if(po.getId()!=null&&!po.getId().trim().equals("")){return;}
		Date date = new Date();
		po.setId(date.toPromotionId());
	}
}
