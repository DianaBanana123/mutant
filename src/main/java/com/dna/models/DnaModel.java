package com.dna.models;

import javax.persistence.*;

/**
 * @author dirojas
 *
 */
@Entity
@Table(name = "dna")
public class DnaModel {
   
    @Id
    @Column(unique = true, nullable = false)
    private String dna;
    
    private boolean mutant;
    
	
	public String getDna() {
		return dna;
	}
	public void setDna(String dna) {
		this.dna = dna;
	}
	public boolean isMutant() {
		return mutant;
	}
	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}   
    
}