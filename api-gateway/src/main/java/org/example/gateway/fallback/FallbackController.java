package org.example.gateway.fallback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping(value = "/fallback/user", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
    public ResponseEntity<String> userServiceFallback() {
        return ResponseEntity.ok("User Service временно недоступен. Попробуйте позже.");
    }

    @RequestMapping(value = "/fallback/notification", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
    public ResponseEntity<String> notificationServiceFallback() {
        return ResponseEntity.ok("Notification Service временно недоступен. Попробуйте позже.");
    }
}
