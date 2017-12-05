package com.cg.payroll.main;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundException;
import com.cg.payroll.exceptions.PayrollServicesDownException;
import com.cg.payroll.services.PayrollServices;
import com.sun.nio.sctp.AssociationChangeNotification;

public class MainClass {
	public static void main(String[] args) throws AssociateDetailsNotFoundException {
		ApplicationContext context = new ClassPathXmlApplicationContext("payrollSpringBeans.xml");
		PayrollServices playerDAO = (PayrollServices) context.getBean("payrollServices");
		Associate associate = new Associate(56, "ss", "df", "df", "df", "dfd", "dfdf", new Salary(34, 34, 45, 45, 45, 45, 45, 45, 45, 45, 45), new BankDetails(3434, "dfgf", "dfd"));
		try {
			
			// Insert the Associate Details.
			int insertAssociate = playerDAO.acceptAssociateDetails(associate);
			System.out.println("Insert Associate"+insertAssociate);
			
			// Get the Associates Details
			Associate associate1= playerDAO.getAssociateDetails(7);
						
			// Get all the associate details.			
			List<Associate>  list =  playerDAO.getAllAssociateDetails();
			for(Associate associate2 :list) {
				System.out.println(":list::getAssociateId::::"+associate2.getAssociateId());
			}
			
			// delete the associate details
//			boolean deleteAssociate= playerDAO.removeAssociate(associate1.getAssociateId());
			boolean deleteAssociate= playerDAO.removeAssociate(2);
			System.out.println("Delete assocaite" + deleteAssociate);
			
		} catch (PayrollServicesDownException e) {
			e.printStackTrace();
		}
			System.out.println(":::::::::::"+associate.getAssociateId());
	}
}
