package com.sportsbetting.domain.data_access;

import com.sportsbetting.domain.entities.SportEvent;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

;

@Repository
public interface EventRepository extends CrudRepository<SportEvent, Long> {
}
