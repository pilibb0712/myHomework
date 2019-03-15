package test.financeBlTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import assistant.exception.AccountNameExistException;
import blImpl.financeBl.BankAccountBl;
import po.BankAccountPO;

public class BankAccountBlTest {
	BankAccountBl bl=BankAccountBl.getInstance();
	
	/**
	 * T120000
	 */
	@Test
	public void driveSaveAccount(){
		BankAccountPO account=new BankAccountPO("≤‚ ‘’Àªß1",2135);
		try {
			assertEquals(true, bl.saveAccount(account));
		} catch (AccountNameExistException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * T121000
	 */
	@Test
	public void driveGetAccounts(){
		assertEquals("gxyÀΩ»À«Æπﬁ", bl.getAccounts().get(0).getAccountName());
	}
	
	/**
	 * T120200
	 */
	@Test
	public void driveUpdateAccount(){
		try {
			assertEquals(true, bl.updateAccount("≤‚ ‘’Àªß1", "≤‚ ‘’Àªß2"));
		} catch (AccountNameExistException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * T120300
	 */
	@Test 
	public void driveUpdateRemainingSum(){
		assertEquals(true, bl.updateRemainingSum("≤‚ ‘’Àªß2", 500));
	}
	
	/**
	 * T120400
	 */
	@Test 
	public void driveDeleteAccount(){
		assertEquals(true, bl.deleteAccount("≤‚ ‘’Àªß2"));
	}
	
	
}
