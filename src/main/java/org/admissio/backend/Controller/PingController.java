package org.admissio.backend.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class PingController {
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
