package com.daniel.wanjema.drone.delivery.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.daniel.wanjema.drone.delivery.config.GlobalVariables.X_CORRELATION_CONVERSATION_ID;

public class LogManager {
    private static final Logger logger = LoggerFactory.getLogger(LogManager.class);

    public static void logDebug(Map<String, String> headers, String message){
        logger.debug("TransactionID={} | Message={}", headers.get(X_CORRELATION_CONVERSATION_ID), message);
    }

    public static void logInfo(Map<String, String> headers, String message){
        logger.info("TransactionID={} | Message={}", headers.get(X_CORRELATION_CONVERSATION_ID), message);
    }

    public static void logWarn(Map<String, String> headers, String message){
        logger.warn("TransactionID={} | Message={}", headers.get(X_CORRELATION_CONVERSATION_ID), message);
    }

    public static void logError(Map<String, String> headers, String message){
        logger.error("TransactionID={} | Message={}", headers.get(X_CORRELATION_CONVERSATION_ID), message);
    }
}
