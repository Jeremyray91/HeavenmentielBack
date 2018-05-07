package heavenmentiel.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(sequenceName = "commande_id_seq", name = "commandeId", initialValue = 1, allocationSize = 1)
public class Commande {

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commandeId") 
	Integer id;
	
	Date date;
	
	@ManyToOne
	User user;
	
	@OneToMany(mappedBy = "commande")
	List<AchatsEvents> achatsEvents;

}