package com.ohkey.app.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ohkey.app.model.Apartment;
import com.ohkey.app.model.Bar;
import com.ohkey.app.repository.BarRepository;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/admin/bar")
public class BarController {

	private static final Logger logger = LoggerFactory
			.getLogger(BarController.class);

	@Autowired
	BarRepository barRepository;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Bar> displayBars() {
		List<Bar> barList = new ArrayList<Bar>();
		Iterable<Bar> bars = barRepository.findAll();
		for(Bar b:bars){
			barList.add(b);
		}
		return barList;

	}

	@RequestMapping(method = RequestMethod.POST)
	public String addBar(@RequestBody Bar b) {
		Bar newBar = new Bar();
		newBar.setBarName(b.getBarName());
		newBar.setAddress(b.getAddress());
		newBar.setComment(b.getComment());
		newBar.setStatus(b.getStatus());
		newBar.setTel(b.getTel());
		barRepository.save(newBar);
		return "success";
	}
	
/*	@RequestMapping(method = RequestMethod.DELETE,value="{id}")
	public String deleteQuestion(@PathVariable("id") int questionId) {
		Question q = apteRepository.findOne(new Integer(questionId));
		q.setStatus(99);
		apteRepository.save(q);
		return "success";
	}*/

}
