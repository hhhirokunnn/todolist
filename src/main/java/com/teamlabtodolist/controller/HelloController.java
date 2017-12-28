package com.teamlabtodolist.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teamlabtodolist.entity.Sample;
import com.teamlabtodolist.model.SampleModel;

@Controller
@EnableAutoConfiguration
public class HelloController {

	@Autowired
    private SampleModel sampleModel;
	
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return sampleModel.findAll().get(0).getName();
    }
}