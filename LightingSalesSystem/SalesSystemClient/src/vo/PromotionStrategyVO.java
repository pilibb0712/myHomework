package vo;

import java.io.Serializable;

import assistant.type.StrategyCategoryEnum;
import assistant.utility.Date;

public class PromotionStrategyVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2972429855629128310L;
	private String Id;
	private String name;
	private Date startDate;
	private Date endDate;
	private String remark;
	private StrategyCategoryEnum strategyCategoryEnum;
	
	public PromotionStrategyVO(){
		this(null, null, null, null);
	}
	
	public PromotionStrategyVO(String Id,Date startDate,Date endDate,String remark){
		this.Id=Id;
		this.startDate=startDate;
		this.endDate=endDate;
		this.remark=remark;
	}
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StrategyCategoryEnum getStrategyCategoryEnum() {
		return strategyCategoryEnum;
	}

	public void setStrategyCategoryEnum(StrategyCategoryEnum strategyCategoryEnum) {
		this.strategyCategoryEnum = strategyCategoryEnum;
	}
}
