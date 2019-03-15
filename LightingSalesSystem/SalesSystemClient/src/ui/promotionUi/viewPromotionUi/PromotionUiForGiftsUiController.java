package ui.promotionUi.viewPromotionUi;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
import ui.promotionUi.bLobj.Goods;
import vo.GiftVO;

/**
 * @author zhangao 
 * @version 2017.12.22
 * */

public interface PromotionUiForGiftsUiController {
	public ObservableList<GiftVO> getGiftList();
	public ObservableList<Goods> getGoodsList();
}
