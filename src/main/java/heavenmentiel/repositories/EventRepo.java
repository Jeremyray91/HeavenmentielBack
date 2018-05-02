package heavenmentiel.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	
	public String createEvent(Event event) {
		em.persist(event);
		return "Ajout éffectué";
	}
	
	public JsonNode getAll() {
		List<Event> events = em.createQuery("from Event", Event.class).getResultList();
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode evenements = mapper.createArrayNode();
		for(Event event : events) {
			evenements.add(toJsonEvent(event));
		}
		return evenements;
	}
	
	public JsonNode getMultiCriteria(
									String name,
									Date datemin,
									Date datemax,
									String place,
									TypeEvent types,
									Float pricemin,
									Float pricemax) {

		CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
		CriteriaQuery createQuery = queryBuilder.createQuery();
		Root<Event> customer =	createQuery.from(Event.class);
		
		//Constructing list of parameters
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (name != null) {
			predicates.add(
					queryBuilder.equal(customer.get("name"), name));
		}if (datemin != null) {
			predicates.add(
					queryBuilder.equal(customer.get("datemin"), datemin));
		}if (datemax != null) {
			predicates.add(
					queryBuilder.equal(customer.get("datemax"), datemax));
		}if (place != null) {
			predicates.add(
					queryBuilder.equal(customer.get("place"), place));
		}if (types != null) {
			predicates.add(
					queryBuilder.equal(customer.get("types"), place));
		}if (pricemin != null) {
			predicates.add(
					queryBuilder.equal(customer.get("pricemin"), pricemin));
		}if (pricemax != null) {
			predicates.add(
					queryBuilder.equal(customer.get("pricemax"), pricemax));
		}
		//Query itself
		createQuery.select(customer)
        				.where(predicates.toArray(new Predicate[]{}));
		
		//Execute query and do something with result
		List<Event> events = em.createQuery(createQuery).getResultList();
		
		//Convert to json
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
		rootNode.put("price", event.getPrice());
		rootNode.put("seatsAvailable", event.getSeatsAvailable());
		rootNode.put("description", event.getDescription());
		rootNode.put("available", event.isAvailable());
		rootNode.put("img", event.getImg());
		return rootNode;
	}
	
	
}
