package heavenmentiel.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import heavenmentiel.enums.TypeEvent;
import heavenmentiel.models.Event;

@Repository
@Transactional
public class EventRepo {
	@PersistenceContext	EntityManager em;
	
	public JsonNode getAll() {
		List<Event> events = em.createQuery("from Event", Event.class).getResultList();
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode evenements = mapper.createArrayNode();
		for(Event event : events) {
			evenements.add(toJsonEvent(event));
		}
		return evenements;
	}
	
	public JsonNode getMultiCriteria(String name, Date datemin, Date datemax, String place, TypeEvent types, Float pricemin, Float pricemax) {
		TypedQuery<Event> query = em.createQuery("from Event event where name like :name and (date > :datemin and date < :datemax) and place like :place and type in (:types) and price >= :pricemin and oruce <= :pricemax", Event.class);
		query.setParameter("name", name);
		query.setParameter("datemin", datemin);
		query.setParameter("datemax", datemax);
		query.setParameter("place", place);
		query.setParameter("types", types);
		query.setParameter("pricemin", pricemin);
		query.setParameter("pricemax", pricemax);
		List<Event> events = query.getResultList();
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode evenements = mapper.createArrayNode();
		for(Event event : events) {
			evenements.add(toJsonEvent(event));
		}
		return evenements;
	}
	
	public JsonNode toJsonEvent(Event event) {
		ObjectMapper mapper =  new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		rootNode.put("id", event.getId());
		rootNode.put("name", event.getName());
		rootNode.put("place", event.getPlace());
		rootNode.put("type", event.getType().toString());
		rootNode.put("date", event.getDateEvent().toString());
		rootNode.put("price", event.getSeatsAvaliable());
		rootNode.put("theme", event.getTheme().toString());
		rootNode.put("status", event.getStatus().toString());
		return rootNode;
	}
	
	
}
