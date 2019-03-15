package vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 经营情况表
 * @author guxinyu
 * @version 2017.12.23
 *
 */
public class BusinessConditionListVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8604837995297065086L;
	private ArrayList<IncomeCostVO> statistics;
	private double afterAllowanceEarning;
	private double allowance;
	private double totalCost;
	private double profit;

	public BusinessConditionListVO(){
		
	}
	
	public BusinessConditionListVO(ArrayList<IncomeCostVO> sta, double afterAllow,
			double allow, double cost, double pro){
		setStatistics(sta);
		setAfterAllowanceEarning(afterAllow);
		setAllowance(allow);
		setTotalCost(cost);
		setProfit(pro);
	}
	
	public ArrayList<IncomeCostVO> getStatistics() {
		return statistics;
	}

	public void setStatistics(ArrayList<IncomeCostVO> statistics) {
		this.statistics = statistics;
	}

	public double getAfterAllowanceEarning() {
		return afterAllowanceEarning;
	}

	public void setAfterAllowanceEarning(double afterAllowanceEarning) {
		this.afterAllowanceEarning = afterAllowanceEarning;
	}

	public double getAllowance() {
		return allowance;
	}

	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}
	
	
}
