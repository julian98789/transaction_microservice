package com.transaction_microservice.transaction.domain.util;

public class Util {

    public static final String ROLE_ADMIN = "hasRole('ADMIN')";
    public static final String ROLE_AUX_BODEGA = "hasRole('AUX_BODEGA')";
    public static final String ROLE_CLIENT = "hasRole('CLIENT')";
    public static final String CLAIM_AUTHORITIES = "authorities";
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String STOCK_SERVICE_NAME = "stock";
    public static final String STOCK_SERVICE_URL = "http://localhost:8080";
    public static final String ARTICLE_NOT_FOUND = "Articulo no encontrado";
    public static final String NEXT_SUPPLY_DATE_NOT_FOUND = "Se requiere la próxima fecha de suministro" ;
    public static final String ARTICLE_QUANTITY_REQUIRED = "Se requiere cantidad del articulo";
    public static final String SUPPLY_NOT_FOUND = "Suministro no encontrado";
    public static final String NEXT_SUPPLY_DATE_INVALID = "La proxima fecha de suministro no puede ser anterior a hoy";

    public static final int TOKEN_PREFIX_LENGTH = 7;
    public static final String DATE_FORMAT = "yyyy-MM-dd";


    private Util() {}

}
