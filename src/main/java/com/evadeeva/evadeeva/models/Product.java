package com.evadeeva.evadeeva.models;

import java.util.*;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String sku;

	@Column
	private String material;

	@Column
	private int visited;

	@Column
	private int price;

	@Column
	private int salePrice;

	@Column
	private Date modifiedDate;

	@Column
	private Date createdDate;

	@Column
	private int status;

	@ManyToOne
	@JoinColumn(name = "product_author_id")
	private User productAuthor;

	@ManyToOne
	@JoinColumn(name = "product_category_id")
	private Category productCategory;

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Color> colors = new ArrayList<>();

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProductImage> productImages = new ArrayList<>();
}
