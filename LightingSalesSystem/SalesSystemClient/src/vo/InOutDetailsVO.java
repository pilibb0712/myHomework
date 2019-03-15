package vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ÕËµ¥Ã÷Ï¸
 * @author guxinyu
 * @version 2017.12.3
 *
 */
public class InOutDetailsVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5404540085661638677L;
	private ArrayList<SingleEntryVO> entries;
	
	public InOutDetailsVO(){
		
	}

	public InOutDetailsVO(ArrayList<SingleEntryVO> all){
		setEntries(all);
	}

	public ArrayList<SingleEntryVO> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<SingleEntryVO> entries) {
		this.entries = entries;
	}

}
