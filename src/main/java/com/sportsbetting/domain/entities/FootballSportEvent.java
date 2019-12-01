package com.sportsbetting.domain.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Football")
public class FootballSportEvent extends SportEvent {
}
