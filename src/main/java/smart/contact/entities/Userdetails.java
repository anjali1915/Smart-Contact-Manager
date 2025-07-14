package smart.contact.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity

public class Userdetails {
	@Id
	@Column(name="email_id")
	@NotBlank(message="email can not be blank")
	@Pattern(regexp="^[a-zA-Z0-9._%=-]+@[a-zA_Z0-9]+\\.[a-zA-Z]{2,6}$", message="please enter a valid email format")
	private String email;
	
	@Column(name="first_name")
	@NotBlank(message="User name cannot be blank")
	@Size(min=3, message="User name should be greater than 3")
	private String fname;
	@Column(name="last_name")
	private String lname;
	@Column(name="address")
	private String address;
	
	@NotBlank(message="mobile number can not be blank")
	@Size(min=10,max=10,message="mobile number must be exactly 10 digits ")
	@Column(name="mobile_number")
	@Pattern(regexp="\\d{10}",message="mobile number should be number only")
	private String mobile;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Userdetails(String fname, String lname, String address, String email, String mobile) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.email = email;
		this.mobile = mobile;
	}
	public Userdetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
