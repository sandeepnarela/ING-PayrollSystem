package com.cg.payroll.beans;
public class Salary {
	private int basicSalary, hra, conveyanceAllowance, otherAllowance, personalAllowance, 	monthlyTax, epf, companyPf, gratuity, grossSalary, netSalary;
	public Salary() {
	}
	public Salary(int basicSalary, int hra, int conveyanceAllowance,
			int otherAllowance, int personalAllowance, int monthlyTax, int epf,
			int companyPf, int gratuity, int grossSalary, int netSalary) {
		super();
		this.basicSalary = basicSalary;
		this.hra = hra;
		this.conveyanceAllowance = conveyanceAllowance;
		this.otherAllowance = otherAllowance;
		this.personalAllowance = personalAllowance;
		this.monthlyTax = monthlyTax;
		this.epf = epf;
		this.companyPf = companyPf;
		this.gratuity = gratuity;
		this.grossSalary = grossSalary;
		this.netSalary = netSalary;
	}

	public Salary(int basicSalary) {
		super();
		this.basicSalary = basicSalary;
	}

	public int getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(int basicSalary) {
		this.basicSalary = basicSalary;
	}

	public int getHra() {
		return hra;
	}

	public void setHra(int hra) {
		this.hra = hra;
	}

	public int getConveyanceAllowance() {
		return conveyanceAllowance;
	}

	public void setConveyanceAllowance(int conveyanceAllowance) {
		this.conveyanceAllowance = conveyanceAllowance;
	}

	public int getOtherAllowance() {
		return otherAllowance;
	}

	public void setOtherAllowance(int otherAllowance) {
		this.otherAllowance = otherAllowance;
	}

	public int getPersonalAllowance() {
		return personalAllowance;
	}

	public void setPersonalAllowance(int personalAllowance) {
		this.personalAllowance = personalAllowance;
	}

	public int getMonthlyTax() {
		return monthlyTax;
	}

	public void setMonthlyTax(int monthlyTax) {
		this.monthlyTax = monthlyTax;
	}

	public int getEpf() {
		return epf;
	}

	public void setEpf(int epf) {
		this.epf = epf;
	}

	public int getCompanyPf() {
		return companyPf;
	}

	public void setCompanyPf(int companyPf) {
		this.companyPf = companyPf;
	}

	public int getGratuity() {
		return gratuity;
	}

	public void setGratuity(int gratuity) {
		this.gratuity = gratuity;
	}

	public int getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(int grossSalary) {
		this.grossSalary = grossSalary;
	}

	public int getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(int netSalary) {
		this.netSalary = netSalary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + basicSalary;
		result = prime * result + companyPf;
		result = prime * result + conveyanceAllowance;
		result = prime * result + epf;
		result = prime * result + gratuity;
		result = prime * result + grossSalary;
		result = prime * result + hra;
		result = prime * result + monthlyTax;
		result = prime * result + netSalary;
		result = prime * result + otherAllowance;
		result = prime * result + personalAllowance;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salary other = (Salary) obj;
		if (basicSalary != other.basicSalary)
			return false;
		if (companyPf != other.companyPf)
			return false;
		if (conveyanceAllowance != other.conveyanceAllowance)
			return false;
		if (epf != other.epf)
			return false;
		if (gratuity != other.gratuity)
			return false;
		if (grossSalary != other.grossSalary)
			return false;
		if (hra != other.hra)
			return false;
		if (monthlyTax != other.monthlyTax)
			return false;
		if (netSalary != other.netSalary)
			return false;
		if (otherAllowance != other.otherAllowance)
			return false;
		if (personalAllowance != other.personalAllowance)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "   BasicSalary=" + basicSalary + ", hra=" + hra
				+ ", conveyanceAllowance=" + conveyanceAllowance
				+ ", otherAllowance=" + otherAllowance + ", personalAllowance="
				+ personalAllowance + ", monthlyTax=" + monthlyTax + ", epf="
				+ epf + ", companyPf=" + companyPf + ", gratuity=" + gratuity
				+ ", grossSalary=" + grossSalary + ", netSalary=" + netSalary;
	}
	
}