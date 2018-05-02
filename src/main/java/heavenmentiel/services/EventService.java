package heavenmentiel.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import heavenmentiel.enums.TypeEvent;
import heavenmentiel.models.Event;
import heavenmentiel.repositories.EventRepo;

@Service
@Transactional
public class EventService {
	@Autowired EventRepo evr;
	
	public JsonNode getAll() {
		return evr.getAll();
	}
	
	public JsonNode getAllByDate() {
		return evr.getAllByDate();
	}
	
	public JsonNode getMultiCriteria(String name, Date datemin, Date datemax, String place, TypeEvent types, Float pricemin, Float pricemax) {
		return evr.getMultiCriteria(place, datemax, datemax, place, types, pricemax, pricemax);
	}
	public String createEvent(Event event) {
		return evr.createEvent(event);
	}
}
