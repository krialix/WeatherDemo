package com.krialix.weatherdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.krialix.weatherdemo.model.OpenWeatherResponse;
import com.krialix.weatherdemo.rest.ApiManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = "WeatherActivity";

    private static final String DEGREE = "\u2103";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.main_content)
    CoordinatorLayout mMainContent;

    @Bind(R.id.location_name)
    TextView mLocationTextView;

    @Bind(R.id.temperature)
    TextView mTempTextView;

    @Bind(R.id.status)
    TextView mStatusTextView;

    @Bind(R.id.wind)
    TextView mWindTextView;

    @Bind(R.id.humidity)
    TextView mHumidityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        makeRequest("İzmir");
    }

    private void makeRequest(String location) {
        ApiManager.getInstance()
                .getWeatherService()
                .getWeather(location, Constants.API_KEY, "metric")
                .enqueue(new Callback<OpenWeatherResponse>() {
                    @Override
                    public void onResponse(Call<OpenWeatherResponse> call, Response<OpenWeatherResponse> response) {
                        setData(response);
                    }

                    @Override
                    public void onFailure(Call<OpenWeatherResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setData(Response<OpenWeatherResponse> response) {
        OpenWeatherResponse body = response.body();

        mLocationTextView.setText(body.getName());
        mTempTextView.setText(String.valueOf(body.getMain().getTemp()) + " " + DEGREE);
        mStatusTextView.setText(body.getWeather().get(0).getMain());

        mHumidityTextView.setText(String.valueOf(body.getMain().getHumidity()));
        mWindTextView.setText(String.valueOf(body.getWind().getSpeed()));
    }

    @OnClick(R.id.fab)
    public void changeLocation() {
        View view = getLayoutInflater().inflate(R.layout.dialog_location, null);
        final EditText locationText = (EditText) view.findViewById(R.id.location_change);

        new AlertDialog.Builder(this)
                .setTitle("Yeni lokasyon")
                .setView(view)
                .setPositiveButton("Değiştir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String location = locationText.getText().toString();

                        makeRequest(location);

                        Snackbar.make(mMainContent, "Lokasyon değişti.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                })
                .show();
    }
}
