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


	public Integer getSeatsAvailable() {
		return seatsAvailable;
	}


	public void setSeatsAvailable(Integer seatsAvaliable) {
		this.seatsAvailable = seatsAvaliable;
	}



	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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
	private Integer seatsAvailable;
	@NotBlank
	private String description;
	@NotBlank
	private Boolean available;
	
	private String img;
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}


	public Boolean isAvailable() {
		return available;
	}


	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	public Event(){}
	
	public Event(Long id,
			String name,
			String place,
			TypeEvent type,
			Date dateEvent,
			Float price,
			Integer seatsAvaliable,
			String description) {
									this.id = id;
									this.name = name;
									this.place = place;
									this.type = type;
									this.dateEvent = dateEvent;
									this.price = price;
									this.seatsAvailable = seatsAvaliable;
									this.description = description;
	}
}