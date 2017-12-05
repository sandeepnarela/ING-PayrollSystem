package com.cg.payroll.services;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.payroll.beans.Associate;
import com.cg.payroll.daoservices.PayrollDAOServices;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundException;
import com.cg.payroll.exceptions.PayrollServicesDownException;

@Service(value = "payrollServices")
public class PayrollServicesImpl implements PayrollServices {
	@Autowired(required = true)
	private PayrollDAOServices daoServices;

	public PayrollServicesImpl(PayrollDAOServices payrollDAOServices) {
		this.daoServices = payrollDAOServices;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int acceptAssociateDetails(Associate associate) throws PayrollServicesDownException {
		try {
			return daoServices.insertAssociate(associate);
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll services down, plz try later", e);
		}
	}

	/**
	 * Calculate the Net Salary Details.
	 * 
	 * @param associateId
	 * @return int
	 * @throws AssociateDetailsNotFoundException
	 * @throws PayrollServicesDownException
	 */
	@Override
	public int calaculateNetSalary(int associateId)
			throws AssociateDetailsNotFoundException, PayrollServicesDownException {
		try {
			Associate associate = this.getAssociateDetails(associateId);
			associate.getSalary().setHra(associate.getSalary().getBasicSalary() * 40 / 100);
			associate.getSalary().setConveyanceAllowance(associate.getSalary().getBasicSalary() * 30 / 100);
			associate.getSalary().setPersonalAllowance(associate.getSalary().getBasicSalary() * 30 / 100);
			associate.getSalary().setGratuity(associate.getSalary().getBasicSalary() * 18 / 100);
			associate.getSalary().setEpf(associate.getSalary().getBasicSalary() * 12 / 100);
			associate.getSalary().setCompanyPf(1800);
			associate.getSalary().setGrossSalary(associate.getSalary().getBasicSalary() + associate.getSalary().getHra()
					+ associate.getSalary().getConveyanceAllowance() + associate.getSalary().getPersonalAllowance()
					+ associate.getSalary().getEpf() + associate.getSalary().getCompanyPf());
			int annualPackage = associate.getSalary().getGrossSalary() * 12;
			associate.setYearlyInvestmentUnder8oC(associate.getYearlyInvestmentUnder8oC()
					+ associate.getSalary().getEpf() * 12 + associate.getSalary().getCompanyPf() * 12);
			int nonTaxcalc = 0;
			if (associate.getYearlyInvestmentUnder8oC() >= 150000)
				nonTaxcalc = 150000 + 250000;
			else
				nonTaxcalc = associate.getYearlyInvestmentUnder8oC() + 250000;
			int annualTax = 0;
			if (annualPackage <= 250000)
				annualTax = 0;
			else if (annualPackage > 250000 && annualPackage <= 500000)
				annualTax = (annualPackage - nonTaxcalc) * 5 / 100;
			else if (annualPackage > 500000 && annualPackage <= 1000000)
				annualTax = (500000 - nonTaxcalc) * 5 / 100 + (annualPackage - 500000) * 20 / 100;
			else if (annualPackage > 1000000)
				annualTax = (500000 - nonTaxcalc) * 5 / 100 + 100000 + (annualPackage - 1000000) * 30 / 100;
			associate.getSalary().setMonthlyTax(annualTax / 12);
			int netSalary = associate.getSalary().getGrossSalary() - associate.getSalary().getEpf()
					- associate.getSalary().getCompanyPf() - associate.getSalary().getMonthlyTax();
			associate.getSalary().setNetSalary(netSalary);
			daoServices.updateAssociate(associate);
			return associate.getSalary().getNetSalary();
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll services down, plz try later", e);
		}
	}

	/**
	 * Getting the associate details.
	 * 
	 * @param associateId
	 * @return
	 * @throws AssociateDetailsNotFoundException
	 * @throws PayrollServicesDownException
	 */
	@Override
	public Associate getAssociateDetails(int associateId)
			throws AssociateDetailsNotFoundException, PayrollServicesDownException {
		try {
			Associate associate = daoServices.getAssociate(associateId);
			if (associate == null)
				throw new AssociateDetailsNotFoundException("Associate details for id " + associateId + " not found");
			return associate;
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll services down, plz try later", e);
		}
	}

	/**
	 * Getting all associate details.
	 * 
	 * @return List<>
	 * @throws AssociateDetailsNotFoundException
	 * @throws PayrollServicesDownException
	 */
	@Override
	public List<Associate> getAllAssociateDetails() throws PayrollServicesDownException {
		try {
			return daoServices.getAssociates();
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll services down, plz try later", e);
		}
	}

	/**
	 * Removing the associate details.
	 * 
	 * @param associateId
	 * @return boolean
	 * @throws AssociateDetailsNotFoundException
	 * @throws PayrollServicesDownException
	 */
	@Override
	public boolean removeAssociate(int associateId) throws PayrollServicesDownException {
		try {
			return daoServices.deleteAssociate(associateId);
		} catch (SQLException e) {
			throw new PayrollServicesDownException("PayrollServices are down, try again later", e);
		}
	}

}
