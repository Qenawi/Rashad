package com.hajjhackaton.rashad;


import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.reflect.TypeToken;
import com.hajjhackaton.rashad.model.CallModel;
import com.hajjhackaton.rashad.retrofit.requist_requird_data;
import com.hajjhackaton.rashad.retrofit.responseModule;
import com.hajjhackaton.rashad.retrofit.retrofitapi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private String TAG = "testy";
    CallModel callModel;
Disposable disposable;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        AndroidNetworking.initialize(Objects.requireNonNull(getActivity()));
        ButterKnife.bind(MainFragment.this, rootView);

        return rootView;
    }


    private Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    @OnClick(R.id.btn)
    protected void onClick()
    {
/*
        String REQUEST_URL = "http://rashedcallrequestapi.gear.host/RashedAssistant/Call/RequestCall";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("calleeId", "123");  //1- police , 2- nurse,  etc....
            jsonObject.put("userId", "15555");  // Hajj QR code
            jsonObject.put("message", "Some one is having a terrible stomach ache"); //msg to say
            jsonObject.put("image", ""); //image
            jsonObject.put("latitude", "1451.222"); //location lat
            jsonObject.put("longitude", "221.6333"); //location long
            jsonObject.put("time", getCurrentDate());
            Log.d(TAG, jsonObject.toString() + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(REQUEST_URL)
                .addApplicationJsonBody(jsonObject)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, "status: " + response.toString() );
                    }

                    @Override
                    public void onError(ANError anError) {

                        // handle error
                        Toast.makeText(getActivity(), "" + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "id : " + anError.getErrorDetail() + anError.getErrorBody() + anError.getMessage());
                    }
                });
                */
Solv();
    }
    void Solv()
    {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit=
               new  Retrofit.Builder().baseUrl("http://rashedcallrequestapi.gear.host/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        retrofitapi retrofitapi=retrofit.create(retrofitapi.class);
        requist_requird_data data=new requist_requird_data();
        data.setCalleeId("123");
        data.setUserId("15555");
        data.setMessage("Qenawi XYZ");
        data.setLatitude("151.121");
        data.setLongitude("155.177");
        data.setTime("21-7-2018");

/*
        "calleeId":"123",
            "userId":"15555",
            "message":"Some one is having a terrible stomach ache",
            "image":"",
            "latitude":"1451.222",
            "longitude":"221.6333",
            "time":"Wed Aug 01 23:42:22 GMT+03:00 2018"
            */
        disposable=(retrofitapi.getData(data).toObservable()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::sucess, t->{t.printStackTrace();});


    }

    private void sucess(responseModule s)
    {
        Log.v(TAG,s.getStatus().getMessage()+" "+s.getData().getUserId());

    }
}