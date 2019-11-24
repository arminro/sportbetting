package com.sportsbetting.domain.data_access;

import com.sportsbetting.domain.entities.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
