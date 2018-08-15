package com.perficient.blackhills.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhc.startstop.web.model.PrimaryPerson;
import com.bhc.startstop.webservice.UserWebService;
import com.perficient.blackhills.util.CustomErrorType;

@RestController
@RequestMapping("/User")
public class UserWebServiceController {

	public static final Logger logger = LoggerFactory.getLogger(UserWebServiceController.class);

	@Autowired
	UserWebService userWebService; 
	
	@RequestMapping(value = "/account/{userName}", method = RequestMethod.GET)
	public ResponseEntity<?> getPrimaryPersonDetails(@PathVariable("userName") String userName) {
		logger.info("Fetching User with id {}", userName);
		PrimaryPerson primaryPerson = userWebService.getLoggedInUserDetails(userName);
		if (primaryPerson == null) {
			logger.error("User with id {} not found.", userName);
			return new ResponseEntity(new CustomErrorType("User with id " + userName 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PrimaryPerson>(primaryPerson, HttpStatus.OK);
	}

}