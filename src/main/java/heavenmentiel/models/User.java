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
import heavenmentiel.enums.RoleEnum;


@Entity
@Table(name ="User_")
@SequenceGenerator(sequenceName = "seq_id_user", name="userId", initialValue = 1, allocationSize = 1)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userId")
	private Long id;
	@NotBlank
	private String firstname;
	@NotBlank
	private String lastname;
	@NotNull
	private Date dateNaissance;
	@NotBlank
	private String adress;
	@NotNull
	private Integer zipCode;
	@NotBlank
	private String city;
	@NotBlank
	private String mail;
	@NotNull
	private Integer tel;
	@NotBlank
	private String pwd;
	@Enumerated(EnumType.STRING)
	@NotBlank
	private RoleEnum role;
	
	public User() {}
	
	public User(Long id,
				String firstname,
				String lastname,
				Date dateNaissance,
				String adress,
				Integer zipCode,
				String city, String mail,
				Integer tel,
				String pwd,
				RoleEnum role) {
									this.id = id;
									this.firstname = firstname;
									this.lastname = lastname;
									this.dateNaissance = dateNaissance;
									this.adress = adress;
									this.zipCode = zipCode;
									this.city = city;
									this.mail = mail;
									this.tel = tel;
									this.pwd = pwd;
									this.role = role;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getTel() {
		return tel;
	}

	public void setTel(Integer tel) {
		this.tel = tel;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

}