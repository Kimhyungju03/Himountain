package com.cookandroid.himountain;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecommendActivity extends AppCompatActivity {

    EditText edtHeight;
    EditText edtWeight;

    RadioGroup rgLevel;

    Button btnRecommend;

    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);

        rgLevel = findViewById(R.id.rgLevel);

        btnRecommend = findViewById(R.id.btnRecommend);

        txtResult = findViewById(R.id.txtResult);

        btnRecommend.setOnClickListener(v -> {

            double height =
                    Double.parseDouble(
                            edtHeight.getText().toString());

            double weight =
                    Double.parseDouble(
                            edtWeight.getText().toString());

            double meter =
                    height / 100.0;

            double bmi =
                    weight / (meter * meter);

            String level = "";

            int checkedId =
                    rgLevel.getCheckedRadioButtonId();

            if(checkedId == R.id.rbBeginner)
                level = "초보";

            else if(checkedId == R.id.rbIntermediate)
                level = "중급";

            else
                level = "고급";

            String result;

            if(level.equals("초보")) {

                result =
                        "추천 산 : 북한산\n\n" +
                                "초보자에게 적합하고\n" +
                                "등산 입문용 코스입니다.";

            }
            else if(level.equals("중급")) {

                result =
                        "추천 산 : 설악산\n\n" +
                                "중급 난이도의 코스로\n" +
                                "전망이 뛰어납니다.";

            }
            else {

                result =
                        "추천 산 : 지리산\n\n" +
                                "고급자를 위한 장거리 코스입니다.";
            }

            result += "\n\nBMI : "
                    + String.format("%.1f", bmi);

            txtResult.setText(result);
        });
    }
}