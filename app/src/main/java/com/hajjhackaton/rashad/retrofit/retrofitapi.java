package com.hajjhackaton.rashad.retrofit;

        import io.reactivex.Single;
        import retrofit2.http.Body;
        import retrofit2.http.FormUrlEncoded;
        import retrofit2.http.POST;

public interface retrofitapi
{
    //http://rashedcallrequestapi.gear.host/
    @POST("RashedAssistant/Call/RequestCall")
    Single<responseModule> getData(@Body requist_requird_data sentData);
}
