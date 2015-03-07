package com.tozny.sdk;

import com.google.api.client.json.GenericJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tozny.sdk.ToznyApiException.ToznyApiError;

/**
 * The Tozny API returns an HTTP 200, even on errors. The Google HTTP client's JSON library provides
 * the GenericJson abstraction for collecting the entire JSON response into a Java Map collection.
 * The ToznyApiResponse class serves to collect the JSON response and to provide error handling helpers.
 */
public class ToznyApiResponse extends GenericJson {

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
        Object returnValue =  this.get("return");
        return returnValue == null ||
                !(returnValue instanceof String) ||
                Status.ERROR.serverResponse.equals(returnValue);
    }

    /**
     * asserts isError().
     * Tozny API returns an error as a field named 'errors' that is a JSON list of objects.
     * Each of the error objects contains a 'status_code' value, analogous to HTTP status code,
     * 'error_message' is a human readable explanation for the error, and 'location' where in the
     * API code the error originated.
     * @return a ToznyApiException exception, with the error response from the Tozny API
     */
    public ToznyApiException getException () {
        assert(isError());

        Object errors = this.get("errors");

        List<ToznyApiError> errorList = new ArrayList<ToznyApiError>();

        if (errors != null && errors instanceof List) {
            for (Map<String,Object> error : (List<Map<String,Object>>)this.get("errors")) {
                Object statusObject = error.get("status_code");
                Object locationObject = error.get("location");
                Object messageObject = error.get("error_message");
                int status = (statusObject == null || !(statusObject instanceof Number)) ? -1 : ((Number)statusObject).intValue();
                String location = (locationObject == null || !(locationObject instanceof String)) ? null : (String)locationObject;
                String message = (messageObject == null || !(messageObject instanceof String)) ? null : (String)messageObject;
                errorList.add(new ToznyApiError(status, message, location));
            }
        }

        return new ToznyApiException(errorList);
    }
}
