package com.bridgelabs.censusanalyser.tester;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.bridgelabs.censusanalyser.controller.CensusAnalyserMain;

public class CensusAnalyserTest {
	private static final Logger LOGGER = LogManager.getLogger(CensusAnalyserTest.class);
	private static final String INDIA_CENSUS_CSV_FILE_PATH = "D:\\AssignmentBridgeLabs\\Indian-States-Census-Analyser\\src\\main\\resources\\IndiaStateCensusData.csv";

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

}
