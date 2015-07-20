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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ohkey.app.model.Apartment;
import com.ohkey.app.model.KeyInfo;
import com.ohkey.app.repository.ApartmentRepository;
import com.ohkey.app.repository.KeyInfoRepository;
import com.ohkey.app.util.Generator;

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

	@Autowired
	KeyInfoRepository keyInfoRepository;
	
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
		String status = a.getStatus();
		newApte.setStatus(status);
		KeyInfo keyInfo = new KeyInfo();
		if (!"not live".equals(status))
			keyInfo.setExternalKey(Generator.generateExternalKey()+1);
		
		keyInfo.setApartment(newApte);
		newApte.setKeyInfo(keyInfo);
		newApte.setTel(a.getTel());
		apteRepository.save(newApte);
		return "success";
	}
	
	@RequestMapping (method = RequestMethod.PUT, value = "{aptId}")
	public String updateStatus (@PathVariable("aptId") int aptId, @RequestParam String newStatus) {
		Apartment a = apteRepository.findOne(new Integer(aptId));
		KeyInfo k = a.getKeyInfo();
		String status = a.getStatus();
  		switch (status) {
  		case "not live":
  			  //different change for available vs unavailable
  			if ("available".equals(newStatus)){
  				a.setStatus(newStatus);
  				apteRepository.save(a);
  				k.setExternalKey(Generator.generateExternalKey()+1);
  				keyInfoRepository.save(k);
  			}else if("unavailable".equals(newStatus)){
  				a.setStatus(newStatus);
  				apteRepository.save(a);
  				k.setExternalKey(Generator.generateExternalKey()+1);
  				keyInfoRepository.save(k);
  			}
  			break;
  		case "available":
  			  //different change for available vs not live
  			if ("not live".equals(newStatus)){
  				a.setStatus(newStatus);
  				apteRepository.save(a);
  				k.setExternalKey(null);
  				keyInfoRepository.save(k);
  			}else if("unavailable".equals(newStatus)){
  				a.setStatus(newStatus);
  				apteRepository.save(a);
  				k.setExternalKey(Generator.generateExternalKey()+1);
  				keyInfoRepository.save(k);
  			}
  			break;
  		case "unavailable":
  			if ("not live".equals(newStatus)){
  				a.setStatus(newStatus);
  				apteRepository.save(a);
  				k.setExternalKey(null);
  				keyInfoRepository.save(k);
  			}else if("available".equals(newStatus)){
  				a.setStatus(newStatus);
  				apteRepository.save(a);
  			}
  			break;
  		}
		return "success";
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public String deleteApte(@PathVariable("id") int aptId) {
		Apartment a = apteRepository.findOne(new Integer(aptId));
		apteRepository.delete(a);
		return "success";
	}

}
