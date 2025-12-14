package com.example.courseWork5REST.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @RequestMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello World");
    }
}
