package com.tozny.sdk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tozny.sdk.ToznyApiException.ToznyApiError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * The Tozny API returns an HTTP 200, even on errors.
 */
public class ToznyApiResponse<T> {

    private final String ret;

    // Responses include either a `result` or `results` field.
    private final T result;
    private final T results;

    private final Integer count;
    private final Integer total;
    private final List<ToznyApiError> errors;

    @JsonCreator
    public ToznyApiResponse(
            @JsonProperty("return")  String ret,
            @JsonProperty("result")  T result,
            @JsonProperty("results") T results,
            @JsonProperty("count")   Integer count,
            @JsonProperty("total")   Integer total,
            @JsonProperty("errors")  List<ToznyApiError> errors) {
        this.ret = ret;
        this.result = result;
        this.results = results;
        this.count = count;
        this.total = total;
        this.errors = errors;
    }

    /**
     * Tozny response always contains a 'return' field, either 'ok' or 'error'
     */
    public static enum Status {
        OK("ok"),
        ERROR("error");

        private final String serverResponse;

        private Status(String serverResponse) {
            this.serverResponse = serverResponse;
        }
    }

    /**
     * Tests the 'return' value in the response.
     * @return true if the response form the Tozny API was an error
     */
    public boolean isError() {
        return ret == null || Status.ERROR.serverResponse.equals(ret);
    }

    public String getReturn() {
        return ret;
    }

    @Nullable
    public T getResult() {
        return result != null ? result : results;
    }

    @Nullable
    public Integer getCount() {
        return count;
    }

    @Nullable
    public Integer getTotal() {
        return total;
    }

    /**
     * asserts isError().
     * Tozny API returns an error as a field named 'errors' that is a JSON list of objects.
     * Each of the error objects contains a 'status_code' value, analogous to HTTP status code,
     * 'error_message' is a human readable explanation for the error, and 'location' where in the
     * API code the error originated.
     * @return a ToznyApiException exception, with the error response from the Tozny API
     */
    public ToznyApiException getException() {
        assert(isError());
        if (errors != null) {
            return new ToznyApiException(errors);
        }
        else {
            return null;
        }
    }

}
