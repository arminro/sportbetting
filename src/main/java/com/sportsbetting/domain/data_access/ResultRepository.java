package com.sportsbetting.domain.data_access;

import com.sportsbetting.domain.entities.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {
}
