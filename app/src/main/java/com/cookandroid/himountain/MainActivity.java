package com.cookandroid.himountain;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Mountain> mountainList;
    MountainAdapter adapter;
    Button btnAI;

    TextView txtTodayMountain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        txtTodayMountain = findViewById(R.id.txtTodayMountain);

        mountainList = new ArrayList<>();

        SharedPreferences ratingPref =
                getSharedPreferences(
                        "MountainRating",
                        MODE_PRIVATE);

        float totalHallasan =
                ratingPref.getFloat(
                        "한라산_total",
                        0);

        int countHallasan =
                ratingPref.getInt(
                        "한라산_count",
                        0);

        String hallasanRating =
                countHallasan == 0 ?
                        "평가 없음" :
                        String.format(
                                "%.1f",
                                totalHallasan / countHallasan);

        mountainList.add(new Mountain(
                "한라산",
                "1947m",
                "제주시 1100로 2070-61",
                "★★★★☆",
                hallasanRating,
                R.drawable.hallasan));

        float totalJiri =
                ratingPref.getFloat(
                        "지리산_total",
                        0);

        int countJiri =
                ratingPref.getInt(
                        "지리산_count",
                        0);

        String jiriRating =
                countJiri == 0 ?
                        "평가 없음" :
                        String.format(
                                "%.1f",
                                totalJiri / countJiri);

        mountainList.add(new Mountain(
                "지리산",
                "1915m",
                "구례군 마산면 화엄사로 539",
                "★★★★★",
                jiriRating,
                R.drawable.jirisan));

        float totalSeorak =
                ratingPref.getFloat(
                        "설악산_total",
                        0);

        int countSeorak =
                ratingPref.getInt(
                        "설악산_count",
                        0);

        String seorakRating =
                countSeorak == 0 ?
                        "평가 없음" :
                        String.format(
                                "%.1f",
                                totalSeorak / countSeorak);

        mountainList.add(new Mountain(
                "설악산",
                "1708m",
                "속초시 설악산로 833",
                "★★★★☆",
                seorakRating,
                R.drawable.seoraksan));

        float totalBukhan =
                ratingPref.getFloat(
                        "북한산_total",
                        0);

        int countBukhan =
                ratingPref.getInt(
                        "북한산_count",
                        0);

        String bukhanRating =
                countBukhan == 0 ?
                        "평가 없음" :
                        String.format(
                                "%.1f",
                                totalBukhan / countBukhan);

        mountainList.add(new Mountain(
                "북한산",
                "836m",
                "서울 강북구 우이동",
                "★★☆☆☆",
                bukhanRating,
                R.drawable.bukhansan));

        adapter =
                new MountainAdapter(mountainList);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        updateTodayMountain();
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateTodayMountain();
        loadRatings();
    }

    private void updateTodayMountain() {

        SharedPreferences pref =
                getSharedPreferences(
                        "MountainApp",
                        MODE_PRIVATE);

        String todayMountain =
                pref.getString(
                        "todayMountain",
                        "없음");

        txtTodayMountain.setText(
                "오늘 갈 산 : " +
                        todayMountain);
    }

    private void loadRatings() {

        mountainList.clear();

            SharedPreferences ratingPref =
                    getSharedPreferences(
                            "MountainRating",
                            MODE_PRIVATE);

            float totalHallasan =
                    ratingPref.getFloat("한라산_total", 0);
            int countHallasan =
                    ratingPref.getInt("한라산_count", 0);

            String hallasanRating =
                    countHallasan == 0 ?
                            "평가 없음" :
                            String.format("%.1f",
                                    totalHallasan / countHallasan);

            mountainList.add(new Mountain(
                    "한라산",
                    "1947m",
                    "제주시 1100로 2070-61",
                    "★★★★☆",
                    hallasanRating,
                    R.drawable.hallasan));

            float totalJiri =
                    ratingPref.getFloat("지리산_total", 0);
            int countJiri =
                    ratingPref.getInt("지리산_count", 0);

            String jiriRating =
                    countJiri == 0 ?
                            "평가 없음" :
                            String.format("%.1f",
                                    totalJiri / countJiri);

            mountainList.add(new Mountain(
                    "지리산",
                    "1915m",
                    "구례군 마산면 화엄사로 539",
                    "★★★★★",
                    jiriRating,
                    R.drawable.jirisan));

            float totalSeorak =
                    ratingPref.getFloat("설악산_total", 0);
            int countSeorak =
                    ratingPref.getInt("설악산_count", 0);

            String seorakRating =
                    countSeorak == 0 ?
                            "평가 없음" :
                            String.format("%.1f",
                                    totalSeorak / countSeorak);

            mountainList.add(new Mountain(
                    "설악산",
                    "1708m",
                    "속초시 설악산로 833",
                    "★★★★☆",
                    seorakRating,
                    R.drawable.seoraksan));

            float totalBukhan =
                    ratingPref.getFloat("북한산_total", 0);
            int countBukhan =
                    ratingPref.getInt("북한산_count", 0);

            String bukhanRating =
                    countBukhan == 0 ?
                            "평가 없음" :
                            String.format("%.1f",
                                    totalBukhan / countBukhan);

            mountainList.add(new Mountain(
                    "북한산",
                    "836m",
                    "서울 강북구 우이동",
                    "★★☆☆☆",
                    bukhanRating,
                    R.drawable.bukhansan));

            adapter.notifyDataSetChanged();

        Button btnAI;

        btnAI = findViewById(R.id.btnAI);

        btnAI.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            RecommendActivity.class);

            startActivity(intent);
        });
        };
    }