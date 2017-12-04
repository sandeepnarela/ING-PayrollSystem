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
			//playerDAO.acceptAssociateDetails(associate);
			Associate associate1= playerDAO.getAssociateDetails(7);
			//int salary =playerDAO.calaculateNetSalary(7);
			//System.out.println("::::Salary:::"+salary);
			System.out.println("get associate:::::"+associate1.getDepartment());
			/*List<Associate>  list =  playerDAO.getAllAssociateDetails();
			for(Associate associate2 :list) {
				System.out.println(":list::getAssociateId::::"+associate2.getAssociateId());
			}*/
		} catch (PayrollServicesDownException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(":::::::::::"+associate.getAssociateId());
	}
}
