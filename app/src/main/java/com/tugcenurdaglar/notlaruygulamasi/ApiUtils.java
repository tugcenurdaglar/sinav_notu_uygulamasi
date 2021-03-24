package com.tugcenurdaglar.notlaruygulamasi;

public class ApiUtils {

    public static final String BASE_URL = "http://kasimadalan.pe.hu/";

    public static NotlarInterface getNotlarInterface(){
        return RetrofitClient.getClient(BASE_URL).create(NotlarInterface.class);
    }
}
