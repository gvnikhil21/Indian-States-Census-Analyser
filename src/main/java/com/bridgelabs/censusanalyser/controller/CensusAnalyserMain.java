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
		try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				if (line.contains(",") == false)
					throw new CensusAnalyserException("Wrong Delimiter",
							CensusAnalyserException.ExceptionType.DELIMITER_PROBLEM);
				if (noOfEntries == 0) {
					String checkArray[] = line.trim().split(",");
					if (checkArray.length != 4)
						throw new CensusAnalyserException("Wrong Delimiter",
								CensusAnalyserException.ExceptionType.HEADER_PROBLEM);
					if (!checkArray[0].equals("State") || !checkArray[1].equals("Population")
							|| !checkArray[2].equals("AreaInSqKm") || !checkArray[3].equals("DensityPerSqKm"))
						throw new CensusAnalyserException("Wrong Delimiter",
								CensusAnalyserException.ExceptionType.CENSUS_TYPE_PROBLEM);
				}
				noOfEntries++;
			}
		} catch (FileNotFoundException e) {
			throw new CensusAnalyserException("File not exists",
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return noOfEntries - 1;
	}
}