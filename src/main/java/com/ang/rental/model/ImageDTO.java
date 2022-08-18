package com.ang.rental.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ImageDTO {

	private long listId;
	private MultipartFile image;
	private String imageType;
	
	
	public long getListId() {
		return listId;
	}
	public void setListId(long listId) {
		this.listId = listId;
	}

	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
}
