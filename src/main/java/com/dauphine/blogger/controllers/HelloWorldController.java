package com.dauphine.blogger.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
        name = "Hello World API",
        description = "My first Hello World endpoint"
)
public class HelloWorldController {

    @GetMapping("hello-world")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("hello")
    public ResponseEntity<String> helloByName(@RequestParam String name) {
        return ResponseEntity.ok("Hello " + name);
    }

    @GetMapping("hello/{name}")
    @Operation(
            summary = "Hello by name endpoint",
            description = "Returns 'Hello {name}' by path variable "
    )
    public ResponseEntity<String> hello(@Parameter(description = "Name to greet") @PathVariable String name) {
        return ResponseEntity.ok("Hello " + name);
    }
}
