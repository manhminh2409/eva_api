package com.evadeeva.evadeeva.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column(nullable = false)
	private String title;

	@Column
	private String description;

	@Column
	private Date createdDate;

	@Column
	private Date modifiedDate;

	@Column
	private int type;//xác định là 0: sản phẩm, 1: chính sách, 2: bài viết, 3: tuyển dụng

	@Column(nullable = false)
	private int status;

	@ManyToOne
	@JoinColumn(name = "parent_category_id")
	private Category parentCategory;

	@OneToMany(mappedBy = "parentCategory")
	private List<Category> childCategories = new ArrayList<>();

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<Banner> banners = new HashSet<>();

	@OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<>();
}