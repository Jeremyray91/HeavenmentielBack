package heavenmentiel.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import heavenmentiel.models.Event;
import heavenmentiel.services.EventService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {
	@Autowired
	EventService evs;

	@RequestMapping(value = "/events/multicriteria", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getMultiCriteria(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "datemin", required = false) String datemin,
			@RequestParam(value = "datemax", required = false) String datemax,
			@RequestParam(value = "place", required = false) String place,
			@RequestParam(value = "types", required = false) String types,
			@RequestParam(value = "pricemin", required = false) Float pricemin,
			@RequestParam(value = "pricemax", required = false) Float pricemax) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date dateMin = null;
		Date dateMax = null;
		try {
			if (datemin != null)
				dateMin = formatter.parse(datemin);
			if (datemax != null)
				dateMax = formatter.parse(datemax);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String[] typesArray = null;
		if (types != null)
			typesArray = types.split(",");
		return evs.getMultiCriteria(name, dateMin, dateMax, place, typesArray, pricemin, pricemax);
	}

	@RequestMapping(value = "/eventsById", method = RequestMethod.GET)
	public Event getEventById(@RequestParam(value = "id") long id) {
		return evs.getEventById(id);
	}

	@RequestMapping(value = "/events", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getAll() {
		return evs.getAll();
	}

	@RequestMapping(value = "/eventsbydate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getAllByDate() {
		return evs.getAllByDate();
	}

	@RequestMapping(value = "/eventsLastFiveAdd", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getLastFiveAdd() {
		return evs.getLastFiveAdd();
	}

	@RequestMapping(value = "/events", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createEvent(@RequestBody Event event) {
		return evs.createEvent(event);
	}
	
	@CrossOrigin
	@RequestMapping(value="/events", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateEvent(@RequestBody Event event) {
		evs.updateEvent(event);
	}
	
	@CrossOrigin
	@RequestMapping(value="/events/{id}", method = RequestMethod.DELETE)
	public void updateEvent(@PathVariable long id) {
		evs.deleteEvent(id);
	}

	@RequestMapping(value = "/types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getTypes() {
		return evs.getTypes();
	}
}
