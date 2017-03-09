package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "supplier")
@Component

// Supplier DO with setter & getter method
public class Supplier_Do {

	@Id
	private String sup_id;
	@Column
	private String sup_name;
	@Column
	private String sup_address;
	@Column
	private String sup_number;
	@Column
	private String sup_email;

	public String getSup_id() {
		return sup_id;
	}

	public void setSup_id(String sup_id) {
		this.sup_id = sup_id;
	}

	public String getSup_name() {
		return sup_name;
	}

	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}

	public String getSup_address() {
		return sup_address;
	}

	public void setSup_address(String sup_address) {
		this.sup_address = sup_address;
	}

	public String getSup_number() {
		return sup_number;
	}

	public void setSup_number(String sup_number) {
		this.sup_number = sup_number;
	}

	public String getSup_email() {
		return sup_email;
	}

	public void setSup_email(String sup_email) {
		this.sup_email = sup_email;
	}

}
