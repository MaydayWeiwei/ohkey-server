package com.ohkey.app.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ohkey.app.model.Apartment;
import com.ohkey.app.repository.ApartmentRepository;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/admin/apartment")
public class ApartmentController {

	private static final Logger logger = LoggerFactory
			.getLogger(ApartmentController.class);

	@Autowired
	ApartmentRepository apteRepository;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Apartment> displayAptes() {
		List<Apartment> apteList = new ArrayList<Apartment>();
		Iterable<Apartment> aptes = apteRepository.findAll();
		for(Apartment a:aptes){
			apteList.add(a);
		}
		return apteList;

	}

	@RequestMapping(method = RequestMethod.POST)
	public String addApte(@RequestBody Apartment a) {
		Apartment newApte = new Apartment();
		newApte.setClientName(a.getClientName());
		newApte.setAddress(a.getAddress());
		newApte.setCapacity(a.getCapacity());
		newApte.setComment1(a.getComment1());
		newApte.setComment2(a.getComment2());
		newApte.setEnterCode(a.getEnterCode());
		newApte.setFloor(a.getFloor());
		newApte.setDoor(a.getDoor());
		newApte.setPrice(a.getPrice());
		newApte.setStatus(a.getStatus());
		newApte.setTel(a.getTel());
		apteRepository.save(newApte);
		return "success";
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value="{id}")
	public String deleteApte(@PathVariable("id") int aptId) {
		Apartment a = apteRepository.findOne(new Integer(aptId));
		apteRepository.delete(a);
		return "success";
	}

}
