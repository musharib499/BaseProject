package com.innobles.smartplaykids.network.retrofit;

import java.util.Map;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


import com.innobles.smartplaykids.network.model.Login;
import com.innobles.smartplaykids.network.model.MobileOtp;
import com.innobles.smartplaykids.network.model.StudentInfo;


/**
 * Created by vinodtakhar on 2/6/16.
 */
public interface RetrofitInterface {

    @GET("parent_send_otp.php")
    Observable<Login> getLogin(@QueryMap Map<String, String> params);

    @GET("parent_check_otp.php")
    Observable<MobileOtp> getMobileOtp(@QueryMap Map<String, String> params);

    @GET("get_students.php")
    Observable<StudentInfo> getStudentInfo(@QueryMap Map<String, String> params);

   /* @GET("logout.php")
    Call<BaseResponseModel> getLogOut(@QueryMap Map<String, String> params);

    @GET("confirm_call_vehical.php")
    Call<ConfirmCallVehicle> getConfirmCallMyCar(@QueryMap Map<String, String> params);

    @GET("confirm_validate_card.php")
    Call<ConfirmValidate> getConfirmValidate(@QueryMap Map<String, String> params);

    @GET("issue_card.php")
    Call<ValidCardInfo> getIssueCard(@QueryMap Map<String, String> params);

    @GET("issue_number.php")
    Call<ValidCardInfo> getIssueCardNumber(@QueryMap Map<String, String> params);

    @GET("call_vehical.php")
    Call<CallMyCar> getCallVehicle(@QueryMap Map<String, String> params);

    @GET("validate_card.php")
    Call<ValidCardInfo> getValidCard(@QueryMap Map<String, String> params);

    @GET("parking_status.php")
    Call<Parking> getParking(@QueryMap Map<String, String> params);*/

   /* @GET("startup/{dealerId}")
    Call<StartupApiModel> getStartup(@Path("dealerId") String dealerId);



    @FormUrlEncoded
    @POST("testimonial/{dealerId}")
    Call<BaseResponseModel> postTestimonial(@Path("dealerId") String dealerId,
                                            @FieldMap Map<String, String> params);
    @Multipart
    @POST("testimonial/{dealerId}")
    Call<BaseResponseModel> postTestimonial(@Path("dealerId") String dealerId,
                                            @Part("file\"; filename=\"imagefile.jpg\" ") RequestBody file,
                                            @PartMap Map<String, RequestBody> params);*/
}
