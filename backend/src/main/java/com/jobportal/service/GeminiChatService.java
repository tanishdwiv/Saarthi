// // package com.jobportal.service;

// // import org.springframework.beans.factory.annotation.Value;
// // import org.springframework.stereotype.Service;
// // import org.springframework.web.reactive.function.client.WebClient;
// // import reactor.core.publisher.Mono;
// // import java.util.Map;

// // @Service
// // public class GeminiChatService {

// //   private final WebClient webClient;

// //   public GeminiChatService(@Value("${gemini.api.url}") String apiUrl,
// //                            @Value("${gemini.api.key}") String apiKey) {
// //     this.webClient = WebClient.builder()
// //       .baseUrl(apiUrl)
// //       .defaultHeader("Authorization", "Bearer " + apiKey)
// //       .build();
// //   }

// //   public Mono<Map> chat(Map<String, Object> payload) {
// //     return webClient.post()
// //       .bodyValue(payload)
// //       .retrieve()
// //       .bodyToMono(Map.class);
// //   }
// // }

// // backend/src/main/java/com/jobportal/service/GeminiChatService.java
// // backend/src/main/java/com/jobportal/service/GeminiChatService.java
// package com.jobportal.service;

// import java.util.Map;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.core.ParameterizedTypeReference;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.stereotype.Service;
// import org.springframework.web.reactive.function.client.ClientResponse;
// import org.springframework.web.reactive.function.client.WebClient;

// import reactor.core.publisher.Mono;

// @Service
// public class GeminiChatService {

//   private static final Logger log = LoggerFactory.getLogger(GeminiChatService.class);

//   private final WebClient webClient;

//   public GeminiChatService(WebClient.Builder webClientBuilder,
//                            @Value("${gemini.api.url}") String apiUrl,
//                            @Value("${gemini.api.key}") String apiKey) {
//     this.webClient = webClientBuilder
//       .baseUrl(apiUrl)
//       .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
//       .build();
//   }

//   public Mono<Map<String, Object>> chat(Map<String, Object> payload) {
//     log.debug(">>> Forwarding to Gemini API: {}", payload);

//     return webClient.post()
//       .contentType(MediaType.APPLICATION_JSON)
//       .bodyValue(payload)
//       .retrieve()
//       // turn any 4xx/5xx into an exception Mono
//       .onStatus(
//         status -> status.is4xxClientError() || status.is5xxServerError(),
//         ClientResponse::createException
//       )
//       // deserialize JSON into Map<String,Object>
//       .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
//       .doOnNext(resp -> log.debug("<<< Gemini API responded: {}", resp))
//       .doOnError(err -> log.error("!!! Error calling Gemini API", err));
//   }
// }

// backend/src/main/java/com/jobportal/service/GeminiChatService.java
package com.jobportal.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class GeminiChatService {

  private static final Logger log = LoggerFactory.getLogger(GeminiChatService.class);
  private final WebClient webClient;
  private final String apiKey;

  public GeminiChatService(WebClient.Builder builder,
                           @Value("${gemini.api.url}") String apiUrl,
                           @Value("${gemini.api.key}") String apiKey) {
    this.apiKey = apiKey;
    this.webClient = builder
      .baseUrl(apiUrl)
      .build();
  }

  public Mono<Map<String, Object>> chat(Map<String, Object> payload) {
    log.debug(">>> Forwarding to Gemini API: {}", payload);

    return webClient.post()
      .uri(uriBuilder ->
        uriBuilder
          .path("")            // baseUrl already ends in your path
          .queryParam("key", apiKey)
          .build()
      )
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(payload)
      .retrieve()
      .onStatus(
        status -> status.is4xxClientError() || status.is5xxServerError(),
        ClientResponse::createException
      )
      .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
      .doOnNext(resp -> log.debug("<<< Gemini API responded: {}", resp))
      .doOnError(err -> log.error("!!! Error calling Gemini API", err));
  }
}