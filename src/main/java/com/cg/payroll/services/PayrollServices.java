package com.cg.payroll.services;

import java.util.List;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundException;
import com.cg.payroll.exceptions.PayrollServicesDownException;

public interface PayrollServices {

	public int acceptAssociateDetails(Associate associate) throws PayrollServicesDownException;

	int calaculateNetSalary(int associateId) throws AssociateDetailsNotFoundException, PayrollServicesDownException;

	Associate getAssociateDetails(int associateId) throws AssociateDetailsNotFoundException, PayrollServicesDownException;

	List<Associate> getAllAssociateDetails() throws PayrollServicesDownException;
	boolean authenticateAssociate(Associate associate) throws AssociateDetailsNotFoundException, PayrollServicesDownException;
	boolean changePassword(Associate associate, String newPassword) throws AssociateDetailsNotFoundException, PayrollServicesDownException;
}
