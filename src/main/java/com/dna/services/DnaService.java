package com.dna.services;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dna.dto.StatDto;
import com.dna.models.DnaModel;
import com.dna.repositories.DnaRepository;

/**
 * @author dirojas
 *
 */
@Service
public class DnaService {

	@Autowired
	DnaRepository dnaRepository;

	
	/**
	 * Query the processed DNAs in a database. 
	 * Calculate how many have been humans and how many mutants, 
	 * and indicating a reason for these two values.
	 * 	
	 * Save DNA and result in database.
	 * 
	 * @return StatDto: Count mutants, humans and ratio
	 */
	public StatDto getStats() {

		ArrayList<DnaModel> dnas = (ArrayList<DnaModel>) dnaRepository.findAll();
		AtomicInteger countMutant = new AtomicInteger();
		AtomicInteger countHuman = new AtomicInteger();

		dnas.stream().forEach(dna -> {
			if (dna.isMutant()) {
				countMutant.getAndIncrement();
			} else {
				countHuman.getAndIncrement();
			}
		});

		StatDto stat = new StatDto();
		stat.setCountMutant(countMutant.get());
		stat.setCountHuman(countHuman.get());
		stat.setRatio(((float) countMutant.get()) / countHuman.get());

		return stat;

	}

	/**
	 * Responsible for calling the DNA validator function. 
	 * 	
	 * Save DNA and result in database.
	 * 
	 * @param dna String[] Makes up a dna
	 * 
	 * @return ResponseEntity:
	 * 		200 - OK to mutants
	 * 		403 - FORBIDDEN to Humans
	 */
	public ResponseEntity<String> isMutant(String[] dna) {
		ResponseEntity<String> status;
		DnaModel dnaModel = new DnaModel();
		dnaModel.setDna(String.join(",", dna));

		if (validateDna(dna)) {
			dnaModel.setMutant(true);
			status = new ResponseEntity<>(HttpStatus.OK);
		} else {
			dnaModel.setMutant(false);
			status = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		dnaRepository.save(dnaModel);
		return status;
	}

	/**
	 * Creates a DNA matrix with the received parameter, executes the methods in
	 * charge of validating the entered DNA.
	 * 
	 * @param dna String[] Makes up a dna
	 * 
	 * @return boolean: If it is a mutant, it returns true.
	 */
	private boolean validateDna(String[] dna) {
		int dnaTotal = dna.length;

		char[][] dnaTable = new char[dna.length][dna.length];
		for (int i = 0; i < dna.length; i++) {
			for (int j = 0; j < dna[0].length(); j++) {
				dnaTable[i][j] = dna[i].toCharArray()[j];
			}
		}

		if (validateDiagonal(dnaTotal, dnaTable)) {
			return true;
		} else if (validateVertical(dnaTotal, dnaTable)) {
			return true;

		} else {
			for (int i = 0; dnaTotal > i; i++) {
				if (validateHorizontal(dna[i])) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Allows you to go through the matrix of and diagonally, comparing each letter
	 * on its left and right side one by one.
	 * 
	 * @param dnaTotal Size of letters found in DNA dnaTable Matrix generated
	 * 
	 * @return boolean: if found four matches diagonally
	 */
	public static boolean validateDiagonal(int dnaTotal, char[][] dnaTable) {
		int matchLetter = 1;
		for (int i = 0; i < dnaTotal; i++) {
			for (int j = 0; j < dnaTotal; j++) {
				if (j == 0 || i == 0) {
					continue;
				}
				/*
				 * if (i == 0) { continue; }
				 */

				if (dnaTable[i - 1][j - 1] == dnaTable[i][j]) {
					matchLetter++;
				} else {
					matchLetter = 1;
				}

				if (matchLetter == 4) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Allows you to go through the matrix of and vertically, comparing each letter
	 * on its left and right side one by one.
	 * 
	 * @param dnaTotal Size of letters found in DNA dnaTable Matrix generated
	 * 
	 * @return boolean: if found four matches vertically
	 */
	public static boolean validateVertical(int dnaTotal, char[][] dnaTable) {
		int matchLetter = 1;
		for (int i = 0; i < dnaTotal; i++) {
			for (int j = 0; j < dnaTotal; j++) {
				if (j == 0) {
					continue;
				}
				if (dnaTable[j][i] == dnaTable[j - 1][i]) {
					matchLetter++;
				} else {
					matchLetter = 1;
				}
				if (matchLetter == 4) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Allows you to go through the matrix of and vertically, comparing each letter
	 * on its left and right side one by one.
	 * 
	 * @param dnaTotal Size of letters found in DNA dnaTable Matrix generated
	 * 
	 * @return boolean: if found four matches horizontally
	 */
	private static boolean validateHorizontal(String dna) {
		int matchLetter = 1;
		char[] dnaChars = dna.toCharArray();

		for (int i = 0; i < dnaChars.length; i++) {
			if (i == 0) {
				continue;
			}
			if (dnaChars[i] == dnaChars[i - 1]) {
				matchLetter++;
			} else {
				matchLetter = 1;
			}
			if (matchLetter == 4) {
				return true;
			}
		}
		return false;
	}

}
