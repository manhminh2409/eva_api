package com.evadeeva.evadeeva.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleImageModel {
	private long article_image_id;
	private String url;
	private long article_id;
}
