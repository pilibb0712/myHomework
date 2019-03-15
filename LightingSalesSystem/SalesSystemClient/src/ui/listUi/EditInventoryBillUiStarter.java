package ui.listUi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageFactory;
import ui.uiAssistants.StageSize;
import vo.InventoryLossBillVO;
import vo.InventoryOverBillVO;
import vo.InventoryWarningBillVO;

public class EditInventoryBillUiStarter {
		Stage editBillStage=StageFactory.getFinanceStage();
	
       public void editInventoryWarningBill(InventoryWarningBillVO billVO,String type){
    	   try{
    		   
//一个单据一个单独的窗口，所以stage写在里面
    		   FXMLLoader loader=new FXMLLoader(getClass().getResource("EditInventoryBillUi.fxml"));
    		   Parent root=loader.load();

    		   EditInventoryBillUiController controller=loader.getController();
    		   controller.editInventoryWarningBill(editBillStage, billVO,type);


    		   Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);
    		   editBillStage.setScene(scene);
    		   editBillStage.setTitle("编辑库存报警单");
    		   editBillStage.show();

    	   }catch(IOException e){
    		   e.printStackTrace();
    	   }
       }
       public void editInventoryLossBill(InventoryLossBillVO billVO,String type){
    	   try{
    		   FXMLLoader loader=new FXMLLoader(getClass().getResource("EditInventoryBillUi.fxml"));
               Parent root=loader.load();

    		   EditInventoryBillUiController controller=loader.getController();
    		   controller.editInventoryLossBill(editBillStage, billVO,type);


    		   Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);
    		   editBillStage.setScene(scene);
    		   editBillStage.setTitle("编辑库存报损单");
    		   editBillStage.show();

    	   }catch(IOException e){
    		   e.printStackTrace();
    	   }
       }
       public void editInventoryOverBill(InventoryOverBillVO billVO,String type){
    	   try{
    		   FXMLLoader loader=new FXMLLoader(getClass().getResource("EditInventoryBillUi.fxml"));
    		   Parent root=loader.load();

    		   EditInventoryBillUiController controller=loader.getController();
    		   controller.editInventoryOverBill(editBillStage, billVO,type);


    		   Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);
    		   editBillStage.setScene(scene);
    		   editBillStage.setTitle("编辑库存报溢单");
    		   editBillStage.show();

    	   }catch(IOException e){
    		   e.printStackTrace();
    	   }
       }
}
