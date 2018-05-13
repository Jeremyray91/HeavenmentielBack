package heavenmentiel.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import heavenmentiel.models.Commande;
import heavenmentiel.repositories.CommandeRepo;

@Service
@Transactional
@CrossOrigin("http://localhost:4200")
public class CommandeService {
	@Autowired CommandeRepo commandRepo;
	@Autowired EventService evs;
	
	public Commande create(Commande commande) {
		return commandRepo.create(commande);
	}
	
	public List<Commande> getMulticriteria(String nom, String prenom, Long idClient, Date datemin, Date datemax,Integer page){
		return commandRepo.getMulticriteria(nom, prenom, idClient, datemin, datemax,page);
	}
	
	
}
