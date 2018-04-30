package heavenmentiel.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import heavenmentiel.enums.RoleEnum;

@Entity
@Table(name ="user_")
@SequenceGenerator(name="seq_user", sequenceName = "seq_user", initialValue = 1, allocationSize = 1)
public class User {

	@Id
	@GeneratedValue(generator = "seq_user")
	private Integer id;
	private String firstname;
	private String lastname;
	private String adress;
	private Integer zipCode;
	private String city;
	private String mail;
	private Integer tel;
	private String pwd;
	@Enumerated(EnumType.STRING)
	private RoleEnum role;
	
	public User() {
		
	}
	
	public User(String firstname, String lastname, String adress, Integer zipCode, String city, String mail, Integer tel, String pwd, RoleEnum role) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
		this.mail = mail;
		this.tel = tel;
		this.pwd = pwd;
		this.role = role;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
