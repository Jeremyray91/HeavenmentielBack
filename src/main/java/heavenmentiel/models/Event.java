package heavenmentiel.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import heavenmentiel.enums.Status;
import heavenmentiel.enums.Theme;
import heavenmentiel.enums.TypeEvent;

@Entity
@Table(name ="Event")
@SequenceGenerator(sequenceName ="event_id_seq", name = "eventId", initialValue = 1, allocationSize = 1)
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eventId")
	private Long id;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public TypeEvent getType() {
		return type;
	}


	public void setType(TypeEvent type) {
		this.type = type;
	}


	public Date getDateEvent() {
		return dateEvent;
	}


	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}


	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}


	public Integer getSeatsAvaliable() {
		return seatsAvaliable;
	}


	public void setSeatsAvaliable(Integer seatsAvaliable) {
		this.seatsAvaliable = seatsAvaliable;
	}


	public Theme getTheme() {
		return theme;
	}


	public void setTheme(Theme theme) {
		this.theme = theme;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	@NotBlank
	private String name;
	@NotBlank
	private String place;
	@Enumerated(EnumType.STRING)
	@NotBlank
	private TypeEvent type; 
	@NotNull
	private Date dateEvent;
	@NotNull
	private Float price;
	@NotNull
	private Integer seatsAvaliable;
	@Enumerated(EnumType.STRING)
	@NotBlank
	private Theme theme;
	@NotBlank
	private String description;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	
	public Event(Long id,
			String name,
			String place,
			TypeEvent type,
			Date dateEvent,
			Float price,
			Integer seatsAvaliable,
			Theme theme,
			String description) {
									this.id = id;
									this.name = name;
									this.place = place;
									this.type = type;
									this.dateEvent = dateEvent;
									this.price = price;
									this.seatsAvaliable = seatsAvaliable;
									this.theme = theme;
									this.description = description;
	}
}