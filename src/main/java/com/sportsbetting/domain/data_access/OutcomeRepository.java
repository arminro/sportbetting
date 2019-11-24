package com.sportsbetting.domain.data_access;

import com.sportsbetting.domain.entities.Outcome;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

;

@Repository
public interface OutcomeRepository extends CrudRepository<Outcome, Long> {
}
