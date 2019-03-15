package ui.promotionUi.updatePromotionUi;

import java.util.HashMap;

import javafx.collections.ObservableList;
import ui.promotionUi.bLobj.Goods;
import vo.GiftVO;

/**
 * @author zhangao 
 * @version 2017.12.22
 * */

public interface PromotionUiForGiftsUiController {
	public ObservableList<Goods> getGoodsList();
	public void addGifts(ObservableList<GiftVO> vos,HashMap<GiftVO, Goods> map);
}
