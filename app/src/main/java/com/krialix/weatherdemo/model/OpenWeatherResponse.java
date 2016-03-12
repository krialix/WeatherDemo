package com.krialix.weatherdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenWeatherResponse {

    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int id;
    private String name;
    private int cod;

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public int getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    public static class Coord {
        private float lon;
        private float lat;

        public float getLon() {
            return lon;
        }

        public float getLat() {
            return lat;
        }
    }

    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;

        public int getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }

    public static class Main {
        private float temp;
        private float pressure;
        private int humidity;
        @SerializedName("temp_min")
        private float tempMin;
        @SerializedName("temp_max")
        private float tempMax;
        @SerializedName("sea_level")
        private float seaLevel;
        @SerializedName("grnd_level")
        private float grndLevel;

        public float getTemp() {
            return temp;
        }

        public float getPressure() {
            return pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public float getTempMin() {
            return tempMin;
        }

        public float getTempMax() {
            return tempMax;
        }

        public float getSeaLevel() {
            return seaLevel;
        }

        public float getGrndLevel() {
            return grndLevel;
        }
    }

    public static class Clouds {
        private int all;
        public int getAll() {
            return all;
        }
    }

    public static class Sys {
        private float message;
        private String country;
        private int sunrise;
        private int sunset;

        public float getMessage() {
            return message;
        }

        public String getCountry() {
            return country;
        }

        public int getSunrise() {
            return sunrise;
        }

        public int getSunset() {
            return sunset;
        }
    }

    public static class Wind {
        private float speed;
        private float deg;

        public float getSpeed() {
            return speed;
        }

        public float getDeg() {
            return deg;
        }
    }
}
