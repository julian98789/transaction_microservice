package com.transaction_microservice.transaction.domain.util;

public class Util {

    public static final String ROLE_ADMIN = "hasRole('ADMIN')";
    public static final String ROLE_AUX_BODEGA = "hasRole('AUX_BODEGA')";
    public static final String ROLE_CLIENTE = "hasRole('CLIENTE')";
    public static final String CLAIM_AUTHORITIES = "authorities";
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String STOCK_SERVICE_NAME = "stock";
    public static final String STOCK_SERVICE_URL = "http://localhost:8080";
    public static final String ARTICLE_NOT_FOUND = "Articulo no encontrado";
    public static final String NEXT_SUPPLY_DATE_NOT_FOUND = "Se requiere la próxima fecha de suministro" ;
    public static final String FIND_NEXT_SUPPLY_DATE_BY_ARTICLE_ID_QUERY =
            "SELECT s.nextSupplyDate FROM SupplyEntity s WHERE s.articleId = :articleId ORDER BY s.nextSupplyDate DESC LIMIT 1";
    public static final String ARTICLE_ID_PARAM = "articleId";
    public static final String ARTICLE_QUANTITY_REQUIRED = "Se requiere cantidad del articulo";

    public static final int TOKEN_PREFIX_LENGTH = 7;



    private Util() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}
