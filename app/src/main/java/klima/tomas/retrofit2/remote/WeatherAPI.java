package klima.tomas.retrofit2.remote;


import klima.tomas.retrofit2.data.Weather;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface WeatherAPI {

	String base_url = "http://api.openweathermap.org/data/2.5/";

	@GET("weather?q=Prague&units=metric&appid=665e58dcc458a27730d0b2130d109f69")
	Call<Weather> getWeather();

	class Factory{
		private static WeatherAPI service;
		public static WeatherAPI getInstance(){
			if(service == null){
				Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();

				service = retrofit.create(WeatherAPI.class);
				return service;
			} else {
				return service;
			}
		}
	}
}
