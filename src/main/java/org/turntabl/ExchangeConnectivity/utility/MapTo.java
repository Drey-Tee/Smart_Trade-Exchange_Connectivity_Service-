package org.turntabl.ExchangeConnectivity.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapTo {

    public  static  <T> T convertToObject(String data, Class<T> type){
        ObjectMapper objectMapper = new ObjectMapper();
        T t = null;

        try{
            t = objectMapper.readValue(data, type);
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        return t;
    }

    public static  <T> String convertToString(T t){
        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;

        try {
            str = objectMapper.writeValueAsString(t);
        }catch (JsonProcessingException e){}

        return str;
    }
}
