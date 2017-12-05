package com.cg.payroll.services;

import java.util.List;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundException;
import com.cg.payroll.exceptions.PayrollServicesDownException;

public interface PayrollServices {

	/**
	 * Insert Associate details
	 * @param associate
	 * @return
	 * @throws PayrollServicesDownException
	 */
	public int acceptAssociateDetails(Associate associate) throws PayrollServicesDownException;

	/**
	 * Calculate the Net Salary Details.
	 * @param associateId
	 * @return
	 * @throws AssociateDetailsNotFoundException
	 * @throws PayrollServicesDownException
	 */
	public int calaculateNetSalary(int associateId) throws AssociateDetailsNotFoundException, PayrollServicesDownException;

	/**
	 * Getting the associate details.
	 * @param associateId
	 * @return
	 * @throws AssociateDetailsNotFoundException
	 * @throws PayrollServicesDownException
	 */
	public Associate getAssociateDetails(int associateId) throws AssociateDetailsNotFoundException, PayrollServicesDownException;

	/**
	 * Getting the all associates details.
	 * @return
	 * @throws PayrollServicesDownException
	 */
	public List<Associate> getAllAssociateDetails() throws PayrollServicesDownException;
	
	/**
	 * Delete the associate details.
	 * @param associateId
	 * @return
	 * @throws PayrollServicesDownException
	 */
	boolean removeAssociate(int associateId) throws PayrollServicesDownException;
}
