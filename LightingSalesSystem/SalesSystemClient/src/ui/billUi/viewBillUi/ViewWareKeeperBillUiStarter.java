/**
 * 查看仓库管理员制定的单据界面，包括库存报警单、库存报损单、库存报警单的界面
 * @author BeibeiZhang
 * @version 2017.11.19
 *
 */
package ui.billUi.viewBillUi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.uiAssistants.StageSize;

public class ViewWareKeeperBillUiStarter {
	private Stage billStage=new Stage();
	public void viewInventoryWarningBill(String billID){
	//库存报警单界面
	   try{
		   FXMLLoader loader=new FXMLLoader(getClass().getResource("WareKeeperBillsUi.fxml"));



		   Parent root=loader.load();
		   ViewWareKeeperBillUiController controller=loader.getController();
		   controller.setInventoryWarningBill(billStage, billID);
		   //将bill的内容设置好
		   Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

		   billStage.setScene(scene);
		   billStage.setTitle("查看库存报警单");
		   billStage.show();
	   }catch(IOException e){
		   e.printStackTrace();
	   }
	}

	public void viewInventoryLossBill(String billID){
	//库存报损单界面
		try{
			   FXMLLoader loader=new FXMLLoader(getClass().getResource("WareKeeperBillsUi.fxml"));

			   //将bill的内容设置好

			   Parent root=loader.load();

			   ViewWareKeeperBillUiController controller=loader.getController();
			   controller.setInventoryLossBill(billStage, billID);
			   Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

			   billStage.setScene(scene);
			   billStage.setTitle("查看库存报损单");
			   billStage.show();
		   }catch(IOException e){
			   e.printStackTrace();
		   }
	}

	public void viewInventoryOverBill(String billID){
	//库存报溢单
		try{
			   FXMLLoader loader=new FXMLLoader(getClass().getResource("WareKeeperBillsUi.fxml"));

			   //将bill的内容设置好

			   Parent root=loader.load();
               ViewWareKeeperBillUiController controller=loader.getController();
			   controller.setInventoryOverBill(billStage, billID);
			   Scene scene=new Scene(root,StageSize.STAGE_WIDTH,StageSize.STAGE_HEIGHT);

			   billStage.setScene(scene);
			   billStage.setTitle("查看库存报溢单");
			   billStage.show();
		   }catch(IOException e){
			   e.printStackTrace();
		   }
	}
}
