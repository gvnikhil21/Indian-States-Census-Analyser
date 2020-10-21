package com.bridgelabs.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class IndianStateCSV {
	@CsvBindByName(column = "SrNo", required = true)
	public int srNo;

	@CsvBindByName(column = "State Name", required = true)
	public String state;

	@CsvBindByName(column = "TIN", required = true)
	public int tin;

	@CsvBindByName(column = "StateCode", required = true)
	public int stateCode;

	@Override
	public String toString() {
		return "IndianStateCSV{srNo=" + srNo + ", state=" + state + ", tin=" + tin + ", stateCode=" + stateCode + "}";
	}

}
