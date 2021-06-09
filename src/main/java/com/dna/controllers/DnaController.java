package com.dna.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dna.dto.DnaDto;
import com.dna.dto.StatDto;
import com.dna.services.DnaService;

/**
 * @author dirojas
 *
 */
@RestController
public class DnaController {
	
	@Autowired
	DnaService dnaService;
		
	@PostMapping("/mutant")	
	public ResponseEntity<String> isMutant(@RequestBody DnaDto dna) {		
		return dnaService.isMutant(dna.getDna().toArray(new String[dna.getDna().size()]));
	}
	
	@GetMapping("/stats")
	public StatDto getStats() {		
		return dnaService.getStats();
		
	}
}
