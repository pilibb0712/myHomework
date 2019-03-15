package assistant.convertors;

import java.util.ArrayList;

import po.GiftPO;
import vo.GiftVO;
/**
 * @author zhangao 161250193
 * @version 2017.12.27
 * GiftListPO和VO的转换器
 */
public class GiftListPOVOConvertor {
	
	/**
	 * 将giftList的po转化为vo
	 * @param pos要转化的po list
	 * @return ArrayList<GiftVO> 转化后的vo的list
	 * */
	public static ArrayList<GiftVO> giftListPOToVO(ArrayList<GiftPO> pos){
		if(pos==null){return null;}
		ArrayList<GiftVO> vos = new ArrayList<>();
		for(GiftPO po:pos){
			GiftVO vo = GiftPOVOConvertor.giftPOtoVO(po);
			vos.add(vo);
		}
		return vos;
	}
	
	/**
	 * 将giftList的vo转化为po
	 * @param vos要转化的vo list
	 * @return ArrayList<GiftPO> 转化后的po的list
	 * */
	public static ArrayList<GiftPO> giftListVOToPO(ArrayList<GiftVO> vos){
		if(vos==null){return null;}
		ArrayList<GiftPO> pos= new ArrayList<>();
		for(GiftVO vo:vos){
			GiftPO po = GiftPOVOConvertor.giftVOtoVPO(vo);
			pos.add(po);
		}
		return pos;
	}
}
