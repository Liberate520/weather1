package ru.samsung.weathercity;

public class WeatherCity {
    private String name;
    private float temperature;

    public WeatherCity(String cityInfo) {
        formatter(cityInfo);
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    private void formatter(String s){
        temperature = Float.parseFloat(getPole(s, "temperature"));
        name = getPole(s, "name");
    }

    @Override
    public String toString() {
        return "WeatherCity{" +
                "name='" + name + '\'' +
                ", temperature=" + temperature +
                '}';
    }

    private String getPole(String s, String pole){
        int i = s.indexOf(pole+"\":");
        i += pole.length()+2;
        String result = "";
        if (s.charAt(i) == '"'){
            i++;
        }
        for (int j = i; j < s.length(); j++){
            if (s.charAt(j) == '"' || s.charAt(j) == ',' || s.charAt(j) == ' '){
                break;
            }
            result += s.charAt(j);
        }
        return result;
    }
}
