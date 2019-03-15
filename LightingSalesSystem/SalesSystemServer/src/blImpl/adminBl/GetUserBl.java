package blImpl.adminBl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import assistant.exception.Myexception;
import assistant.type.UserPositionEnum;
import blInteract.userInteract.GetUserService;
import vo.UserVO;

public class GetUserBl implements GetUserService{
	private AdminBl bl = new AdminBl();
	private ArrayList<UserVO> vos;
	public GetUserBl() {
		try {
			vos = bl.getUserList();
		} catch (RemoteException | Myexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public ArrayList<UserVO> getManager() {
		return filterByPosition(vos, UserPositionEnum.MANAGER);
	}

	@Override
	public ArrayList<UserVO> getWarekeeper() {
		return filterByPosition(vos, UserPositionEnum.WAREKEPPER);
	}

	@Override
	public ArrayList<UserVO> getFianceStaff() {
		return filterByPosition(vos, UserPositionEnum.FINANCE);
	}

	@Override
	public ArrayList<UserVO> getAdmin() {
		return filterByPosition(vos, UserPositionEnum.ADMIN);
	}

	@Override
	public ArrayList<UserVO> getSalesman() {
		return filterByPosition(vos, UserPositionEnum.SALESMAN);
	}

	private ArrayList<UserVO> filterByPosition(ArrayList<UserVO> vos,UserPositionEnum position){
		ArrayList<UserVO> filteredVos = new ArrayList<>();
		for(UserVO vo: vos){
			if(vo.getPosition()==position){
				filteredVos.add(vo);
			}
		}
		return filteredVos;
	}

}
