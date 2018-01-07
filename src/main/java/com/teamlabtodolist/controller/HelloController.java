package com.teamlabtodolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamlabtodolist.model.SampleModel;

@Controller
public class HelloController {

	@Autowired
    private SampleModel sampleModel;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    String index(Model model) {
		model.addAttribute("data",sampleModel.findAll().get(0).getTitle());
        return "index";
    }
}