package com.ayushchauhan.x1.api;

import com.ayushchauhan.x1.model.AddCustomerResponse;
import com.ayushchauhan.x1.model.AddTransactionResponse;
import com.ayushchauhan.x1.model.CreateUserResponse;
import com.ayushchauhan.x1.model.CustomerResponse;
import com.ayushchauhan.x1.model.GetTransactionResponse;
import com.ayushchauhan.x1.model.LogoutUserResponse;
import com.ayushchauhan.x1.model.UserLoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    //    @POST("createuser.php")
//  Call<Users> createUser(@FieldMap HashMap<String,String> user);



    @FormUrlEncoded

    @POST("createuser.php")
    Call<CreateUserResponse> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("password") String password

    );


//    @POST("createuser.php")
//    Call<Users> createUser(@Body Users users);

    @FormUrlEncoded
    @POST("userlogin.php")
    Call<UserLoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("logoutuser.php")
    Call<LogoutUserResponse> logout(
            @Field("token") String token
    );




    @FormUrlEncoded
    @POST("addcustomer.php")
    Call<AddCustomerResponse> addCustomer(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("address") String address,
            @Field("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("getcustomer.php")
    Call<CustomerResponse> getcustomer(
            @Field("user_id") int user_id
    );


    @FormUrlEncoded
    @POST("addtransaction.php")
    Call<AddTransactionResponse> addtransaction(
            @Field("user_id") int user_id,
            @Field("customer_id") int customer_id,
            @Field("title") String title,
            @Field("amount") String amount,
            @Field("type") String type,
            @Field("description") String description
    );

    @FormUrlEncoded
    @POST("gettransaction.php")
    Call<GetTransactionResponse> getAllTransactiondata(
            @Field("user_id") int user_id
    );


    @FormUrlEncoded
    @POST("updateprofile.php")
    Call<CreateUserResponse> UpdateUserDetails(
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("user_id") int user_id
            );

}