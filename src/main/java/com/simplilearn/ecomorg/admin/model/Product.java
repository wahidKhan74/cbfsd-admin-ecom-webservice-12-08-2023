package com.simplilearn.ecomorg.admin.model;

import java.util.Date;
import java.util.List;

public class Product {

	// data properties
	private int productId;
	private String productTitle;
	private String productDescription;
	private String productCode;
	private List<String> images;
	private int thumbnailImage;
	private int price;
	private Date addedOn;
	private int rating;

	// default constructor
	public Product() { super(); }

	// parameterized constructor
	public Product(int productId, String productTitle, String productDescription, String productCode,
			List<String> images, int thumbnailImage, int price, Date addedOn, int rating) {
		super();
		this.productId = productId;
		this.productTitle = productTitle;
		this.productDescription = productDescription;
		this.productCode = productCode;
		this.images = images;
		this.thumbnailImage = thumbnailImage;
		this.price = price;
		this.addedOn = addedOn;
		this.rating = rating;
	}

	// getter & setter methods
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public int getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(int thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	// override to string
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productTitle=" + productTitle + ", productDescription="
				+ productDescription + ", productCode=" + productCode + ", images=" + images + ", thumbnailImage="
				+ thumbnailImage + ", price=" + price + ", addedOn=" + addedOn + ", rating=" + rating + "]";
	}
}
