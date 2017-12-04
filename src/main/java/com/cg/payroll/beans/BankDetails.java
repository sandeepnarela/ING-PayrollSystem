package com.cg.payroll.beans;
public class BankDetails {
	
	private int accountNo;
	private String bankName, ifscCode;

	public BankDetails() {
		super();
	}

	public BankDetails(int accountNo, String bankName, String ifscCode) {
		super();
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.ifscCode = ifscCode;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNo;
		result = prime * result
				+ ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result
				+ ((ifscCode == null) ? 0 : ifscCode.hashCode());
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
		BankDetails other = (BankDetails) obj;
		if (accountNo != other.accountNo)
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (ifscCode == null) {
			if (other.ifscCode != null)
				return false;
		} else if (!ifscCode.equals(other.ifscCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  "  AccountNo=" + accountNo + ", bankName=" + bankName
				+ ", ifscCode=" + ifscCode;
	}

	
}