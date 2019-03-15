package ui.uiAssistants;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageFactory {
	private static Stage logStage;
	private static Stage financeStage;
	private static Stage salesmanStage;
	private static Stage managerStage;
	private static Stage adminStage;
	
	public static Stage getLogStage(){
		if(logStage==null){
			logStage=new Stage();
			logStage.initStyle(StageStyle.TRANSPARENT);
		}
		return logStage;
	}
	
	public static Stage getFinanceStage(){
		if(financeStage==null){
			financeStage=new Stage();
		}
		
		return financeStage;
	}
	
	public static Stage getSalesmanStage(){
		if(salesmanStage==null){
			salesmanStage=new Stage();
		}
		
		return salesmanStage;
	}
	
	public static Stage getManagerStage(){
		if(managerStage==null){
			managerStage=new Stage();
		}
		
		return managerStage;
	}
	
	public static Stage getAdminStage(){
		if(adminStage==null){
			adminStage=new Stage();
		}
		
		return adminStage;
	}
}
