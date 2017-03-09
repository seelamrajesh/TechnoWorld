package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
// Category DO with setter & getter method
public class Category {
	@Id
	private String cate_id;
	@Column
	private String cate_name;
	@Column
	private String cate_desc;
	
	


	public String getCate_id() {
		return cate_id;
	}

	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}

	public String getCate_name() {
		return cate_name;
	}

	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}

	public String getCate_desc() {
		return cate_desc;
	}

	public void setCate_desc(String cate_desc) {
		this.cate_desc = cate_desc;
	}

}
