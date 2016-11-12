package com.cloudconnection.utility;

/**
 * Created by Godwin Joseph on 18-01-2016.
 */
public class ErrorHandler {
    public static final int TIMEOUT_EXCEPTION = 100;
    public static final int EMPTY_RESPONSE = 101;
    public static final int INVALID_APPREGISTRATION_REQUEST_PARAMS = 102;
    public static final int INVALID_CONTEXT = 103;
    public static final int INVALID_APP_ID = 104;
    public static final int INVALID_RESPONSE = 105;
    public static final int INVALID_DEVICE_ID = 106;
    public static final int INVALID_LOGIN_INFO = 107;
    public static final int INVALID_SIGNUP_INFO = 108;
    public static final int INVALID_FORGOT_PASSWORD_INFO = 109;
    public static final int INVALID_CHANGE_PASSWORD_INFO = 110;
    public static final int INVALID_RESPONSE_JSON_EXCEPTION = 105;
    public static final int INVALID_ORGANISATION_ID = 111;
    public static final int INVALID_GROUP_VALIDATION = 112;
    public static final int INVALID_TOKEN = 113;

    public static final int INVALID_ADD_REQUEST_REQUEST_PARAMS = 200;
    public static final int INVALID_DELETE_REQUEST_PARAMS = 201;
    public static final int INVALID_DIRECT_OPERAION_REQUEST_PARAMS = 202;
    public static final int INVALID_REMOTE_OPERATION_STATUS_REQUEST_PARAMS = 203;
    public static final int INVALID_GET_ALL_DEVICE_REQUEST_PARAMS = 204;
    public static final int INVALID_GET_DEVICE_DETAIL_REQUEST_PARAMS = 205;
    public static final int INVALID_EDIT_DEVICE_REQUEST_PARAMS = 206;
    public static final int INVALID_SENSOR_LINKING_REQUEST_PARAMS = 207;
    public static final int INVALID_USER_ID = 208;
    public static final int INVALID_NETWORK_KEY = 209;
    public static final int INVALID_NETWORK_ID = 210;
    public static final int INVALID_PHONE_SHORT_ID = 211;
    public static final int INVALID_PHONE_LONG_ID = 212;

    public static final int INVALID_ADD_GROUP_REQUEST_PARAMS = 300;
    public static final int INVALID_EDIT_GROUP_REQUEST_PARAMS = 301;
    public static final int INVALID_DELETE_GROUP_REQUEST_PARAMS = 302;
    public static final int INVALID_GET_GROUP_REQUEST_PARAMS = 303;

    public static final int BAD_REQUEST = 400;

    public static final int API_RUNNING = 401;
    public static final int API_FINISHED = 402;
    public static final  int RESPONSE_CODE_ERROR =501;

    public static class ErrorMessage {
        public static final String BAD_REQUEST = "Bad Request... header data may be wrong....";

        public static final String TIMEOUT_EXCEPTION = "Request Timed Out";
        public static final String EMPTY_RESPONSE = "Server response empty";
        public static final String INVALID_APPREGISTRATION_REQUEST_PARAMS = "Invalid app registration request.";
        public static final String INVALID_CONTEXT = "Invalid Context";
        public static final String INVALID_APP_ID = "Invalid App Id";
        public static final String INVALID_RESPONSE = "Invalid Response";
        public static final String INVALID_DEVICE_ID = "Invalid device Id";
        public static final String INVALID_LOGIN_INFO = "Invalid Login Request";
        public static final String INVALID_SIGNUP_INFO = "Invalid Signup Request";
        public static final String INVALID_FORGOT_PASSWORD_INFO = "Invalid forgot password request";
        public static final String INVALID_CHANGE_PASSWORD_INFO = "Invalid change password request";

        public static final String INVALID_RESPONSE_JSON_EXCEPTION = "Json exception in server response";
        public static final String INVALID_USER_ID = "Invalid user id";
        public static final String INVALID_NETWORK_KEY = "Invalid network key";
        public static final String INVALID_NETWORK_ID = "Invalid network id";
        public static final String INVALID_PHONE_SHORT_ID = "Invalid phone short id";
        public static final String INVALID_PHONE_LONG_ID = "Invalid phone long id";


        public static final String INVALID_ADD_REQUEST_REQUEST_PARAMS = "Invalid add device request";
        public static final String INVALID_DELETE_REQUEST_PARAMS = "Invalid device delete request";
        public static final String INVALID_DIRECT_OPERAION_REQUEST_PARAMS = "Invalid direct operation request";
        public static final String INVALID_REMOTE_OPERATION_STATUS_REQUEST_PARAMS = "Invalid remote operation status request";
        public static final String INVALID_GET_ALL_DEVICE_REQUEST_PARAMS = "Invalid get all device request";
        public static final String INVALID_GET_DEVICE_DETAIL_REQUEST_PARAMS = "Invalid get device details request";
        public static final String INVALID_EDIT_DEVICE_REQUEST_PARAMS = "Invalid edit device request";
        public static final String INVALID_SENSOR_LINKING_REQUEST_PARAMS = "Invalid sensor link/ delink request ";
        public static final String INVALID_ORGANISATION_ID = "Invalid organisation id.";
        public static final String INVALID_GROUP_VALIDATION = "Invalid Group validation error.";
        public static final String INVALID_TOKEN = "Invalid token";
        public static final String INVALID_GET_GROUPS_REQUEST_PARAMS = "Invalid get groups request parameters";

        public static final String INVALID_ADD_GROUP_REQUEST_PARAMS = "INVALID_ADD_GROUP_REQUEST_PARAMS";
        public static final String INVALID_EDIT_GROUP_REQUEST_PARAMS = "INVALID_EDIT_GROUP_REQUEST_PARAMS";
        public static final String INVALID_DELETE_GROUP_REQUEST_PARAMS = "INVALID_DELETE_GROUP_REQUEST_PARAMS";
        public static final String INVALID_GET_GROUP_REQUEST_PARAMS = "INVALID_GET_GROUP_REQUEST_PARAMS";


    }

}
