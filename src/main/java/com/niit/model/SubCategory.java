package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class SubCategory {
	
	@Id
	private String sub_cate_id;
	@Column
	private String sub_cate_name;
	@Column
	private String subcate_cate;
	public String getSub_cate_id() {
		return sub_cate_id;
	}
	public void setSub_cate_id(String sub_cate_id) {
		this.sub_cate_id = sub_cate_id;
	}
	public String getSub_cate_name() {
		return sub_cate_name;
	}
	public void setSub_cate_name(String sub_cate_name) {
		this.sub_cate_name = sub_cate_name;
	}
	public String getSubcate_cate() {
		return subcate_cate;
	}
	public void setSubcate_cate(String subcate_cate) {
		this.subcate_cate = subcate_cate;
	}
	
	

}
