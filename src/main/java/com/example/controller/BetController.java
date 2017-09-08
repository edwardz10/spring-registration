package com.example.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Bet;
import com.example.repository.BetRepository;

@Controller
public class BetController {

	private static final Logger LOG = LoggerFactory.getLogger(BetController.class);

	private BetRepository betRepository;

	@Autowired
	public BetController(BetRepository betRepository) {
		this.betRepository = betRepository;
	}
	
	@RequestMapping(value="/newbet", method = RequestMethod.POST)
	public String addBet(@ModelAttribute Bet bet) {
		bet.setTimestamp(new Date());
		LOG.info("Bet to store: " + bet);
		betRepository.save(bet);
		return "redirect:/products";
	}
}
