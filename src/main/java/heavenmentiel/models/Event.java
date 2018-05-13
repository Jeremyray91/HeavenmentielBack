package heavenmentiel.models;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;
import heavenmentiel.enums.TypeEvent;

@Entity
@Table(name = "Event")
@SequenceGenerator(sequenceName = "event_id_seq", name = "eventId", initialValue = 1, allocationSize = 1)
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eventId")
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String place;
	@Enumerated(EnumType.STRING)
	//@NotNull
	private TypeEvent type;
	//@NotNull
	private Date dateEvent;
	//@NotNull
	private Float price;
	//@NotNull
	private Integer stock;
	@NotBlank
	private String description;
	@NotBlank
	private String shortDescription;
	//@NotNull
	private Boolean available;
	private String img;
	private String imgMin;
	
	@OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
	private Set<AchatsEvents> commandes;

	public Event() {
	}

	public Event(String name, String place, TypeEvent type, Date dateEvent, Float price,
			Integer stock, String description, String shortDescription, Boolean available, 
			String img, String imgMin, Set<AchatsEvents> commandes) {
		this.name = name;
		this.place = place;
		this.type = type;
		this.dateEvent = dateEvent;
		this.price = price;
		this.stock = stock;
		this.description = description;
		this.shortDescription = shortDescription;
		this.available = available;
		this.img = img;
		this.imgMin = imgMin;
		this.commandes = commandes;
	}

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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer seatsAvaliable) {
		this.stock = seatsAvaliable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Boolean getAvailable() {
		return available;
	}

	public Boolean isAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}

	public String getImgMin() {
		return imgMin;
	}

	public void setImgMin(String imgMin) {
		this.imgMin = imgMin;
	}

	public Set<AchatsEvents> getCommandes() {
		return commandes;
	}

	public void setCommandes(Set<AchatsEvents> commandes) {
		this.commandes = commandes;
	}
}