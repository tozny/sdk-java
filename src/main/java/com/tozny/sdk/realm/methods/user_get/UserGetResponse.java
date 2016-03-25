package com.tozny.sdk.realm.methods.user_get;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiException.ToznyApiError;
import com.tozny.sdk.ToznyApiResponse;
import com.tozny.sdk.realm.User;

import java.util.List;

public class UserGetResponse extends ToznyApiResponse<User> {

    @JsonCreator
    public UserGetResponse(
            @JsonProperty("return")  String ret,
            @JsonProperty("results") User results,
            @JsonProperty("count")   Integer count,
            @JsonProperty("total")   Integer total,
            @JsonProperty("errors")  List<ToznyApiError> errors) {
        super(ret, null, results, count, total, errors);
    }

}
