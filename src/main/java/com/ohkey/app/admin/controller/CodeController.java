package com.ohkey.app.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohkey.app.Exception.MultiCodeException;
import com.ohkey.app.model.Apartment;
import com.ohkey.app.model.Code;
import com.ohkey.app.model.KeyInfo;
import com.ohkey.app.repository.ApartmentRepository;
import com.ohkey.app.repository.CodeRepository;
import com.ohkey.app.repository.KeyInfoRepository;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/admin/code")
public class CodeController {

	private static final Logger logger = LoggerFactory
			.getLogger(CodeController.class);

	@Autowired
	CodeRepository codeRepository;

	@Autowired
	ApartmentRepository apteRepository;
	
	@Autowired
	KeyInfoRepository keyInfoRepository;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Code> displayCodes() {
		List<Code> codeList = new ArrayList<Code>();
		Iterable<Code> codes = codeRepository.findAll();
		for(Code c:codes){
			codeList.add(c);
		}
		return codeList;

	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public KeyInfo validateCode (@RequestParam String generateCode) throws MultiCodeException {
		Date today = new Date();
		List<Code> codes = codeRepository.findByGenerateCode(generateCode);
		if (codes.isEmpty())
			return null;

		if (codes.size() > 1)
			throw new MultiCodeException();
		Code c = codes.get(0);
		Date startDate = c.getStartDate();
		Date endDate = new Date(c.getEndDate().getTime());
		if ((startDate.before(today) || compareDate(startDate, today))
				&& (endDate.after(today) || compareDate(endDate, today))) {
			return c.getKeyInfo();
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addCode(@RequestParam String startDate, @RequestParam String endDate, @RequestParam int aptId) {
		Code newCode = new Code();
		Apartment apartment = apteRepository.findOne(new Integer(aptId));
		KeyInfo keyInfo = keyInfoRepository.findByApartment(apartment).get(0);
		newCode.setStartDate(DateTime.parse(startDate).toDate());
		newCode.setEndDate(DateTime.parse(endDate).toDate());
		newCode.setCurrentDate(new Date());
		newCode.setKeyInfo(keyInfo);
		newCode.setGenerateCode(generateCode (startDate, endDate, aptId, keyInfo.getId()));
		codeRepository.save(newCode);
		return "success";

	}
	
	private String generateCode(String startDate, String endDate, int aptId, int keyId) {
		StringBuilder sb = new StringBuilder();
		sb.append(startDate.charAt(0)).append(aptId).append(endDate.charAt(0)).append(keyId);
		return sb.toString();
	}
	
	private boolean compareDate (Date date1, Date date2) {
		return date1.getYear() == date2.getYear() &&date1.getMonth()== date2.getMonth() && date1.getDay()==date2.getDay();
	}
	
}
