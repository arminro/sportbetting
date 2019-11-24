package com.sportsbetting.domain.data_access;

import com.sportsbetting.domain.entities.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BetRepository extends CrudRepository<Bet, Long> {
}
