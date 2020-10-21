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
	private static final String INDIAN_STATECODES_CSVFILE = "D:\\AssignmentBridgeLabs\\Indian-States-Census-Analyser\\src\\main\\resources\\IndiaStateCode.csv";
	private static final String INDIAN_CENSUS_WRONG_DELIMITER = "D:\\AssignmentBridgeLabs\\Indian-States-Census-Analyser\\src\\main\\resources\\InidaStateCensusFaultDelimiter.csv";
	private static final String INDIAN_CENSUS_WRONG_HEADER = "D:\\AssignmentBridgeLabs\\Indian-States-Census-Analyser\\src\\main\\resources\\IndiaStateCensusWrongHeader.csv";
	private static final String INDIAN_STATE_CODE_WRONG_DELIMITER = "D:\\AssignmentBridgeLabs\\Indian-States-Census-Analyser\\src\\main\\resources\\IndiaStateCodeFaultDelimiter.csv";

	@Test
	public void givenIndianCensusCSVFile_ShouldReturnCorrectEntries() {
		try {
			CensusAnalyserMain censusAnalyserMain = new CensusAnalyserMain();
			int noOfEntries = censusAnalyserMain.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(29, noOfEntries);
			LOGGER.info("Test performed for no. of entries in given CSV file");
		} catch (Exception e) {
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

	@Test
	public void GivenTheStateCensusCsvFile_WhenCorrect_ButFileExtensionIncorrect_ShouldThrowCensusAnalyserException() {
		try {
			CensusAnalyserMain censusAnalyserMain = new CensusAnalyserMain();
			censusAnalyserMain.loadIndiaCensusData(INDIAN_STATECODES_CSVFILE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_TYPE_PROBLEM, e.type);
			LOGGER.info("Test performed for given correct CSV file but inconsistent type data");
		}
	}

	@Test
	public void GivenTheStateCensusCsvFile_WhenCorrect_ButIncorrectDelimiter_ShouldThrowCensusAnalyserException() {
		try {
			CensusAnalyserMain censusAnalyserMain = new CensusAnalyserMain();
			censusAnalyserMain.loadIndiaCensusData(INDIAN_CENSUS_WRONG_DELIMITER);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_PROBLEM, e.type);
			LOGGER.info("Test performed for given correct CSV file but incorrect delimiter");
		}
	}

	@Test
	public void GivenTheStateCensusCsvFile_WhenCorrect_ButIncorrectHeader_ShouldThrowCensusAnalyserException() {
		try {
			CensusAnalyserMain censusAnalyserMain = new CensusAnalyserMain();
			censusAnalyserMain.loadIndiaCensusData(INDIAN_CENSUS_WRONG_HEADER);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_PROBLEM, e.type);
			LOGGER.info("Test performed for given correct CSV file but incorrect header");
		}
	}

	@Test
	public void givenIndianStateCSVFile_ShouldReturnCorrectEntries() {
		try {
			CensusAnalyserMain censusAnalyserMain = new CensusAnalyserMain();
			int noOfEntries = censusAnalyserMain.loadIndiaStateCodeData(INDIAN_STATECODES_CSVFILE);
			Assert.assertEquals(37, noOfEntries);
			LOGGER.info("Test performed for no. of entries in given CSV file for state data");
		} catch (Exception e) {
		}
	}

	@Test
	public void givenIndianStateCSVFile_WithWrongFile_ShouldThrowException() {
		try {
			CensusAnalyserMain censusAnalyserMain = new CensusAnalyserMain();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyserMain.loadIndiaStateCodeData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
			LOGGER.info("Test performed for given wrong CSV file for state data");
		}
	}

	@Test
	public void givenIndianStateCSVFile_WhenCorrect_ButFileExtensionIncorrect_ShouldThrowCensusAnalyserException() {
		try {
			CensusAnalyserMain censusAnalyserMain = new CensusAnalyserMain();
			censusAnalyserMain.loadIndiaStateCodeData(INDIA_CENSUS_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_TYPE_PROBLEM, e.type);
			LOGGER.info("Test performed for given correct CSV file but inconsistent type data for state data");
		}
	}

	@Test
	public void givenIndianStateCSVFile_WhenCorrect_ButIncorrectDelimiter_ShouldThrowCensusAnalyserException() {
		try {
			CensusAnalyserMain censusAnalyserMain = new CensusAnalyserMain();
			censusAnalyserMain.loadIndiaStateCodeData(INDIAN_STATE_CODE_WRONG_DELIMITER);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_PROBLEM, e.type);
			LOGGER.info("Test performed for given correct CSV file but incorrect delimiter for state data");
		}
	}

}
