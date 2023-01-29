package com.daniel.wanjema.drone.delivery.config;

public class GlobalVariables {

    public static final String X_CORRELATION_CONVERSATION_ID = "X-Correlation-ConversationID";
    
    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_CREATED = 201;
    public static final Integer CODE_REQUEST_ERROR = 400;
    public static final Integer CODE_NOT_FOUND_ERROR = 404;
    public static final Integer CODE_SERVER_ERROR = 500;

    public static final String MESSAGE_SUCCESS = "Operation successful";
    public static final String MESSAGE_SERVER_ERROR = "Error has occurred";

    private GlobalVariables() {
    }
}
