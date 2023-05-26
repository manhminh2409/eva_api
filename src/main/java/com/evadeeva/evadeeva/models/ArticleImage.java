package com.evadeeva.evadeeva.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="article_image")
public class ArticleImage implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int article_image_id;
	
	@Column(name = "url")
	private String url;
	
	@ManyToOne
	@JoinColumn(name = "article_id")
	Article article;
}

