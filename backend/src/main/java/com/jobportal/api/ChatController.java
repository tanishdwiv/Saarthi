// // package com.jobportal.api;

// // import com.jobportal.service.GeminiChatService;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.*;

// // import reactor.core.publisher.Mono;
// // import java.util.HashMap;
// // import java.util.Map;


// // // backend/src/main/java/com/jobportal/api/ChatController.java
// // @RestController
// // @RequestMapping("/api/chat")
// // @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

// // public class ChatController {

// //   @Autowired
// //   private GeminiChatService geminiChatService;

// //   @PostMapping
// //   public Mono<ResponseEntity<Map>> chat(@RequestBody Map<String,Object> body) {
// //     // body = { "messages": [ { "role": "user", "content": "Hello?" } ] }
// //     return geminiChatService.chat(body)
// //       .map(resp -> ResponseEntity.ok(resp))
// //       .defaultIfEmpty(ResponseEntity.status(502).build());
// //   }
// // }

// // backend/src/main/java/com/jobportal/api/ChatController.java
// package com.jobportal.api;

// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.jobportal.service.GeminiChatService;

// @RestController
// @RequestMapping("/api/chat")
// @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
// public class ChatController {

//   private final GeminiChatService geminiChatService;

//   public ChatController(GeminiChatService geminiChatService) {
//     this.geminiChatService = geminiChatService;
//   }

//   @PostMapping
//   public ResponseEntity<?> chat(@RequestBody Map<String, Object> body) {
//     // Log incoming payload
//     System.out.println(">>> Received /api/chat payload: " + body);

//     try {
//       // Block on the reactive service to get a synchronous result
//       Map<String, Object> resp = geminiChatService.chat(body).block();

//       if (resp == null) {
//         return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
//       }

//       return ResponseEntity.ok(resp);
//     } catch (Exception ex) {
//       // Log full stack trace
//       ex.printStackTrace();

//       // Build a friendlier error response
//       Map<String, String> errorBody = new HashMap<>();
//       errorBody.put("error", "Gemini proxy failed");
//       errorBody.put("message", ex.getMessage());

//       return ResponseEntity
//         .status(HttpStatus.INTERNAL_SERVER_ERROR)
//         .body(errorBody);
//     }
//   }
// }

package com.jobportal.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.service.GeminiChatService;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ChatController {

  private final GeminiChatService geminiChatService;

  public ChatController(GeminiChatService geminiChatService) {
    this.geminiChatService = geminiChatService;
  }

  @PostMapping
  public ResponseEntity<?> chat(@RequestBody Map<String, Object> body) {
    System.out.println(">>> Received /api/chat payload: " + body);

    try {
      Map<String, Object> geminiResponse = geminiChatService.chat(body).block();

      if (geminiResponse == null || !geminiResponse.containsKey("candidates")) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Map.of("error", "No candidates returned"));
      }

      // Extract reply text from Gemini response
      List<Map<String, Object>> candidates = (List<Map<String, Object>>) geminiResponse.get("candidates");
      Map<String, Object> firstCandidate = candidates.get(0);
      Map<String, Object> content = (Map<String, Object>) firstCandidate.get("content");
      List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
      String reply = (String) parts.get(0).get("text");

      // Return only the reply field
      return ResponseEntity.ok(Map.of("reply", reply.trim()));

    } catch (Exception ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
        "error", "Gemini proxy failed",
        "message", ex.getMessage()
      ));
    }
  }
}