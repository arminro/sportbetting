package com.sportsbetting.domain.data_access;

import com.sportsbetting.domain.entities.OverwatchSportEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverwatchSportEventRepository extends CrudRepository<OverwatchSportEvent, Long> {
}

