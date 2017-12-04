package com.cg.payroll.daoservices;

import java.sql.SQLException;
import java.util.List;

import com.cg.payroll.beans.Associate;

public interface PayrollDAOServices {

	int insertAssociate(Associate associate)throws SQLException;
	boolean updateAssociate(Associate associate)throws SQLException;
	boolean deleteAssociate(int associateId)throws SQLException;
	Associate getAssociate(int associateId)throws SQLException;
	List<Associate> getAssociates()throws SQLException;
	boolean updateAssociatePassword(Associate associate) throws SQLException;
}
