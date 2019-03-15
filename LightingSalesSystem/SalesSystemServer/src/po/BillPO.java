package po;

import java.io.Serializable;
import assistant.type.BillCategoryEnum;
import assistant.type.BillStateEnum;
import assistant.utility.Date;
import vo.BillVO;

/**
 * BillPO数据类
 * @author 张傲  161250193
 * @version 2017.11.8
 */

public class BillPO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6523859307356498788L;
	
	private BillCategoryEnum categoryEnum;//单据的种类
	private String Id;//单据的编号(格式为开头字母缩写+"yyyyMMdd-xxxxx")
	private BillStateEnum billStateEnum;//单据的状态
	private UserInfoPO creater;//单据的创建者
	private UserInfoPO approver;//单据的审批者
	private UserInfoPO executor;//单据的执行者
	private Date createDate;//单据的创建日期
	private Date approveDate;//单据的审批日期
	private Date finishDate;//单据完成日期
	private String createrComment;//单据创建者的备注
	private String approverComment; //单据审批者的备注
	
	public BillCategoryEnum getCategoryEnum() {
		return categoryEnum;
	}
	public void setCategoryEnum(BillCategoryEnum categoryEnum) {
		this.categoryEnum = categoryEnum;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public BillStateEnum getBillStateEnum() {
		return billStateEnum;
	}
	public void setBillStateEnum(BillStateEnum billStateEnum) {
		this.billStateEnum = billStateEnum;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public String getCreaterComment() {
		return createrComment;
	}
	public void setCreaterComment(String createrComment) {
		this.createrComment = createrComment;
	}
	public String getApproverComment() {
		return approverComment;
	}
	public void setApproverComment(String approverComment) {
		this.approverComment = approverComment;
	}
	public UserInfoPO getCreater() {
		return creater;
	}
	public void setCreater(UserInfoPO creater) {
		this.creater = creater;
	}
	public UserInfoPO getApprover() {
		return approver;
	}
	public void setApprover(UserInfoPO approver) {
		this.approver = approver;
	}
	public UserInfoPO getExecutor() {
		return executor;
	}
	public void setExecutor(UserInfoPO executor) {
		this.executor = executor;
	}

	
}
