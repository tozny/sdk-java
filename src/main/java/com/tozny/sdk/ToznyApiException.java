package com.tozny.sdk;


import java.util.Collections;
import java.util.List;

/**
 * Reports exceptions that occur while interacting with the Tozny API.
 * Tozny's API will return HTTP 200, even on errors.
 * The ToznyApiException class marshals a response, if it's
 */
public class ToznyApiException extends RuntimeException {
    public final List<ToznyApiError> errors;

    /**
     * A class that modeles a singl error reported by the Tozny API.
     */
    public static class ToznyApiError {
        /**
         * Analogous to an HTTP status code.
         */
        public final int status_code;
        /**
         * A human-readable explanation of the error
         */
        public final String error_message;

        /**
         *  Where in the API's call stack the error originated from.
         */
        public final String location;

        public ToznyApiError (int status_code, String error_message, String location) {
            this.error_message = error_message;
            this.status_code = status_code;
            this.location = location;
        }

        @Override
        public String toString() {
            return "ToznyApiError{" +
                    "status_code=" + status_code +
                    ", error_message='" + error_message + '\'' +
                    ", location='" + location + '\'' +
                    '}';
        }
    }

    @SuppressWarnings("unchecked")
    public ToznyApiException(String message, Throwable cause) {
        super(message,cause);
        this.errors = Collections.EMPTY_LIST;
    }
    @SuppressWarnings("unchecked")
    public ToznyApiException(String message) {
        super(message);
        this.errors = Collections.EMPTY_LIST;
    }

    public ToznyApiException(List<ToznyApiError> errors) {
        super(showErrors(errors));
        this.errors = errors;
    }

    public ToznyApiException(List<ToznyApiError> errors, Throwable cause) {
        super(cause);
        this.errors = errors;
    }

    /**
     * Concatenates several instances of ToznyApiError into a single String.
     * System.getProperty("line.separator") is used to separate each instance.
     *
     * @param errors A List of ToznyApiError instances to
     * @return The concatenation of the given ToznyApiError instances.
     */
    private static String  showErrors(List<ToznyApiError> errors) {
        StringBuilder sb = new StringBuilder();
        for (ToznyApiError error : errors) {

            sb.append(System.getProperty("line.separator"));
            sb.append(error.toString());
        }
        return sb.toString();
    }
}
