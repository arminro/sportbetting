package com.sportsbetting.domain.data_access;

import com.sportsbetting.domain.entities.Wager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

;

@Repository
public interface WagerRepository extends CrudRepository<Wager, Long> {
}
