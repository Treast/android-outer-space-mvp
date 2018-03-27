package riva.vincent.outerspacemanager;

import java.util.Map;

import retrofit2.*;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import riva.vincent.outerspacemanager.Requests.AuthCreateRequest;
import riva.vincent.outerspacemanager.Responses.AuthCreate;
import riva.vincent.outerspacemanager.Responses.AuthLogin;

/**
 * Created by vriva on 26/03/2018.
 */

public interface ApiService {
    @POST("api/v100/auth/create")
    Call<AuthCreate> authCreate(@Body AuthCreateRequest authCreateRequest);

    @POST("api/v100/auth/login")
    Call<AuthLogin> authLogin(@Body String login, String password);
}
