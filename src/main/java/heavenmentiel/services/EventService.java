package heavenmentiel.services;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import heavenmentiel.models.Event;
import heavenmentiel.repositories.EventRepo;

@Service
@Transactional
@CrossOrigin("http://localhost:4200")
public class EventService {
	@Autowired EventRepo evr;
	@PersistenceContext	EntityManager em;
	@Autowired protected Environment env;
	
	public Event getEventById(long id)
	{
		return evr.getById(id);
	}
	
	public JsonNode getAll() {
		return evr.getAll();
	}
	
	public JsonNode getAllByDate() {
		return evr.getAllByDate();
	}
	
	public JsonNode getLastFiveAdd() {
		return evr.getLastFiveAdd();
	}
	
	public List<Event> getMultiCriteria(String name, Date datemin, Date datemax, String place, String[] types, Float pricemin, Float pricemax, Integer page) {
		return evr.getMultiCriteria(name, datemin, datemax, place, types, pricemin, pricemax, page);
	}
	
	public long getMulticriteriaCount(String name, Date datemin, Date datemax, String place, String[] types, Float pricemin, Float pricemax) {
		return evr.getMultiCriteriaCount(name, datemin, datemax, place, types, pricemin, pricemax);
	}
	
	public Long getMulticriteriaNbPages(Long count) {
		Integer pagination = Integer.parseInt(env.getRequiredProperty("EventPagination"));
		Long nbPages = count/pagination;
		if(count%pagination==0)
			return  nbPages;
		else
			return nbPages+1;
	}
	
	public void createEvent(Event event) {
		 evr.createEvent(event);
	}
	
	public void updateEvent(Event event) {
		evr.update(event);
	}
	
	public void deleteEvent(long id) {
		evr.delete(id);
	}
	
	public JsonNode getTypes() {
		return evr.getTypes();
	}
	public void addImageMin(MultipartFile file) {
		evr.addImageMin(file);
	}
	public void addImage(MultipartFile file) {
		evr.addImage(file);
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
