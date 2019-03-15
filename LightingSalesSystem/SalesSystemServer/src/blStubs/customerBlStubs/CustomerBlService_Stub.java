package blStubs.customerBlStubs;

import blService.customerBlService.CustomerBlService;
import vo.CustomerVO;

public class CustomerBlService_Stub implements CustomerBlService{
	String number;
	String type;
	char level;
	String name, tel, address, post, email;
	double s_receive, receive, pay;
	String s_salesman;
	CustomerVO test;
	
	public CustomerBlService_Stub(String a, String b, char c, String d, String e, String f, String g, String h, 
			double i, double j, double k, String l) {
		// TODO Auto-generated constructor stub
		number = a;
		type = b;
		level = c;
		name = d;
		tel = e;
		address = f;
		post = g;
		email = h;
		s_receive = i;
		receive = j;
		pay = k;
		s_salesman = l;
	}
	
	public CustomerBlService_Stub(CustomerVO testVo) {
		// TODO Auto-generated constructor stub
		test=testVo;
	}
	
	public CustomerBlService_Stub(String na) {
		// TODO Auto-generated constructor stub
		name=na;
	}
	
	@Override
	public boolean AddCustomer(CustomerVO vo){
		// TODO Auto-generated method stub
		vo.setNumber(number);
		vo.setType(type);
		vo.setLevel(level);
		vo.setName(name);
		vo.setTel(tel);
		vo.setAddress(address);
		vo.setPost(post);
		vo.setEmail(email);
		vo.setS_receive(s_receive);
		vo.setReceive(receive);
		vo.setPay(pay);
		vo.setS_salesman(s_salesman);
		return true;
	}

	@Override
	public boolean DelCustomer(String number, String name){
		// TODO Auto-generated method stub
		if(number=="i00001"&&name=="customer") {
			return true;
		}
		return false;
	}

	@Override
	public boolean ModCustomer(CustomerVO vo){
		// TODO Auto-generated method stub
		if(vo.getName()=="i00001"&&vo.getLevel()=='A') {
			return true;
		}
		return false;
	}

	@Override
	public CustomerVO QueryCustomer(String name){
		// TODO Auto-generated method stub
		CustomerVO vo=new CustomerVO("i00001", "in", 'A', "customer", "10010", "No.1, Road A", "000000", "customer@customer.com", 1000, 500, 0, "people");
		if(name=="i00001") {
			return vo;
		}
		return null;
	}
	
}
