package com.nxl.gateway_server.controller.api.remote;

import com.nxl.gateway_server.model.PersonModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoteController {

    @GetMapping("/hello")
    public PersonModel hello(@RequestParam("name") String name, @RequestParam("age") String age) {

        PersonModel personModel = new PersonModel();
        personModel.setName(name);
        personModel.setAge(age);

        return personModel;
    }

}
