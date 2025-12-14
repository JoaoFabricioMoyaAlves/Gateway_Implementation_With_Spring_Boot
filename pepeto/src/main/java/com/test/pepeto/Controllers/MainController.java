
package com.test.pepeto.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {
    
    @GetMapping("")
    public String hello(){
        return "Resposta vindo do servico REAL (8001)";
    }
    @GetMapping("/hh")
    public String hello2(){
        return "Resposta vindo  (8001)";
    }


}
