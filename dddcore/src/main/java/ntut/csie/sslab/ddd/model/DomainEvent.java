package ntut.csie.sslab.ddd.model;

import java.io.Serializable;
import java.util.Date;

public interface DomainEvent extends Serializable {
	public Date occurredOn();
}
