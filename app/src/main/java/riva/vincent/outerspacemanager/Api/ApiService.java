package riva.vincent.outerspacemanager.Api;

import retrofit2.*;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import riva.vincent.outerspacemanager.Api.Requests.AuthCreateRequest;
import riva.vincent.outerspacemanager.Api.Requests.AuthLoginRequest;
import riva.vincent.outerspacemanager.Api.Responses.AuthCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.AuthLoginResponse;
import riva.vincent.outerspacemanager.Api.Responses.BuildingCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.BuildingListResponse;
import riva.vincent.outerspacemanager.Api.Responses.UsersGetResponse;

/**
 * Created by vriva on 26/03/2018.
 */

public interface ApiService {
    @POST("api/v1/auth/create")
    Call<AuthCreateResponse> authCreate(@Body AuthCreateRequest authCreateRequest);

    @POST("api/v1/auth/login")
    Call<AuthLoginResponse> authLogin(@Body AuthLoginRequest authLoginRequest);

    @GET("api/v1/users/get")
    Call<UsersGetResponse> getCurrentUser(@Header("x-access-token") String token);

    @GET("api/v1/buildings/list")
    Call<BuildingListResponse> getBuildingList(@Header("x-access-token") String token);

    @POST("api/v1/buildings/create/{buildingId}")
    Call<BuildingCreateResponse> buildingCreate(@Header("x-access-token") String token, @Path("buildingId") Integer buildingId);
}
