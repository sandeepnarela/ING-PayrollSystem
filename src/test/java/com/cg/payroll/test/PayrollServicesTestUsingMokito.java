package com.cg.payroll.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.daoservices.PayrollDAOServices;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundException;
import com.cg.payroll.exceptions.PayrollServicesDownException;
import com.cg.payroll.services.PayrollServices;
import com.cg.payroll.services.PayrollServicesImpl;

/**
 * Payroll services test cases
 */
public class PayrollServicesTestUsingMokito {
	private static PayrollServices payrollServices;
	private static PayrollDAOServices mockDaoServices;
	private ArrayList<Associate> associatesList=null;
	private Associate associate3 =null;
	@BeforeClass
	public static void setUpPayrollServices() {
		mockDaoServices = Mockito.mock(PayrollDAOServices.class);
		
		payrollServices = new PayrollServicesImpl(mockDaoServices);
	}
	@Before
	public void setUpTestData() throws SQLException {
		Associate associate1 = new Associate(1000, 78000, "John", "Peter", "Training", "Manager", "DTDYF736",
				"payrollServices@capgemini.com", new Salary(35000, 1800, 1800),
				new BankDetails(12345, "HDFC", "HDFC0097"));
		
		Associate associate2 =new Associate(1001, 87372, "Ayush", "Peter", "Training", "Manager", "YCTCC727",
				"ayush.Peter@capgemini.com", new Salary(87738, 1800, 1800),
				new BankDetails(12345, "HDFC", "HDFC0097"));
		
		associatesList = new ArrayList<>();
		associatesList.add(associate1);
		associatesList.add(associate2);
	
		associate3 = new Associate(120020, "Kumar", "Raj", "Training", "Sr Con", "HDUDUI37", "kumar.raj.@capgemini.com", new Salary(35000, 2000, 2000), new BankDetails(12344, "HSBC", "hsbc293"));
		
		Mockito.when(mockDaoServices.getAssociate(1000)).thenReturn(associate1);
		Mockito.when(mockDaoServices.getAssociate(1001)).thenReturn(associate2);
		Mockito.when(mockDaoServices.getAssociate(1234)).thenReturn(null);
		Mockito.when(mockDaoServices.getAssociates()).thenReturn(associatesList);
		Mockito.when(mockDaoServices.insertAssociate(associate3)).thenReturn(1002);
		Mockito.when(mockDaoServices.deleteAssociate(associate3.getAssociateId())).thenReturn(true);
	}

	@Test(expected = AssociateDetailsNotFoundException.class)
	public void testGetAssociateDataForInvalidAssociateId()
			throws PayrollServicesDownException, AssociateDetailsNotFoundException, SQLException {
		payrollServices.getAssociateDetails(1234);
		Mockito.verify(mockDaoServices).getAssociate(1234);
	}

	@Test
	public void testGetAssociateDataForValidAssociateId()
			throws PayrollServicesDownException, AssociateDetailsNotFoundException, SQLException {
		Associate expectedAssociate = new Associate(1000, 78000, "John", "Peter", "Training", "Manager", "DTDYF736",
				"payrollServices@capgemini.com", new Salary(35000, 1800, 1800),
				new BankDetails(12345, "HDFC", "HDFC0097"));
		Associate actualAssociate = payrollServices.getAssociateDetails(1000);
		
		Mockito.verify(mockDaoServices).getAssociate(1000);
		assertEquals(expectedAssociate, actualAssociate);
	}

	@Test
	public void testAcceptAssociateDetailsForValidData() throws PayrollServicesDownException, SQLException {
		int expectedAssociateId = 1002;
		int actualAssociateId = payrollServices.acceptAssociateDetails(associate3);
		Mockito.verify(mockDaoServices).insertAssociate(associate3);
		assertEquals(expectedAssociateId, actualAssociateId);
	}
	
	@Test
	public void testDeleteAssociateDetails() throws PayrollServicesDownException, SQLException
	{
		
		assertEquals( payrollServices.removeAssociate(associate3.getAssociateId()), true);
		Mockito.verify(mockDaoServices).deleteAssociate(associate3.getAssociateId());
	}

	@Test(expected = AssociateDetailsNotFoundException.class)
	public void testCalculateNetSalaryForInvalidAssociateId()
			throws PayrollServicesDownException, AssociateDetailsNotFoundException, SQLException {
		payrollServices.getAssociateDetails(63363);
		Mockito.verify(mockDaoServices).getAssociate(Mockito.anyInt());
		
	}


	@Test
	public void testGetAllAssociatesDetails() throws PayrollServicesDownException, SQLException {
		List<Associate> expectedAssociateList = associatesList;
		List<Associate> actualAssociateList = payrollServices.getAllAssociateDetails();
		Mockito.verify(mockDaoServices).getAssociates();
		assertEquals(expectedAssociateList, actualAssociateList);
	}

	@After
	public void tearDownTestData() {
		
	}

	@AfterClass
	public static void tearDownPayrollServicesq() {
		mockDaoServices = null;
		payrollServices = null;
	}

}
