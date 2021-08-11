package com.fullstack.springbootecommerce.controller;

import com.fullstack.springbootecommerce.dto.JBody;
import com.fullstack.springbootecommerce.dto.ResponseMessage;
import com.fullstack.springbootecommerce.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/purchase", method = RequestMethod.GET)
public class JavaRestController {

    private EntityService entityService;

    @Autowired
    JavaRestController(EntityService entityService) { this.entityService = entityService; }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseMessage addOrder(@RequestBody JBody body) {
        return entityService.addOrder(body);
    }
}
