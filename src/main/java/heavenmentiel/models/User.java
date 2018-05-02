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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="User_")
@Getter @Setter
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
	
}
