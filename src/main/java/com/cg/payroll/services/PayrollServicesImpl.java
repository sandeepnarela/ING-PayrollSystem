package com.cg.payroll.services;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.payroll.beans.Associate;
import com.cg.payroll.daoservices.PayrollDAOServices;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundException;
import com.cg.payroll.exceptions.PayrollServicesDownException;
@Service(value="payrollServices")
public class PayrollServicesImpl implements PayrollServices{
	@Autowired(required=true)
	private PayrollDAOServices daoServices;
	
	@Override
	public int acceptAssociateDetails(Associate associate) throws PayrollServicesDownException{
		try {
			return daoServices.insertAssociate(associate);
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll services down, plz try later", e);
		}
	}
	@Override
	public int calaculateNetSalary(int associateId) throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		try {
			Associate associate = this.getAssociateDetails(associateId);
			associate.getSalary().setHra(associate.getSalary().getBasicSalary() * 40 / 100);
			associate.getSalary().setConveyanceAllowance(associate.getSalary().getBasicSalary() * 30 / 100);
			associate.getSalary().setPersonalAllowance(associate.getSalary().getBasicSalary() * 30 / 100);
			associate.getSalary().setGratuity(associate.getSalary().getBasicSalary() * 18 / 100);
			associate.getSalary().setEpf( associate.getSalary().getBasicSalary() * 12 / 100);
			associate.getSalary().setCompanyPf(1800);
			associate.getSalary().setGrossSalary(associate.getSalary().getBasicSalary() + associate.getSalary().getHra() + associate.getSalary().getConveyanceAllowance() + associate.getSalary().getPersonalAllowance() + associate.getSalary().getEpf() + associate.getSalary().getCompanyPf());
			int annualPackage = associate.getSalary().getGrossSalary() * 12;
			associate.setYearlyInvestmentUnder8oC(associate.getYearlyInvestmentUnder8oC() + associate.getSalary().getEpf() * 12 + associate.getSalary().getCompanyPf() * 12);
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
				annualTax =( 500000 - nonTaxcalc) * 5 / 100 + (annualPackage - 500000) * 20 / 100;
			else if (annualPackage > 1000000)
				annualTax = (500000 - nonTaxcalc) * 5 / 100 + 100000 + (annualPackage - 1000000) * 30 / 100;
			associate.getSalary().setMonthlyTax(annualTax / 12);
			int netSalary = associate.getSalary().getGrossSalary() - associate.getSalary().getEpf() - associate.getSalary().getCompanyPf() - associate.getSalary().getMonthlyTax();
			associate.getSalary().setNetSalary(netSalary);
			daoServices.updateAssociate(associate);
			return associate.getSalary().getNetSalary();
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll services down, plz try later", e);
		}
	}

	@Override
	public Associate getAssociateDetails(int associateId) throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		try {
			Associate associate = daoServices.getAssociate(associateId);
			if (associate == null) throw new AssociateDetailsNotFoundException("Associate details for id "+associateId+" not found");
			return associate;
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll services down, plz try later", e);
		}
	}

	@Override
	public List<Associate> getAllAssociateDetails() throws PayrollServicesDownException {
		try {
			return daoServices.getAssociates();
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll services down, plz try later", e);
		}
	}

	@Override
	public boolean authenticateAssociate(Associate associate) throws AssociateDetailsNotFoundException, PayrollServicesDownException {
		Associate associate2 = this.getAssociateDetails(associate.getAssociateId());
		if(associate.getPassword().equals(associate2.getPassword()))
			return true;
		return false;
	}

	@Override
	public boolean changePassword(Associate associate, String newPassword) throws AssociateDetailsNotFoundException,
			PayrollServicesDownException {
		try {
				if (authenticateAssociate(associate))  {
					Associate associate2 = this.getAssociateDetails(associate.getAssociateId());
					associate2.setPassword(newPassword);
					daoServices.updateAssociate(associate2);
					return true;
			}
			return false;
		} catch (SQLException e) {
			throw new PayrollServicesDownException("Payroll services down, plz try later", e);
		}
	}

}
