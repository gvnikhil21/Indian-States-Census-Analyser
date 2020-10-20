package com.bridgelabs.censusanalyser.tester;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bridgelabs.censusanalyser.controller.CensusAnalyserMain;
import com.bridgelabs.censusanalyser.model.CensusAnalyserException;

public class CensusAnalyserTest {
	private static final Logger LOGGER = LogManager.getLogger(CensusAnalyserTest.class);
	private static final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\AssignmentBridgeLabs\\Indian-States-Census-Analyser\\src\\main\\resources\\IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "D:\\AssignmentBridgeLabs\\Indian-States-Census-Analyser\\src\\main\\resources\\IndiaStateCensusDataOne.csv";

	@Test
	public void givenIndianCensusCSVFile_ShouldReturnCorrectEntries() {
		try {
			CensusAnalyserMain censusAnalyserMain = new CensusAnalyserMain();
			int noOfEntries = censusAnalyserMain.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(29, noOfEntries);
			LOGGER.info("Test performed for no. of entries in given CSV file");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Test
	public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
		try {
			CensusAnalyserMain censusAnalyserMain = new CensusAnalyserMain();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyserMain.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
			LOGGER.info("Test performed for given wrong CSV file");
		}
	}
}
