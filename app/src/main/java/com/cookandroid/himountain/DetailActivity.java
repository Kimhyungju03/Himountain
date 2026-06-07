package com.cookandroid.himountain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    private static final String API_KEY =
            "661e993d0b1696bea1d8815a7d6d0c55";

    ImageView imgCourse;

    TextView txtMountainName;
    TextView txtInfo;
    TextView txtWeather;
    TextView txtReviewList;

    Button btnTodayMountain;
    Button btnResetMountain;
    Button btnMap;
    Button btnReview;
    Button btnEditReview;
    Button btnDeleteReview;

    RatingBar ratingBar;
    EditText edtReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgCourse = findViewById(R.id.imgCourse);

        txtMountainName = findViewById(R.id.txtMountainName);
        txtInfo = findViewById(R.id.txtInfo);
        txtWeather = findViewById(R.id.txtWeather);
        txtReviewList = findViewById(R.id.txtReviewList);

        btnTodayMountain = findViewById(R.id.btnTodayMountain);
        btnResetMountain = findViewById(R.id.btnResetMountain);
        btnMap = findViewById(R.id.btnMap);
        btnReview = findViewById(R.id.btnReview);
        btnEditReview = findViewById(R.id.btnEditReview);
        btnDeleteReview = findViewById(R.id.btnDeleteReview);

        ratingBar = findViewById(R.id.ratingBar);
        edtReview = findViewById(R.id.edtReview);

        String mountainName =
                getIntent().getStringExtra("mountainName");

        if (mountainName == null) {
            finish();
            return;
        }

        txtMountainName.setText(mountainName);

        String detail = "";
        String mapUrl = "";

        double lat = 0;
        double lon = 0;

        switch (mountainName) {

            case "한라산":

                mapUrl =
                        "https://www.google.com/maps/search/?api=1&query=33.3617,126.5292";

                imgCourse.setImageResource(
                        R.drawable.route_hanrasan);

                detail =
                        "📍 위치\n제주시 1100로 2070-61\n\n" +
                                "🏔 해발\n1947m\n\n" +
                                "⭐ 난이도\n★★★★☆\n\n" +
                                "🥾 준비물\n등산화\n우비\n물 2L 이상\n간식\n\n" +
                                "🌸 추천 계절\n봄, 가을";

                lat = 33.3617;
                lon = 126.5292;
                break;

            case "지리산":

                mapUrl =
                        "https://www.google.com/maps/search/?api=1&query=35.3360,127.7300";

                imgCourse.setImageResource(
                        R.drawable.route_jirisan);

                detail =
                        "📍 위치\n전남 구례군 마산면 화엄사로 539\n\n" +
                                "🏔 해발\n1915m\n\n" +
                                "⭐ 난이도\n★★★★★\n\n" +
                                "🥾 준비물\n등산화\n우비\n보조배터리\n물 2L 이상\n\n" +
                                "🌸 추천 계절\n봄, 가을";

                lat = 35.3360;
                lon = 127.7300;
                break;

            case "설악산":

                mapUrl =
                        "https://www.google.com/maps/search/?api=1&query=38.1194,128.4656";

                imgCourse.setImageResource(
                        R.drawable.route_seolaksan);

                detail =
                        "📍 위치\n강원도 속초시 설악산로 833\n\n" +
                                "🏔 해발\n1708m\n\n" +
                                "⭐ 난이도\n★★★★☆\n\n" +
                                "🥾 준비물\n등산화\n방풍자켓\n물\n\n" +
                                "🌸 추천 계절\n가을";

                lat = 38.1194;
                lon = 128.4656;
                break;

            case "북한산":

                mapUrl =
                        "https://www.google.com/maps/search/?api=1&query=37.6587,126.9770";

                imgCourse.setImageResource(
                        R.drawable.route_bukhansan);

                detail =
                        "📍 위치\n서울 강북구 우이동\n\n" +
                                "🏔 해발\n836m\n\n" +
                                "⭐ 난이도\n★★☆☆☆\n\n" +
                                "🥾 준비물\n운동화\n물\n간식\n\n" +
                                "🌸 추천 계절\n사계절";

                lat = 37.6587;
                lon = 126.9770;
                break;
        }

        txtInfo.setText(detail);

        loadWeather(lat, lon);
        loadForecast(lat, lon);

        SharedPreferences reviewPref =
                getSharedPreferences(
                        "MountainReview",
                        MODE_PRIVATE);

        txtReviewList.setText(
                reviewPref.getString(
                        mountainName,
                        ""));

        btnTodayMountain.setOnClickListener(v -> {

            SharedPreferences pref =
                    getSharedPreferences(
                            "MountainApp",
                            MODE_PRIVATE);

            pref.edit()
                    .putString(
                            "todayMountain",
                            mountainName)
                    .apply();

            Toast.makeText(
                            this,
                            mountainName + " 등록 완료!",
                            Toast.LENGTH_SHORT)
                    .show();
        });

        btnResetMountain.setOnClickListener(v -> {

            SharedPreferences pref =
                    getSharedPreferences(
                            "MountainApp",
                            MODE_PRIVATE);

            pref.edit()
                    .remove("todayMountain")
                    .apply();

            Toast.makeText(
                            this,
                            "오늘 갈 산 초기화 완료",
                            Toast.LENGTH_SHORT)
                    .show();
        });

        final String finalMapUrl = mapUrl;

        btnMap.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(finalMapUrl));

            startActivity(intent);
        });

        btnReview.setOnClickListener(v -> {

            String review =
                    edtReview.getText().toString();

            float rating =
                    ratingBar.getRating();

            if (review.isEmpty()) {
                Toast.makeText(
                                this,
                                "리뷰를 입력하세요",
                                Toast.LENGTH_SHORT)
                        .show();
                return;
            }

            String today =
                    new SimpleDateFormat(
                            "yyyy-MM-dd",
                            Locale.getDefault())
                            .format(new Date());

            SharedPreferences pref =
                    getSharedPreferences(
                            "MountainReview",
                            MODE_PRIVATE);

            String reviewText =
                    "📅 " + today +
                            "\n⭐ 평점 : " + rating +
                            "\n📝 " + review;

            pref.edit()
                    .putString(
                            mountainName,
                            reviewText)
                    .apply();

            txtReviewList.setText(reviewText);

            edtReview.setText("");
            ratingBar.setRating(0);

            Toast.makeText(
                            this,
                            "리뷰 등록 완료",
                            Toast.LENGTH_SHORT)
                    .show();
        });

        btnEditReview.setOnClickListener(v -> {

            edtReview.setText(
                    txtReviewList.getText()
                            .toString());

        });

        btnDeleteReview.setOnClickListener(v -> {

            SharedPreferences pref =
                    getSharedPreferences(
                            "MountainReview",
                            MODE_PRIVATE);

            pref.edit()
                    .remove(mountainName)
                    .apply();

            txtReviewList.setText("");

            Toast.makeText(
                            this,
                            "리뷰 삭제 완료",
                            Toast.LENGTH_SHORT)
                    .show();
        });
    }

    private void loadWeather(
            double lat,
            double lon) {

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(
                                "https://api.openweathermap.org/")
                        .addConverterFactory(
                                GsonConverterFactory.create())
                        .build();

        WeatherApi api =
                retrofit.create(
                        WeatherApi.class);

        Call<WeatherResponse> call =
                api.getWeather(
                        lat,
                        lon,
                        API_KEY,
                        "metric",
                        "kr");
        call.enqueue(
                new Callback<WeatherResponse>() {

                    @Override
                    public void onResponse(
                            Call<WeatherResponse> call,
                            Response<WeatherResponse> response) {

                        if (response.isSuccessful()
                                && response.body() != null) {

                            WeatherResponse weather =
                                    response.body();

                            String weatherText =
                                    "🌡 현재 온도 : "
                                            + weather.main.temp + "℃\n\n"

                                            + "🤗 체감 온도 : "
                                            + weather.main.feels_like + "℃\n\n"

                                            + "💧 습도 : "
                                            + weather.main.humidity + "%\n\n"

                                            + "☁️ 날씨 : "
                                            + weather.weather.get(0).description + "\n\n"

                                            + "💨 풍속 : "
                                            + weather.wind.speed + "m/s";

                            txtWeather.setText(weatherText);
                        }
                    }

                    @Override
                    public void onFailure(
                            Call<WeatherResponse> call,
                            Throwable t) {

                        txtWeather.setText(
                                "날씨 정보를 불러오지 못했습니다.");
                    }
                });
    }

    private void loadForecast(
            double lat,
            double lon) {

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(
                                "https://api.openweathermap.org/")
                        .addConverterFactory(
                                GsonConverterFactory.create())
                        .build();

        ForecastApi api =
                retrofit.create(
                        ForecastApi.class);

        api.getForecast(
                        lat,
                        lon,
                        API_KEY,
                        "metric",
                        "kr")
                .enqueue(
                        new Callback<ForecastResponse>() {

                            @Override
                            public void onResponse(
                                    Call<ForecastResponse> call,
                                    Response<ForecastResponse> response) {

                                if (response.isSuccessful()
                                        && response.body() != null) {

                                    ForecastResponse forecast =
                                            response.body();

                                    String text =
                                            txtWeather.getText().toString()

                                                    + "\n\n📅 3일 예보"

                                                    + "\n내일 : "
                                                    + forecast.list.get(8).main.temp
                                                    + "℃"

                                                    + "\n모레 : "
                                                    + forecast.list.get(16).main.temp
                                                    + "℃"

                                                    + "\n글피 : "
                                                    + forecast.list.get(24).main.temp
                                                    + "℃";

                                    txtWeather.setText(text);
                                }
                            }

                            @Override
                            public void onFailure(
                                    Call<ForecastResponse> call,
                                    Throwable t) {
                            }
                        });
    }
}
