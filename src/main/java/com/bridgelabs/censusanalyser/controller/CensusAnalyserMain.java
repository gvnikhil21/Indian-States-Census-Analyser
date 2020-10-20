package com.bridgelabs.censusanalyser.controller;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bridgelabs.censusanalyser.model.*;

public class CensusAnalyserMain {

	private static final Logger LOGGER = LogManager.getLogger(CensusAnalyserMain.class);

	public static void main(String[] args) {
		// welcome message
		LOGGER.info("Welcome to Indian States Census Analyser!");
	}

	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		int noOfEntries = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IndiaCensusCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
			while (censusCSVIterator.hasNext()) {
				noOfEntries++;
				censusCSVIterator.next();
			}
		} catch (Exception e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
		return noOfEntries;
	}
}
