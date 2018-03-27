package riva.vincent.outerspacemanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import riva.vincent.outerspacemanager.Requests.AuthCreateRequest;
import riva.vincent.outerspacemanager.Responses.AuthCreate;

/**
 * Created by vriva on 26/03/2018.
 */

public class SignUpActivity extends Activity implements View.OnClickListener {

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        setLogin();


        loginButton.setOnClickListener(this);
    }

    public void setLogin() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<AuthCreate> authCreate = service.authCreate(new AuthCreateRequest("Treast", "secret", "vincent.riva@outlook.fr"));

        authCreate.enqueue(new Callback<AuthCreate>() {
            @Override
            public void onResponse(Call<AuthCreate> call, Response<AuthCreate> response) {
                Log.d("OKOK", "onResponse: OK" + response.toString());
            }

            @Override
            public void onFailure(Call<AuthCreate> call, Throwable t) {

                Log.d("PASOK", "onResponse: PAS OK" + t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
