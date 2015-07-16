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

import com.ohkey.app.model.Code;
import com.ohkey.app.repository.CodeRepository;
import com.ohkey.app.util.CodeGenerator;

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

	@RequestMapping(method = RequestMethod.POST)
	public String addCode(@RequestBody Code c) {
		Code newCode = new Code();
		newCode.setCurrentDate(c.getCurrentDate());
		newCode.setEndDate(c.getEndDate());
		newCode.setGenerateCode(CodeGenerator.generateCode());
		newCode.setKeyInfo(c.getKeyInfo());
		newCode.setStartDate(c.getStartDate());
		codeRepository.save(newCode);
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
