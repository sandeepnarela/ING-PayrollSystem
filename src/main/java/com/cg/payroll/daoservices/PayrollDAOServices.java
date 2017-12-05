package com.cg.payroll.daoservices;

import java.sql.SQLException;
import java.util.List;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.PayrollServicesDownException;

public interface PayrollDAOServices {

	/**
	 * Insert Associate details
	 * @param associate
	 * @return int
	 * @throws SQLException
	 */
	int insertAssociate(Associate associate)throws SQLException;
	
	/**
	 * Update the associate the details
	 * @param associate
	 * @return boolean
	 * @throws SQLException
	 */
	boolean updateAssociate(Associate associate)throws SQLException;
	
	/**
	 * Delete the associate the details
	 * @param associateId
	 * @return boolean
	 * @throws SQLException
	 */
	boolean deleteAssociate(int associateId)throws SQLException;
	
	/**
	 * Get the associate the details.
	 * @param associateId
	 * @return Associate
	 * @throws SQLException
	 */
	Associate getAssociate(int associateId)throws SQLException;
	List<Associate> getAssociates()throws SQLException;
}
