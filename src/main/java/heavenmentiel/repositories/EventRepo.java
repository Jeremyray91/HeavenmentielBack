package heavenmentiel.repositories;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import heavenmentiel.enums.TypeEvent;
import heavenmentiel.models.Event;
import heavenmentiel.services.EventService;

@Repository
@Transactional
@CrossOrigin(origins = "http://localhost:4200")
public class EventRepo {
	@PersistenceContext	EntityManager em;
	@Autowired	EventService evs;
	@Autowired protected Environment env;
	
	public void createEvent(Event event) {
		em.persist(event);
	}
	
	public void update(Event event) {
		em.merge(event);
	}
	
	public void updateById(Event event) {
		Event e = em.find(Event.class, event.getId());
		e.setAvailable(event.getAvailable());
		em.merge(e);
	}
	
	public void delete(long id) {
		Event event = em.find(Event.class, id);
		em.remove(event);
	}
	
	public JsonNode getAll() {
		List<Event> events = em.createQuery("from Event", Event.class).getResultList();
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode evenements = mapper.createArrayNode();
		for (Event event : events) {
			evenements.add(evs.toJsonEvent(event));
		}
		return evenements;
	}
	
	public JsonNode getAllByDate() {
		List<Event> events = em.createQuery("from Event order by dateEvent", Event.class).getResultList();
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode evenements = mapper.createArrayNode();
		for (Event event : events) {
			evenements.add(evs.toJsonEvent(event));
		}
		return evenements;
	}
	
	public JsonNode getLastFiveAdd() {
		List<Event> events = em.createQuery("from Event order by dateEvent", Event.class).setMaxResults(5).getResultList();
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode evenements = mapper.createArrayNode();
		for (Event event : events) {
			evenements.add(evs.toJsonEvent(event));
		}
		return evenements;
	}
	
	public Event getById(long id)
	{
		TypedQuery<Event> eventQuery = em.createQuery("SELECT evt FROM Event evt WHERE id = :id", Event.class);
		eventQuery.setParameter("id", id);
		Event resultEvent = eventQuery.getSingleResult();
		return resultEvent;
	}

	public List<Event> getMultiCriteria(String name, Date datemin, Date datemax, String place, String[] types,Float pricemin, Float pricemax, Integer page, String role) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Event> q = cb.createQuery(Event.class);
		Root<Event> c = q.from(Event.class);
		TypedQuery<Event> query = em.createQuery(q);
		
		q = q.select(c).where(getMulticriteriaPredicate(name, datemin, datemax, place, types, pricemin, pricemax, role));
		query = em.createQuery(q);
		for(Object [] param : getMultiCriteriaParameters(name, datemin, datemax, place, types, pricemin, pricemax, role)) {
			query.setParameter((String) param[0],param[1]);
		}
		
