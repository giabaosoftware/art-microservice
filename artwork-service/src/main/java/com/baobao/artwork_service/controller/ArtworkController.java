package com.baobao.artwork_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/artworks")
public class ArtworkController {

    @GetMapping
    public String gretting(){
        return "Hello world";
    }
}
