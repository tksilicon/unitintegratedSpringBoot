package com.unitintegrated.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;




/**
 * The persistent class for the category database table.
 * 
 */




@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="category_id")
	private int categoryId;

	@Column(name="department_id")
	private int departmentId;

	private String description;

	private String name;
	
	
	//@OneToOne(fetch = FetchType.LAZY, optional = false)
	//@JoinColumns({
	       //@JoinColumn(name = "product_id",referencedColumnName = "product_id", insertable = false, updatable = false, nullable = false),
	       //@JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false, nullable = false)

	    //})
	
	
	
//	private ProductCategory productCategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", insertable = false, updatable = false, nullable = false)
	private Department department;
	
	

	public Category() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	

	

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	


	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	

	
	
	
	

}