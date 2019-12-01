package com.sportsbetting.domain.data_access;

import com.sportsbetting.domain.entities.Wager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

;import java.util.List;

@Repository
public interface WagerRepository extends CrudRepository<Wager, Long> {
    List<Wager> findWagersByPlayerId(long playerId);
}
