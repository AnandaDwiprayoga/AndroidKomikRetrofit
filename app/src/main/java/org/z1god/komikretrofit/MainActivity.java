package org.z1god.komikretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int MIN_ID = 1;
    public static final int MAX_ID = 2299;
    int currentId = MIN_ID;

    ProgressBar loading;
    ImageView imgComic;
    TextView tvJudulComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading = findViewById(R.id.loading);
        imgComic = findViewById(R.id.iv_comic);
        tvJudulComic = findViewById(R.id.judul_comic);
        Button btnFirst = findViewById(R.id.btn_go_first);
        Button btnLast = findViewById(R.id.btn_go_last);
        Button btnPrev = findViewById(R.id.btn_go_prev);
        Button btnNext = findViewById(R.id.btn_go_next);
        Button btnRandom = findViewById(R.id.btn_go_random);

        loading.setVisibility(View.VISIBLE);
        getComicById(currentId);

        btnFirst.setOnClickListener(this);
        btnLast.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnRandom.setOnClickListener(this);

    }

    private void getComicById(int id) {
        Call<ResponseModel> request = ApiConfig.getService().getComicById(id);
        request.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()){
                    ResponseModel result = response.body();

                    Picasso.get().load(result.imgUrl).into(imgComic);
                    tvJudulComic.setText(result.judul);
                }else{
                    Toast.makeText(MainActivity.this, "Failed to get commic", Toast.LENGTH_SHORT).show();
                }

                loading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int button = v.getId();

        switch (button){
            case R.id.btn_go_first :
                currentId = MIN_ID;

                getComicById(MIN_ID);
                break;
            case R.id.btn_go_last :
                currentId = MAX_ID;

                getComicById(MAX_ID);
                break;
            case R.id.btn_go_prev :
                if (currentId > MIN_ID){
                    currentId--;

                    getComicById(currentId);
                }else{
                    Toast.makeText(this, "sudah mencapai minimal halaman", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case R.id.btn_go_next :
                if (currentId < MAX_ID){
                    currentId++;

                    getComicById(currentId);
                }else{
                    Toast.makeText(this, "sudah maksimal minimal halaman", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case R.id.btn_go_random :
                Random rand = new Random();
                int randomId = rand.nextInt(MAX_ID)+1;

                currentId = randomId;

                getComicById(randomId);
            default:
                break;
        }
        loading.setVisibility(View.VISIBLE);
    }
}
