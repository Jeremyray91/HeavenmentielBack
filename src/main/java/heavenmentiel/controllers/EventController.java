package heavenmentiel.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import heavenmentiel.enums.TypeEvent;
import heavenmentiel.services.EventService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="*")
public class EventController {
	@Autowired EventService evs;

	@RequestMapping(value ="/event/multicriteria", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public JsonNode getMultiCriteria(
										@RequestParam("name") String name,
										@RequestParam("datemin") Date datemin,
										@RequestParam("datemax") Date datemax,
										@RequestParam("place") String place,
										@RequestParam("types") TypeEvent types,
										@RequestParam("pricemin") Float pricemin,
										@RequestParam("pricemax") Float pricemax)
		{
		return evs.getMultiCriteria(name, datemin, datemax, place, types, pricemin, pricemax);
	}
	
	@RequestMapping(value ="/event", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getAll() {
		return evs.getAll();
	}
}
