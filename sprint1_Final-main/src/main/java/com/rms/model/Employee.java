package com.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(min = 2, message = "Name should have atleast 2 charactors")
	@Column(name = "e_name")
	private String Name;

	@NotNull
	@Size(min = 2, message = "Contact should have atleast 2 charactors")
	@Column(name = "e_contact")
	private String contact;

	@NotNull
	@Size(min = 2, message = "Shift should have atleast 2 charactors")
	@Column(name = "e_shift")
	private String shift;

	@NotNull
	@Size(min = 2, message = "Adress should have atleast 2 charactors")
	@Column(name = "e_address")
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Employee(String name, String contact, String shift, String address) {
		super();
		Name = name;
		this.contact = contact;
		this.shift = shift;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

}
