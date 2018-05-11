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
	
	@Id	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "achatseventsId") 
	Integer id;
	
	@ManyToOne	Event event;
	@ManyToOne	Commande commande;
	
	int qte;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}
	
}
