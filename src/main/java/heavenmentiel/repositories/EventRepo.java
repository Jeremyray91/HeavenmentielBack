package heavenmentiel.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import heavenmentiel.enums.TypeEvent;
import heavenmentiel.models.Event;

@Repository
@Transactional
@CrossOrigin(origins = "http://localhost:4200")
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
		for (Event event : events) {
			evenements.add(toJsonEvent(event));
		}
		return evenements;
	}
	
	public JsonNode getAllByDate() {
		List<Event> events = em.createQuery("from Event order by dateEvent", Event.class).getResultList();
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode evenements = mapper.createArrayNode();
		for (Event event : events) {
			evenements.add(toJsonEvent(event));
		}
		return evenements;
	}
	
	public JsonNode getLastFiveAdd() {
		List<Event> events = em.createQuery("from Event order by dateEvent", Event.class).setMaxResults(5).getResultList();
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode evenements = mapper.createArrayNode();
		for (Event event : events) {
			evenements.add(toJsonEvent(event));
		}
		return evenements;
	}

	public JsonNode getMultiCriteria(String name, Date datemin, Date datemax, String place, TypeEvent types,Float pricemin, Float pricemax) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Event> q = cb.createQuery(Event.class);
		Root<Event> c = q.from(Event.class);
		TypedQuery<Event> query = em.createQuery(q);

		ParameterExpression<String> paramName = cb.parameter(String.class);
		ParameterExpression<Date> paramDateMin = cb.parameter(Date.class);
		ParameterExpression<Date> paramDateMax = cb.parameter(Date.class);
		ParameterExpression<String> paramPlace = cb.parameter(String.class);
		ParameterExpression<TypeEvent> paramType = cb.parameter(TypeEvent.class);
		ParameterExpression<Float> paramPriceMin = cb.parameter(Float.class);
		ParameterExpression<Float> paramPriceMax = cb.parameter(Float.class);
		
		List<Predicate> criteres = new ArrayList<Predicate>();
		Predicate criteresPredicate;
		List<Object[]> parametres = new ArrayList<Object[]>();
		
		if(name!=null) {
			criteres.add(cb.like(c.get("name"), paramName));
			parametres.add(new Object[] {paramName,"%"+name+"%"});
		}
		if(datemin!=null) {
			criteres.add(cb.greaterThanOrEqualTo(c.get("dateEvent"), paramDateMin));
			parametres.add(new Object[] {paramDateMin,datemin});
		}
		if(datemax!=null) {
			criteres.add(cb.lessThanOrEqualTo(c.get("dateEvent"), paramDateMax));
			parametres.add(new Object[] {paramDateMax,datemax});
		}
		if(place!=null) {
			criteres.add(cb.like(c.get("place"), paramPlace));
			parametres.add(new Object[] {paramPlace,"%"+place+"%"});
		}
		if(types!=null) {
			criteres.add(cb.equal(c.get("type"), paramType));
			parametres.add(new Object[] {paramType,types});
		}
		if(pricemin!=null) {
			criteres.add(cb.greaterThanOrEqualTo(c.get("price"), paramPriceMin));
			parametres.add(new Object[] {paramPriceMin,pricemin});
		}
		if(pricemax!=null) {
			criteres.add(cb.lessThanOrEqualTo(c.get("price"), paramPriceMax));
			parametres.add(new Object[] {paramPriceMax,pricemax});
		}
		
		criteresPredicate = cb.and(criteres.toArray(new Predicate[criteres.size()]));
		
		q = q.select(c).where(criteresPredicate);
		query = em.createQuery(q);
		for(Object [] params : parametres) {
			query.setParameter((ParameterExpression<Object>)params[0],  params[1]);
		}
		List<Event> events = query.getResultList();
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode evenements = mapper.createArrayNode();
		for (Event event : events) {
			evenements.add(toJsonEvent(event));
		}
		return evenements;
	}
	
	
	public JsonNode getTypes() {
		/*
		 * TO DO : Sécurity : PREPARED QUERY
		 */
		List<String> types = em.createNativeQuery("select distinct type from event").getResultList();
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode typesArray = mapper.createArrayNode();
		for (String type : types) {
			typesArray.add(type);
		}
		return typesArray;
	}

	public JsonNode toJsonEvent(Event event) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		rootNode.put("id", event.getId());
		rootNode.put("name", event.getName());
		rootNode.put("place", event.getPlace());
		rootNode.put("type", event.getType().toString());
		rootNode.put("date", event.getDateEvent().toString());
		rootNode.put("price", event.getPrice());
		rootNode.put("stock", event.getStock());
		rootNode.put("description", event.getDescription());
		rootNode.put("shortDescription", event.getShortDescription());
		rootNode.put("available", event.isAvailable());
		rootNode.put("img", event.getImg());
		rootNode.put("imgMin", event.getImgMin());
		return rootNode;
	}

}
