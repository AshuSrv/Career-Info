package com.example.ashutoshshrivastava.restapi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LoginInterface {

    @POST("user/login")
    Call<LoginClass> login(@Body LoginClass loginClass);

    @POST("user/signup")
    Call<LoginClass> signup(@Body LoginClass task);

    @POST("user/personaldetail/{id}")
    Call<FillDetailsclass> filldetails(@Path("id") int id,  @Body FillDetailsclass task);

    @GET("user/personaldetail/{id}")
    Call<FillDetailsclass> getdetails(@Path("id") int id);

    @GET("user/educationdetail/{id}")
    Call<educationclass> geteducationdetails(@Path("id") int id);

    @GET("/user/professionaldetail/{id}")
    Call<ProfessionalDetailClass> getprofessiondetails(@Path("id") int id);

    @POST("user/educationdetail/{id}")
    Call<educationclass> filledu(@Path("id") int id,  @Body educationclass task);

    @PUT("user/educationdetail/{id}")
    Call<educationclass> Updatefilledu(@Path("id") int id,  @Body educationclass task);

    @POST("user/professionaldetail/{id}")
    Call<ProfessionalDetailClass> fillprofdetail(@Path("id") int id,  @Body ProfessionalDetailClass task);

    @PUT("user/professionaldetail/{id}")
    Call<ProfessionalDetailClass> Updatefillprofdetail(@Path("id") int id,  @Body ProfessionalDetailClass task);


}
