package riva.vincent.outerspacemanager.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Main.MainActivity;
import riva.vincent.outerspacemanager.R;
import riva.vincent.outerspacemanager.SignUp.SignUpActivity;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signUpButton;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);

        presenter = new LoginPresenterImpl(this);

        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.signUpButton:
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.loginButton:
                presenter.logIn(loginEditText.getText().toString(), passwordEditText.getText().toString());
                break;
        }
    }

    @Override
    public void showMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showToastFailure() {
        Api.showFailureToast(getApplicationContext());
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
