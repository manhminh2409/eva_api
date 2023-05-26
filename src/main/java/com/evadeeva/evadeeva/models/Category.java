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
	@Column(name = "category_id")
	private long id;

	@Column
	private Date createdDate;

	@Column
	private String description;

	@Column
	private Date modifiedDate;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private int status;

	@ManyToOne
	@JoinColumn(name = "parent_category_id")
	private Category parentCategory;

	@OneToMany(mappedBy = "parentCategory")
	private List<Category> childCategories = new ArrayList<>();

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<Banner> banners = new HashSet<>();
}