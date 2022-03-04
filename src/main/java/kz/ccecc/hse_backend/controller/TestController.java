package kz.ccecc.hse_backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/test")
public class TestController {

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public String test() {
        return "Ok";
    }
}
