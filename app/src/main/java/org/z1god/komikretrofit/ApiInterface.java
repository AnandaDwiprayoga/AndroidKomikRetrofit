package org.z1god.komikretrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("{id}/info.0.json")
    Call<ResponseModel> getComicById(@Path("id") int id);
}
