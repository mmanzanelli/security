package com.ad.equipojugadorsecurity.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ad.equipojugadorsecurity.dto.EquipoDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {
    
    @PostMapping(value = "demo")
    public String welcome()
    {
        return "Welcome from secure endpoint";
    }
    
    
    
    @PostMapping(value = "demo2")
    public String welcome2()
    {
        return "Welcome from SECOND secure endpoint";
    }
}
