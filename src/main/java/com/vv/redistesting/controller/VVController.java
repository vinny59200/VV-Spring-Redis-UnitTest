package com.vv.redistesting.controller;

import com.vv.redistesting.service.VVService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/vv" )
public class VVController {

    private final VVService theService;

    @PostMapping
    public String postStr() {
        return theService.save( "123ABC" );
    }
}
