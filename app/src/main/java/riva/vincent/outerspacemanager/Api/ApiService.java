package riva.vincent.outerspacemanager.Api;

import retrofit2.*;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import riva.vincent.outerspacemanager.Api.Requests.AuthCreateRequest;
import riva.vincent.outerspacemanager.Api.Requests.AuthLoginRequest;
import riva.vincent.outerspacemanager.Api.Requests.DeviceAddRequest;
import riva.vincent.outerspacemanager.Api.Requests.ShipAttackRequest;
import riva.vincent.outerspacemanager.Api.Requests.ShipCreateRequest;
import riva.vincent.outerspacemanager.Api.Responses.AuthCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.AuthLoginResponse;
import riva.vincent.outerspacemanager.Api.Responses.BuildingCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.BuildingListResponse;
import riva.vincent.outerspacemanager.Api.Responses.FleetListResponse;
import riva.vincent.outerspacemanager.Api.Responses.MyShipsResponse;
import riva.vincent.outerspacemanager.Api.Responses.SearchCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.SearchListResponse;
import riva.vincent.outerspacemanager.Api.Responses.ShipAttackResponse;
import riva.vincent.outerspacemanager.Api.Responses.ShipCreateResponse;
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

    @GET("api/v1/ships")
    Call<FleetListResponse> getFleetList(@Header("x-access-token") String token);

    @GET("api/v1/fleet/list")
    Call<MyShipsResponse> getShipList(@Header("x-access-token") String token);

    @GET("api/v1/searches/list")
    Call<SearchListResponse> getSearchList(@Header("x-access-token") String token);

    @POST("api/v1/searches/create/{searchId}")
    Call<SearchCreateResponse> searchCreate(@Header("x-access-token") String token, @Path("searchId") Integer searchId);

    @POST("api/v1/buildings/create/{buildingId}")
    Call<BuildingCreateResponse> buildingCreate(@Header("x-access-token") String token, @Path("buildingId") Integer buildingId);

    @POST("api/v1/ships/create/{shipId}")
    Call<ShipCreateResponse> shipCreate(@Header("x-access-token") String token, @Path("shipId") Integer shipId, @Body ShipCreateRequest shipCreateRequest);


    @POST("api/v1/fleet/attack/{userName}")
    Call<ShipAttackResponse> shipAttack(@Header("x-access-token") String token, @Path("userName") String userName, @Body ShipAttackRequest shipAttackRequest);

    @POST("api/v1/devices/add")
    Call<SearchCreateResponse> deviceAdd(@Header("x-access-token") String token, @Body DeviceAddRequest deviceAddRequest);
}
