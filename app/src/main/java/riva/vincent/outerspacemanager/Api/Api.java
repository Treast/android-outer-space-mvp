package riva.vincent.outerspacemanager.Api;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by treast on 27/03/2018.
 */

public class Api {
    private static final Api ourInstance = new Api();
    private ApiService apiService;

    private String BASE_URL = "https://outer-space-manager-staging.herokuapp.com/";

    public static ApiService getInstance() {
        return ourInstance.apiService;
    }

    private Api() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static void showFailureToast(Context context) {
        Toast toast = Toast.makeText(context, "Erreur de réseau.", Toast.LENGTH_LONG);
        toast.show();
    }
}
