package eCom.backEnd.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import eCom.backEnd.entity.base.BaseEntity;
import eCom.backEnd.enums.Gender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class Users extends BaseEntity {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "first_name")
	@NotNull(message = "First Name should not be null")
	private String firstName;

	@Column(name = "last_name")
	@NotNull(message = "Last Name should not be null")
	private String lastName;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Column(name = "user_name")
	private String userName;

	@Email(message = "please enter valid email")
	@NotNull(message = "email should not be null")
	private String email;

	@JoinColumn(name = "user_id")
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	List<Authority> authorityList;

	@Valid
	@JoinColumn(name = "user_id")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Address> addressList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

}
