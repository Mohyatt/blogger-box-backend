package com.dauphine.blogger.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Tag(
        name="Hello World API",
        description = "My first Hello World endpoint"
)
public class HelloWorldController{

    @GetMapping("hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("hello")
    public String helloByName(@RequestParam String name){
        return "Hello "+name;
    }

    @GetMapping("hello/{name}")
    @Operation(
            summary = "Hello by name endpoint",
            description = "Returns 'Hello {name}' by path variable "
    )
    public String hello(@Parameter (description = "Name to greet") @PathVariable String name){
        return "Hello "+name;
    }

}
