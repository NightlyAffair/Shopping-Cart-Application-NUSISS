package com.Assignment.shopping_carts.DTO;

public class ProductHistory {
  private int productId;
  private String productName;
  private String category;
  private Double unitprice;

  public ProductHistory(int productId, String productName, String category, Double unitprice) {
    this.productId = productId;
    this.productName = productName;
    this.category = category;
    this.unitprice = unitprice;
  }
}
