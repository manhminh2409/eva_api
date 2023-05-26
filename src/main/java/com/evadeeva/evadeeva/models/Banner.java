package com.evadeeva.evadeeva.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banner")
public class Banner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "banner_id")
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private String image;
	
	@Column
	private Date createdDate;
	
	@Column
	private Date modifiedDate;
	
	@Column(nullable = false)
	private int status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
}
