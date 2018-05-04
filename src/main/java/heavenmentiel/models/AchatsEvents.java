package heavenmentiel.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@SequenceGenerator(sequenceName = "achatsevents_id_seq", name = "achatseventsId", initialValue = 1, allocationSize = 1)
@Entity
public class AchatsEvents {
	
	@Id	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "achatseventsId") int id;
	@ManyToOne	Event event;
	@ManyToOne	Commande commande;
	
	int qte;
	
}
