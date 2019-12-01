package com.sportsbetting.domain.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Tennis")
public class TennisSportEvent extends SportEvent {
}
