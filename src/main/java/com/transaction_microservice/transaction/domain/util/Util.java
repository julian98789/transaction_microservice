package com.transaction_microservice.transaction.domain.util;

public class Util {

    public static final String ROLE_AUX_BODEGA = "hasRole('AUX_BODEGA')";
    public static final String ROLE_CLIENT = "hasRole('CLIENT')";
    public static final String CLAIM_AUTHORITIES = "authorities";
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String STOCK_SERVICE_NAME = "stock";
    public static final String STOCK_SERVICE_URL = "http://localhost:8080";
    public static final String CART_SERVICE_NAME = "cart";
    public static final String CART_SERVICE_URL = "http://localhost:8083";
    public static final String REPORT_SERVICE_NAME = "report";
    public static final String REPORT_SERVICE_URL = "http://localhost:8084";
    public static final String ARTICLE_NOT_FOUND = "Article not found";
    public static final String NEXT_SUPPLY_DATE_NOT_FOUND = "Next supply date required";
    public static final String ARTICLE_QUANTITY_REQUIRED = "Article quantity required";
    public static final String SUPPLY_NOT_FOUND = "Supply not found";
    public static final String NEXT_SUPPLY_DATE_INVALID = "Next supply date cannot be earlier than today";
    public static final String INSUFFICIENT_STOCK = "Insufficient stock for next supply date:";
    public static final int TOKEN_PREFIX_LENGTH = 7;
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String PURCHASE_ERROR = "Error making the purchase";
    public static final String CART_EMPTY = "The cart is empty";



    private Util() {}

}