		List<Event> events = new ArrayList<>();
		Integer pagination = Integer.parseInt(env.getRequiredProperty("EventPagination"));
		if(page!=null && page!=0)
			events = query.setFirstResult(page).setMaxResults(pagination).getResultList();
		else
			events = query.setFirstResult(0).setMaxResults(pagination).getResultList();
		return events;
	}
	
	public long getMultiCriteriaCount(String name, Date datemin, Date datemax, String place, String[] types,Float pricemin, Float pricemax, String role) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Event.class))).where(getMulticriteriaPredicate(name, datemin, datemax, place, types, pricemin, pricemax, role));
		TypedQuery<Long> q = em.createQuery(cq);
		
		for(Object[] param : getMultiCriteriaParameters(name, datemin, datemax, place, types, pricemin, pricemax, role)) {
			q.setParameter((String) param[0],param[1]);
		}
		return q.getSingleResult();
	}
	
	
	public Predicate getMulticriteriaPredicate(String name, Date datemin, Date datemax, String place, String[] types,Float pricemin, Float pricemax, String role) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Event> q = cb.createQuery(Event.class);
		Root<Event> c = q.from(Event.class);
		TypedQuery<Event> query = em.createQuery(q);
		
		List<Predicate> criteres = new ArrayList<Predicate>();
		Predicate criteresPredicate;
		
		if(name!=null) {
			criteres.add(cb.like(c.get("name"), cb.parameter(String.class,"name")));
		}
		if(datemin!=null) {
			criteres.add(cb.greaterThanOrEqualTo(c.get("dateEvent"), cb.parameter(Date.class,"dateMin")));
		}
		if(datemax!=null) {
			criteres.add(cb.lessThanOrEqualTo(c.get("dateEvent"), cb.parameter(Date.class,"dateMax")));
		}
		if(place!=null) {
			criteres.add(cb.like(c.get("place"), cb.parameter(String.class,"place")));
		}
		if(pricemin!=null) {
			criteres.add(cb.greaterThanOrEqualTo(c.get("price"), cb.parameter(Float.class,"priceMin")));
		}
		if(pricemax!=null) {
			criteres.add(cb.lessThanOrEqualTo(c.get("price"), cb.parameter(Float.class,"priceMax")));
		}
		if(types!=null) {
			ArrayList<TypeEvent> typesEventEnum = new ArrayList<TypeEvent>();
			for(String t : types) {
				typesEventEnum.add(TypeEvent.valueOf(t));
			}
			criteres.add(c.get("type").in(cb.parameter(List.class,"types")));
		}
		criteresPredicate = cb.and(criteres.toArray(new Predicate[criteres.size()]));
		if(!role.equals("ADMIN")) {
			criteresPredicate = cb.and(criteresPredicate,cb.equal(c.get("available"), cb.parameter(Boolean.class,"available")));
		}
		return criteresPredicate;
	}
	
	public List<Object[]> getMultiCriteriaParameters(String name, Date datemin, Date datemax, String place, String[] types,Float pricemin, Float pricemax, String role) {
		
		List<Object[]> parametres = new ArrayList<Object[]>();
		if(name!=null) {
			parametres.add(new Object[] {"name","%"+name+"%"});
		}
		if(datemin!=null) {
			parametres.add(new Object[] {"dateMin",datemin});
		}
		if(datemax!=null) {
			parametres.add(new Object[] {"dateMax",datemax});
		}
		if(place!=null) {
			parametres.add(new Object[] {"place","%"+place+"%"});
		}
		if(pricemin!=null) {
			parametres.add(new Object[] {"priceMin",pricemin});
		}
		if(pricemax!=null) {
			parametres.add(new Object[] {"priceMax",pricemax});
		}
		if(types!=null) {
			ArrayList<TypeEvent> typesEventEnum = new ArrayList<TypeEvent>();
			for(String t : types) {
				typesEventEnum.add(TypeEvent.valueOf(t));
			}
			parametres.add(new Object[] {"types",typesEventEnum});
		}
		if(!role.equals("ADMIN")) {
			parametres.add(new Object[] {"available",true});
		}
		return parametres;
	}
	
	public void addImageMin(MultipartFile file) {
		final Logger logger = LoggerFactory
				.getLogger(EventRepo.class);
		
		String message = "";

			try {
				byte[] bytes = file.getBytes();
				
				// Creating the directory to store file
				//String homePath = System.getProperty("user.home");
				String projectPath = env.getProperty("frontProjectPath")+"src" + File.separator + "assets";
				File dir = new File(projectPath + File.separator +"img_miniature");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file= " + file.getOriginalFilename();
			} catch (Exception e) {
				System.out.println("You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage());
			}
			System.out.println(message);	
	}
	public void addImage(MultipartFile file) {
		final Logger logger = LoggerFactory
				.getLogger(EventRepo.class);
		
		String message = "";
			try {
				byte[] bytes = file.getBytes();
				
				// Creating the directory to store file
				String projectPath = env.getProperty("frontProjectPath")+"src" + File.separator + "assets";
				File dir = new File(projectPath + File.separator +"img_carousel");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file= " + file.getOriginalFilename();
			} catch (Exception e) {
				System.out.println("You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage());
			}
			System.out.println(message);	
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


}
