package klima.tomas.retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import klima.tomas.retrofit2.data.Weather;
import klima.tomas.retrofit2.remote.WeatherAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
	@BindView(R.id.btn) Button btn;
	@BindView(R.id.city) TextView city;
	@BindView(R.id.temp) TextView temp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}

	@OnClick(R.id.btn)
	public void btnRefresh(){
		WeatherAPI.Factory.getInstance().getWeather().enqueue(new Callback<Weather>() {
			@Override public void onResponse(Call<Weather> call, Response<Weather> response) {
				temp.setText(response.body().getMain().getTemp() + "°C");
				city.setText(response.body().getName());
			}

			@Override public void onFailure(Call<Weather> call, Throwable t) {
				Log.e("Failed", t.getMessage());
			}
		});
	}
}
