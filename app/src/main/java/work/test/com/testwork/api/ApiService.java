package work.test.com.testwork.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import work.test.com.testwork.entity.MyResponse;

public interface ApiService {




    @GET("/v2/venues/explore")
    Call<MyResponse> getVenues(@Query("client_id") String client_id,
                               @Query("client_secret") String client_secret,
                               @Query("v") String v,
                               @Query("ll") String ll,
                               @Query("limit") String limit);


}