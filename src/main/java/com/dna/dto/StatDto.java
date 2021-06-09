package com.dna.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dirojas
 *
 */
public class StatDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3446472122110976576L;
	
	@JsonProperty("count_mutant_dna")
	private long countMutant;
	
	@JsonProperty("count_human_dna")
	private long countHuman;
	
	@JsonProperty("ratio")
	private float ratio;

	public long getCountMutant() {
		return countMutant;
	}

	public void setCountMutant(long countMutant) {
		this.countMutant = countMutant;
	}

	public long getCountHuman() {
		return countHuman;
	}

	public void setCountHuman(long countHuman) {
		this.countHuman = countHuman;
	}

	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	
}
