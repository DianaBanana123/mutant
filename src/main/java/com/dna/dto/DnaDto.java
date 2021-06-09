package com.dna.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author dirojas
 *
 */
public class DnaDto implements Serializable{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 2897087415381208943L;
	
	
	ArrayList<String> dna = new ArrayList<String>();


	public ArrayList<String> getDna() {
		return dna;
	}


	public void setDna(ArrayList<String> dna) {
		this.dna = dna;
	}

}
