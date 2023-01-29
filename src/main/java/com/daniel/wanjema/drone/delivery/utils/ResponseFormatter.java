package com.daniel.wanjema.drone.delivery.utils;

import com.daniel.wanjema.drone.delivery.model.dto.ApiResponse;
import com.daniel.wanjema.drone.delivery.model.dto.ApiResponseHeader;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;

import static com.daniel.wanjema.drone.delivery.config.GlobalVariables.X_CORRELATION_CONVERSATION_ID;

public class ResponseFormatter {
	public static ApiResponse setApiResponse(Map<String, String> headers, int responseCode, String responseMessage, Object object){
		return new ApiResponse(new ApiResponseHeader(headers.get(X_CORRELATION_CONVERSATION_ID), responseCode,responseMessage, Instant.now().toString()),object);
	}

	/*public static Mono<ApiResponse> setApiResponse(Map<String, String> headers, int responseCode, String responseMessage, Object object){
		return Mono.just(setApiResponse(headers, responseCode, responseMessage, object));
	}*/
}
