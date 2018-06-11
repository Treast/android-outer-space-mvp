package riva.vincent.outerspacemanager;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Requests.DeviceAddRequest;
import riva.vincent.outerspacemanager.Api.Responses.DeviceAddResponse;
import riva.vincent.outerspacemanager.Api.Responses.SearchCreateResponse;

/**
 * Created by vriva on 18/04/2018.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        final String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("OuterSpace", "Refreshed token: " + refreshedToken);

        SharedPreferences settings = getSharedPreferences("OuterSpaceManager", 0);
        String token = settings.getString("token", "");

        Call<DeviceAddResponse> deviceAdd = Api.getInstance().deviceAdd(token, new DeviceAddRequest(refreshedToken));

        deviceAdd.enqueue(new Callback<DeviceAddResponse>() {
            @Override
            public void onResponse(Call<DeviceAddResponse> call, Response<DeviceAddResponse> response) {
                Log.d("OuterSpace", "currentDeviceToken: " + refreshedToken);
            }

            @Override
            public void onFailure(Call<DeviceAddResponse> call, Throwable t) {
                Log.d("OuterSpace", "onFailure: " + t);
            }
        });
    }
}
