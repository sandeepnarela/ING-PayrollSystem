package com.cg.payroll.daoservices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.exceptions.PayrollServicesDownException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository(value = "daoServices")
public class PayrollDAOServicesImpl implements PayrollDAOServices {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public PayrollDAOServicesImpl() {
	}

	/**
	 * Insert Associate details
	 * @param associate
	 * @return int
	 * @throws SQLException
	 */
	public int insertAssociate(Associate associate) throws SQLException {
		jdbcTemplate.update(
				"insert into Associate (yearlyInvestmentUnder8oC,firstName,lastName,department,designation,pancard,emailId) values(?,?,?,?,?,?,?)",
				associate.getYearlyInvestmentUnder8oC(), associate.getFirstName(), associate.getLastName(),
				associate.getDepartment(), associate.getDesignation(), associate.getPancard(), associate.getEmailId());

		String query = "select max(associateId)  from Associate";
		int associateId = jdbcTemplate.queryForInt(query);

		jdbcTemplate.update("insert into Salary(associateId,basicSalary,epf,companyPf)values(?,?,?,?)", associateId,
				associate.getSalary().getBasicSalary(), associate.getSalary().getEpf(),
				associate.getSalary().getCompanyPf());

		jdbcTemplate.update("insert into BankDetails(associateId,accountNo,bankName, ifscCode)values(?,?,?,?)",
				associateId, associate.getBankDetails().getAccountNo(), associate.getBankDetails().getBankName(),
				associate.getBankDetails().getIfscCode());

		return associateId;

	}

	
	/**
	 * Update the associate the details
	 * @param associate
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateAssociate(Associate associate) throws SQLException {

		boolean updateFlag;
		int associatUpdateCount = jdbcTemplate.update(
				"update Associate set yearlyInvestmentUnder8oC=?,firstName=?,lastName=?,department=?,designation=?,pancard=?,emailId=? where associateId="
						+ associate.getAssociateId(),
				associate.getYearlyInvestmentUnder8oC(), associate.getFirstName(), associate.getLastName(),
				associate.getDepartment(), associate.getDesignation(), associate.getPancard(), associate.getEmailId());
		// String query = "select max(associateId) from Associate";

		// int associateId = jdbcTemplate.queryForInt(query);
		if (associatUpdateCount > 0) {
			jdbcTemplate.update(
					"update Salary set associateId=?, basicSalary=?,epf=?,companyPf=?"
							+ ", hra=?, conveyanceAllowance=?, otherAllowance=?," + "personalAllowance=?,"
							+ "monthlyTax=?,gratuity=?," + "grossSalary=?,netSalary=? where associateId="
							+ associate.getAssociateId(),
					associate.getAssociateId(), associate.getSalary().getBasicSalary(), associate.getSalary().getEpf(),
					associate.getSalary().getCompanyPf(), associate.getSalary().getHra(),
					associate.getSalary().getConveyanceAllowance(), associate.getSalary().getOtherAllowance(),
					associate.getSalary().getPersonalAllowance(), associate.getSalary().getMonthlyTax(),
					associate.getSalary().getGratuity(), associate.getSalary().getGrossSalary(),
					associate.getSalary().getNetSalary());

			jdbcTemplate.update(
					"update BankDetails set associateId=?,accountNo=?,bankName=?, ifscCode=? where associateId="
							+ associate.getAssociateId(),
					associate.getAssociateId(), associate.getBankDetails().getAccountNo(),
					associate.getBankDetails().getBankName(), associate.getBankDetails().getIfscCode());
			updateFlag = true;
			return updateFlag;
		}
		return false;
	}

	/**
	 * Delete the associate the details
	 * @param associateId
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean deleteAssociate(int associateId) throws SQLException {

		jdbcTemplate.update("delete from salary where associateId=" + associateId);
		jdbcTemplate.update("delete from bankdetails where associateId=" + associateId);
		int associateUpdate = jdbcTemplate.update("delete from associate where associateId=" + associateId);
		System.out.println("Deleted the associate details" + associateUpdate);
		if (associateUpdate > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Get the associate the details.
	 * @param associateId
	 * @return Associate
	 * @throws SQLException
	 */
	public Associate getAssociate(int associateId) throws SQLException {
		String query = "select * from Associate a , Salary s , BankDetails b  where a.associateId=s.associateId and a.associateId=b.associateId and a.associateId= ?";
		Associate associate = (Associate) jdbcTemplate.query(query, new AssociateMapper(), associateId).get(0);
		return associate;
	}

	/**
	 * Get the associate the details.
	 * @param associateId
	 * @return Associate
	 * @throws SQLException
	 */
	public List<Associate> getAssociates() throws SQLException {
		List<Associate> associates = jdbcTemplate.query("select * from Associate a , Salary s , BankDetails b  "
				+ "where a.associateId=s.associateId and  " + "a.associateId=b.associateId", new AssociateMapper());
		System.out.println("::list size:" + associates.size());
		return associates;
	}

}

final class AssociateMapper implements RowMapper<Associate> {
	@Override
	public Associate mapRow(ResultSet rs, int rowNum) throws SQLException {
		Associate associate = new Associate(rs.getInt("associateId"), rs.getInt("yearlyInvestmentUnder8oC"),
				rs.getString("firstName"), rs.getString("lastName"), rs.getString("department"),
				rs.getString("designation"), rs.getString("pancard"), rs.getString("emailId"),

				new Salary(rs.getInt("basicSalary"), rs.getInt("hra"), rs.getInt("conveyanceAllowance"),
						rs.getInt("otherAllowance"), rs.getInt("personalAllowance"), rs.getInt("monthlyTax"),
						rs.getInt("epf"), rs.getInt("companyPf"), rs.getInt("gratuity"), rs.getInt("grossSalary"),
						rs.getInt("netSalary")),

				new BankDetails(rs.getInt("accountNo"), rs.getString("bankName"), rs.getString("ifscCode")));
		return associate;
	}

}
