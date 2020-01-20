package com.example.lenovo.emptypro.ApiCallClasses.RetrofitClasses;

import android.provider.ContactsContract;

import com.example.lenovo.emptypro.ModelClasses.AllApiResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface GetDataService {

     @GET("category/")
    Call<AllApiResponse.CategoryResponse> allCateListApi();

    @GET("city/")
    Call<AllApiResponse.CityResponse> allCityListApi();

    @FormUrlEncoded
    @POST("all-pets/")
    Call<AllApiResponse.AllTypePetsRes> allTypePetsListApi(@Field("cityName") String cityName,@Field("userID") String userID);

    @FormUrlEncoded
    @POST("sold/")
    Call<AllApiResponse.SoldPetsRes> getSoldPetsApi(@Field("userID") String userID);

    @FormUrlEncoded
    @POST("uploaded/")
    Call<AllApiResponse.UploadedPetsRes> getUploadedPetsApi(@Field("userID") String userID);

    @FormUrlEncoded
    @POST("favourite/")
    Call<AllApiResponse.FavouritePetsRes> getFavouritePetsApi(@Field("userID") String userID);

    @FormUrlEncoded
    @POST("filter-data/")
    Call<AllApiResponse.FilterBasePetsRes> getFilterBaseApi(@Field("userID") String userID,@Field("cityName") String cityName,@Field("catID") String catID,@Field("subCat") String subCat);

    @FormUrlEncoded
    @POST("single-pet/")
    Call<AllApiResponse.PetDetailRes> getPetDetailsApi(@Field("userID") String userID,@Field("petID") String petID);


    @FormUrlEncoded
    @POST("generateOTP/")
     Call<AllApiResponse.OtpResponse> getOtpApi(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("verifyOTP/")
    Call<AllApiResponse.VerifyOtpRes> verifyOtpApi(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("favourite-set/")
    Call<AllApiResponse.CommonRes> addInFav(@Field("action") String action, @Field("userID") String userId,@Field("petID") String petId);   //action=sold,add,remove


    @FormUrlEncoded
    @POST("feedback/")
    Call<AllApiResponse.CommonRes> feedbackApi(@Field("userID") String userid, @Query("message") String message);


    @FormUrlEncoded
    @POST("profile-update/")
    Call<AllApiResponse.CommonRes> profileUpdateApi(@Query("firstName") String firstName, @Query("lastName") String lastName, @Query("address") String address,
                                                    @Query("village") String village, @Query("city") String city ,@Query("state") String state,
                                                    @Query("Email") String Email ,@Query("phone") String phone ,@Field("userID") String userid );



    @Multipart
    @POST("json.php")
    Call<AllApiResponse.CommonRes> addNewAdvertisementApi(@Query("action") String Add_Ad, @Query("User_Id") String User_Id,
                                                          @Query("Ad_title") String Ad_title,
                                                          @Query("Ad_Description") String Ad_Description, @Query("show_mobile_on_add") String show_mobile_on_add,
                                                          @Query("cityId") String cityId, @Query("cityName") String cityName,
                                                          @Query("areaId") String areaId, @Query("areaName") String areaName,
                                                          @Query("CatId") String CatId, @Query("SubCatId") String SubCatId,
                                                          @Query("field1") String field1, @Query("field_second1") String field_second1,
                                                          @Query("field2") String field2, @Query("field_second2") String field_second2,
                                                          @Query("field3") String field3, @Query("field_second3") String field_second3,
                                                          @Query("field4") String field4, @Query("field_second4") String field_second4,
                                                          @Query("field5") String field5, @Query("field_second5") String field_second5,
                                                          @Query("field6") String field6, @Query("field_second6") String field_second6,
                                                          @Query("field7") String field7, @Query("field_second7") String field_second7,
                                                          @Query("field8") String field8, @Query("field_second8") String field_second8,
                                                          @Query("field9") String field9, @Query("field_second9") String field_second9,
                                                          @Query("field10") String field10, @Query("field_second10") String field_second10,
                                                          @Part MultipartBody.Part image1, @Part MultipartBody.Part image2,
                                                          @Part MultipartBody.Part image3, @Part MultipartBody.Part image4);




}
