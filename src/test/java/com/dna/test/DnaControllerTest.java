package com.dna.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dna.controllers.DnaController;
import com.dna.dto.DnaDto;
import com.dna.dto.StatDto;
import com.dna.models.DnaModel;
import com.dna.repositories.DnaRepository;


/**
 * @author dirojas
 *
 */
@SpringBootTest
class DnaControllerTest {	

    @MockBean
    private DnaRepository dnaRepositoryMock;	

	@Autowired
	DnaController dnaController = new DnaController();	
	
	static DnaDto dnaHumanDtoMock = new DnaDto();
	static DnaDto dnaMutantDtoMock = new DnaDto();
	
	static DnaModel dnaHumalModelMock = new DnaModel();
	static DnaModel dnaMutantModelMock = new DnaModel();
	
	static ArrayList<DnaModel> dnas = new ArrayList<DnaModel>();
		

	@BeforeAll
	static void setUpBeforeClass() throws Exception {  
		
		getDnaHumanDto();	
		getDnaMutantDto();
		
		getDnaHumanModel();
		getDnaMutantModel();
		
		addAllAdns();	
	}
	
	@Test
	void isHumanTest() {		
		dnaRepositoryMock.save(dnaHumalModelMock);				
		ResponseEntity<String> response = dnaController.isMutant(dnaHumanDtoMock);		
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), response);		
	}
	
	@Test
	void isMutantTest() {		
		dnaRepositoryMock.save(dnaMutantModelMock);				
		ResponseEntity<String> response = dnaController.isMutant(dnaMutantDtoMock);		
		assertEquals(new ResponseEntity<>(HttpStatus.OK), response);		
	}
	
	@Test	
	void getStatsTest() {		
		Mockito.when(dnaRepositoryMock.findAll()).thenReturn(dnas);		
		StatDto stat =  dnaController.getStats();
		
		assertEquals(1, stat.getCountHuman());
		assertEquals(1, stat.getCountMutant());
		assertThat(stat.getRatio() > 0);		
	}
	
	private static void getDnaHumanDto() {
		ArrayList<String> dnaHumanValue = new ArrayList<>();
		dnaHumanValue.add("ATGCCA");
		dnaHumanValue.add("AAGTCC");
		dnaHumanValue.add("CTACGT");
		dnaHumanValue.add("AACAGG");
		dnaHumanValue.add("GGCCCA");
		dnaHumanValue.add("TCCATG");				
		dnaHumanDtoMock.setDna(dnaHumanValue);
	}
	
	private static void getDnaMutantDto() {
		ArrayList<String> dnaMutantValue = new ArrayList<>();
		dnaMutantValue.add("ATGCGA");
		dnaMutantValue.add("CAGTGC");
		dnaMutantValue.add("TTATGT");
		dnaMutantValue.add("AGAAGG");
		dnaMutantValue.add("CCCCTA");
		dnaMutantValue.add("TCACTG");				
		dnaMutantDtoMock.setDna(dnaMutantValue);
	}
	
	private static DnaModel getDnaHumanModel() {		
		dnaHumalModelMock.setDna("\"ATGCCA\", \"AAGTCC\", \"CTACGT\", \"AACAGG\", \"GGCCCA\", \"TCCATG\"");
		dnaHumalModelMock.setMutant(false);
		return dnaHumalModelMock;
	}
	
	private static DnaModel getDnaMutantModel() {		
		dnaMutantModelMock.setDna("\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"");
		dnaMutantModelMock.setMutant(true);
		return dnaMutantModelMock;
	}
	
	private static void addAllAdns() {
		dnas.add(dnaHumalModelMock);
		dnas.add(dnaMutantModelMock);
	}
}
