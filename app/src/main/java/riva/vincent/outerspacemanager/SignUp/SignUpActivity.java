package riva.vincent.outerspacemanager.SignUp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.ApiService;
import riva.vincent.outerspacemanager.Api.Requests.AuthCreateRequest;
import riva.vincent.outerspacemanager.Api.Requests.AuthLoginRequest;
import riva.vincent.outerspacemanager.Api.Responses.AuthCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.AuthLoginResponse;
import riva.vincent.outerspacemanager.Login.LoginActivity;
import riva.vincent.outerspacemanager.Main.MainActivity;
import riva.vincent.outerspacemanager.R;

/**
 * Created by vriva on 26/03/2018.
 */

public class SignUpActivity extends Activity implements SignUpView, View.OnClickListener {

    private EditText loginEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private Button loginButton;
    private Button signUpButton;

    private SignUpPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkForExistingToken();

        setContentView(R.layout.activity_signup);

        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        emailEditText = findViewById(R.id.emailEditText);
        loginButton = findViewById(R.id.logInButton);
        signUpButton = findViewById(R.id.signUpButton);

        presenter = new SignUpPresenterImpl(this);

        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    private void checkForExistingToken() {
        SharedPreferences settings = getSharedPreferences("OuterSpaceManager", 0);
        String token = settings.getString("token", "");
        Long expires = settings.getLong("expires", 0L);

        if(token != "" && token != null && expires > new Date().getTime()) {
            Log.d("OuterSpace", "checkForExistingToken: " + token);
            showMainActivity();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.logInButton:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.signUpButton:
                presenter.signUp(loginEditText.getText().toString(), passwordEditText.getText().toString(), emailEditText.getText().toString());
                break;
        }
    }

    @Override
    public void showToastFailure() {
        Api.showFailureToast(getApplicationContext());
    }

    @Override
    public void showMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void setToken(String token, double expires) {
        SharedPreferences settings = getSharedPreferences("OuterSpaceManager", 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("token", token);
        editor.putLong("expires", Double.valueOf(expires).longValue());
        editor.commit();
    }
}
