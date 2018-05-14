package heavenmentiel.models;

import java.util.Date;
import java.util.List;

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

import heavenmentiel.enums.RoleEnum;

@Entity
@Table(name = "User_")
@SequenceGenerator(sequenceName = "seq_id_user", name = "userId", initialValue = 1, allocationSize = 1)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userId")
	private Long id;
	//@NotBlank
	private String firstName;
	//@NotBlank
	private String lastName;
	//@NotNull
	private Date birthDay;
	//@NotBlank
	private String adress;
	//@NotNull
	private Integer zipCode;
	//@NotBlank
	private String city;
	//@NotBlank
	private String mail;
	//@NotNull
	private String tel;
	//@NotBlank
	private String pwd;
	@Enumerated(EnumType.STRING)
	//@NotBlank
	private RoleEnum role;
	
//	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
//	private List<Commande> commands;

	public User() {

	}

	public User(String firstName, String lastName, Date birthDay, String adress, Integer zipCode,
			String city, String mail, String tel, String pwd, RoleEnum role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDay = birthDay;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
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
	
//	public List<Commande> getCommands() {
//		return commands;
//	}
//
//	public void setCommands(List<Commande> commands) {
//		this.commands = commands;
//	}

}