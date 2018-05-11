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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<AchatsEvents> getAchatsEvents() {
		return achatsEvents;
	}

	public void setAchatsEvents(List<AchatsEvents> achatsEvents) {
		this.achatsEvents = achatsEvents;
	}
	
	

}
