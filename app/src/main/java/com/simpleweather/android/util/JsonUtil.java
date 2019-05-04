package com.simpleweather.android.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.simpleweather.android.db.City;
import com.simpleweather.android.db.Country;
import com.simpleweather.android.db.Province;
import com.simpleweather.android.gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(jsonObject.getString("name"));
                    province.setProvinceCode(jsonObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    City city = new City();
                    city.setCityName(jsonObject.getString("name"));
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCountryResponse(String response,int cityid){
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Country country= new Country();
                    country.setCountryName(jsonObject.getString("name"));
                    country.setCityId(cityid);
                    country.setWeatherId(jsonObject.getString("weather_id"));
                    country.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
