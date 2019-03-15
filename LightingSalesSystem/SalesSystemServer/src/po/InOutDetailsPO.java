package po;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ÕËµ¥Ã÷Ï¸
 * @author guxinyu
 * @version 2017.12.4
 *
 */
public class InOutDetailsPO implements Serializable{

	private static final long serialVersionUID = 4L;
	
	private ArrayList<SingleEntryPO> entries=new ArrayList<SingleEntryPO>();
	
	public InOutDetailsPO(){
		
	}

	public InOutDetailsPO(ArrayList<SingleEntryPO> all){
		setEntries(all);
	}

	public ArrayList<SingleEntryPO> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<SingleEntryPO> entries) {
		this.entries = entries;
	}

}
