package com.evadeeva.evadeeva.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="article")
public class Article implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long article_id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name = "created_date")
	private String createdDate;
	
	@Column(name = "modified_date")
	private String modifiedDate;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	User user;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	Category category;
	
	//@JsonIgnore
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "article")
	//List<ArticleComment> listComment;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "article")
	List<ArticleImage> listImage;
}

