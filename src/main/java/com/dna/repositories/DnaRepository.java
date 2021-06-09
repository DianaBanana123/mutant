package com.dna.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dna.models.DnaModel;

/**
 * @author dirojas
 *
 */
@Repository
public interface DnaRepository extends CrudRepository<DnaModel, Long> {

}
