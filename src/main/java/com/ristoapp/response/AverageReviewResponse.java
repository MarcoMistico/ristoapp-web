package com.ristoapp.response;

public class AverageReviewResponse {

	private long idProduct;
	private int productReviewsAmount;
	private double averageProductReviewsValue;
	
	public AverageReviewResponse() {
	}
	
	public AverageReviewResponse(long idProduct, int productReviewsAmount, double averageProductReviewsValue) {
		this.idProduct = idProduct;
		this.productReviewsAmount = productReviewsAmount;
		this.averageProductReviewsValue = averageProductReviewsValue;
	}
	
	public long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}

	public int getProductReviewsAmount() {
		return productReviewsAmount;
	}
	
	public void setProductReviewsAmount(int productReviewsAmount) {
		this.productReviewsAmount = productReviewsAmount;
	}
	
	public double getAverageProductReviewsValue() {
		return averageProductReviewsValue;
	}
	
	public void setAverageProductReviewsValue(double averageProductReviewsValue) {
		this.averageProductReviewsValue = averageProductReviewsValue;
	}
}
