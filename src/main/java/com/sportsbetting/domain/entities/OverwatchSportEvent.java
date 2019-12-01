package com.sportsbetting.domain.entities;

import com.sportsbetting.utils.SportEventBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Overwatch")
public class OverwatchSportEvent extends SportEvent {

}

