package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.collection.HistoCollection;
import com.example.demo.collection.JoueurCollection;
import com.example.demo.collection.MaterielleCollection;
import com.example.demo.model.Admins;
import com.example.demo.model.Category;
import com.example.demo.model.Client;
import com.example.demo.model.Enchere;
import com.example.demo.model.Historique;
import com.example.demo.model.Materielle;
import com.example.demo.model.Solde_client;
import com.example.demo.model.State;
import com.example.demo.model.Token;
import com.example.demo.repository.HistoRepository;

import database.GenericDAO;

@RestController
@CrossOrigin
public class Service {

	@Autowired
    HistoRepository histoRepository;
	@Autowired
	JdbcTemplate db;
	GenericDAO dao = new GenericDAO();
	
	static int idUsers = 1;
	static int idAdmins = 1;
	
	@GetMapping("/getMongo")
	public List<HistoCollection> testMongo() {
		return histoRepository.findAll();
	}
	
	@GetMapping("/setMongo")
	public String testSetMongo() {
		try {
			JoueurCollection joueur = new JoueurCollection();
			joueur.setIdJoueur(idUsers);
			joueur.setMontant(6000);
			MaterielleCollection collection = new MaterielleCollection();
			collection.setIdMaterielle(2);
			collection.setNom("tableau");
			HistoCollection histo = new HistoCollection();
			histo.setJoueur(joueur);
			histo.setMaterielle(collection);
			histoRepository.save(histo);
			return "finish";
		} catch (Exception e) {
			return "error : "+e.toString();
		}
	}
	
	@GetMapping("/connection")
	public void connection(@RequestParam("nom") String nom , @RequestParam("mdp") String mdp) {
		if(nom.equals("mic") && mdp.equals("root")) {
			idUsers = 1;
			System.out.println(idUsers);
		}
		System.out.println(nom +" / "+ mdp);
	}
	
	@GetMapping("/login")
	public List<Admins> login(@RequestParam("nom") String nom , @RequestParam("mdp") String mdp) {
		List<Admins> admins = db.query("select * from admins where nom='"+nom+"' and mdp='"+mdp+"'",  new BeanPropertyRowMapper<Admins>(Admins.class));
		this.idAdmins=admins.get(0).getId();
		return admins;
	}
	
//	@GetMapping("/createToken")
	public String createToken(int id , String uuid) {
		LocalDate now = LocalDate.now();
		db.update("insert into token values ('"+uuid+"','"+now.plusDays(1)+"',"+id+")");
		return uuid;
	}
	
	@GetMapping("/loginClient")
	public String loginClient(@RequestParam("nom") String nom , @RequestParam("mdp") String mdp) {
		List<Admins> admins = db.query("select * from client where nom='"+nom+"' and mdp='"+mdp+"'",  new BeanPropertyRowMapper<Admins>(Admins.class));
		UUID uuid = UUID.randomUUID();
		if (admins.size()!=0) {
			return "null";
		}
		if (checkToken(admins.get(0).getId())) {
			System.out.println("idClient = "+getIdClient(uuid.toString()));
		}
		else {
			createToken(admins.get(0).getId(), uuid.toString());
		}
		return uuid.toString();
	}
	
	public int getIdClient(String uuid) {
		try {
			System.out.println("\"select * from token where value='"+uuid+"' and dateajout > now()");
			List<Token> users = db.query("select * from token where value='"+uuid+"' and dateajout > now()",  new BeanPropertyRowMapper<Token>(Token.class));
			return users.get(0).getIdclient();
		} catch (Exception e) {
			return 0;
		}
	}
	
	public boolean checkToken(String uuid) {
		try {
			List<Token> users = db.query("select * from token where value='"+uuid+"' and dateajout > now()",  new BeanPropertyRowMapper<Token>(Token.class));
			if (users.size()==0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean checkToken(int id) {
		try {
			List<Token> users = db.query("select * from token where idClient="+id+" and dateajout < now()",  new BeanPropertyRowMapper<Token>(Token.class));
			if (users.size()==0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@GetMapping("/search")
	public List<Enchere> search(@RequestParam("nom") String nom) {
		return db.query("select * from enchere e join materielle m on e.idmaterielle=m.id where m.nom='"+nom+"'",  new BeanPropertyRowMapper<Enchere>(Enchere.class));
	}
	
	@GetMapping("/advanceSearch")
	public void advanceSearch(@RequestParam("motCle") String motCle, @RequestParam("date") String date ,@RequestParam("prix") int prix , @RequestParam("categorie") int categorie, @RequestParam("status") int status) {
		String sql ="select * from enchere e join materielle m on e.idmaterielle=m.id where 1=1 ";
		if (!motCle.equals("")) {
			sql += " and m.nom = '"+motCle+"'";
		}
		if (!date.equals("")) {
			sql += " and e.datedepartenchere < '"+date+"'";
		}
		if (prix!=0) {
			sql += " and m.prixminimal < "+ prix;
		}
		if (categorie!=0) {
			sql += " and m.idcategory = "+categorie;
		}
		if (status==0 || status==1) {
			sql += " and e.isfinish = "+status;
		}
		sql += ")";
		System.out.println(sql);
//		return db.query("select * from enchere e join materielle m on e.idmaterielle=m.id where m.nom=''",  new BeanPropertyRowMapper<Enchere>(Enchere.class));
	}
	
	@GetMapping("/addClient")
	public String addClient(@RequestParam("nom") String nom , @RequestParam("mdp") String mdp) {
		try {
			db.update("insert into client values (default , '"+nom+"' , '"+mdp+"')");
			return "finish";
		} catch (Exception e) {
			return "error : " + e.toString();
		}
	}
	@GetMapping("/addAdmins")
	public String addAdmins(@RequestParam("nom") String nom , @RequestParam("mdp") String mdp) {
		try {
			db.update("insert into admins values (default , '"+nom+"' , '"+mdp+"')");
			return "finish";
		} catch (Exception e) {
			return "error : " + e.toString();
		}
	}
	
	@GetMapping("/listEnchere")
	public List<Enchere> listEnchere() {
		return db.query("select * from enchere where isfinish = 0", new BeanPropertyRowMapper<Enchere>(Enchere.class));
	}
	
	@GetMapping("/updateEnchere")
	public String updateEnchere(@RequestParam("idEnchere") int idEnchere ,@RequestParam("idProduit") int idProduit , @RequestParam("qte") int qte , @RequestParam("duree") int duree , @RequestParam("status") int status , @RequestParam("commision") int commision) {
		try {
			db.update("update enchere set idmaterielle="+idProduit+", dureeenchere="+duree+" , isfinish="+status+" , qte="+qte+" , commision="+commision+"  where id ="+idEnchere);
			return "finish";
		} catch (Exception e) {
			return "problem : "+ e.getMessage();
		}
	}
	
	@GetMapping("/getCategory")
	public List<Category> getCategory() {
		return db.query("select * from category", new BeanPropertyRowMapper<Category>(Category.class) );
	}
	
	@GetMapping("/addCategory")
	public String addCategory(@RequestParam("types") String types) {
		try {
			db.update("insert into category values (default,'"+types+"')");
			return "finish";
		} catch (Exception e) {
			return "errot : "+e.toString();
		}
	}
	
	@GetMapping("/getProduit")
	public List<Materielle> getProduit() {
		return db.query("select * from materielle",new BeanPropertyRowMapper<Materielle>(Materielle.class));
	}
	
	@GetMapping("/addProduit")
	public String addProduit (@RequestParam("nom") String nom,@RequestParam("prixminimal")int prixminimal ,@RequestParam("datedebut") String datedebut,@RequestParam("duree")int duree,@RequestParam("idcategory")int idcategory,@RequestParam("idclient")int idclient){
		try {
			db.update("insert into materielle values ('"+nom+"',"+prixminimal+",'"+datedebut+"',"+duree+","+idcategory+",default,"+idclient+")");
			return "finish";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}
	}
	
	@GetMapping("/getEnchere")
	public List<Enchere> getEnchere() {
		return db.query("select * from enchere", new BeanPropertyRowMapper<Enchere>(Enchere.class));
	}
	
	@GetMapping("/addEnchere")
	public String addEnchere (@RequestParam("desc")String desc,@RequestParam("datedepart")String datedepart,@RequestParam("duree")int duree,@RequestParam("idmaterielle")int idmaterielle,@RequestParam("qte")int qte){
		try {
			String sql = "insert into enchere values (default ,'"+desc+"','"+datedepart+"',"+duree+","+idmaterielle+",0,"+qte+",default)";
			db.update(sql);
			return "finish";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}
	}
	
	@GetMapping("/finishEnchere")
	public String finishEnchere (@RequestParam("id") int id) {
		try {
			db.update("update enchere set isfinish = 1 where id="+id);
			return "finish";
		} catch (Exception e) {
			return "error : "+e.toString();
		}
	}
	
	@GetMapping("/encherire")
	public String encherire (@RequestParam("uuid") String uuid,@RequestParam("idenchere") int idenchere ,@RequestParam("montant") int montant) {
		try {
			int idUser = getIdClient(uuid);
			System.out.println(idUser);
			boolean check =checkEnchere(idUser, idenchere);
			if (check) {
				db.update("insert into histo_enchere values ("+idenchere+","+idUser+","+montant+",default)");
				return "finish";
			}
			else {
				return "c'est votre enchere";
			}
		} catch (Exception e) {
			return "error : "+e.toString();
		}
	}
	
	public boolean checkEnchere(int idClient,int idenchere) {
		try {
			List<Enchere> encheres = db.query("select * from enchere e join materielle m on e.idmaterielle=m.id where m.idClient!="+idClient,  new BeanPropertyRowMapper<Enchere>(Enchere.class));
			for (Enchere enchere : encheres) {
				if (enchere.getId()==idenchere) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@GetMapping("/histoEnchere")
	public List<Enchere> histoEnchere () {
		return db.query("select * from enchere e join histo_enchere h on e.id=h.idenchere", new BeanPropertyRowMapper<Enchere>(Enchere.class));
	}
	
	@GetMapping("/histoEnchereDetaille")
	public List<Enchere> histoEnchere (@RequestParam("idEnchere") int idEnchere) {
		return db.query("select * from enchere e join histo_enchere h on e.id=h.idenchere where e.id="+idEnchere, new BeanPropertyRowMapper<Enchere>(Enchere.class));
	}
	
	@GetMapping("/getHistorique")
	public List<Historique> getHistorique(@RequestParam("idEnchere") int idEnchere) {
		return db.query("select * from historique where idenchere = "+idEnchere, new BeanPropertyRowMapper<Historique>(Historique.class));
	}
	
	@GetMapping("/historique")
	public List<Historique> getHistorique() {
		return db.query("select * from historique", new BeanPropertyRowMapper<Historique>(Historique.class));
	}
	
	@GetMapping("/state")
	public List<State> getState() {
		return db.query("select  max(nbr) , min(nbr) , avg(nbr) , sqrt(VAR_SAMP(nbr)) from count_enchere", new BeanPropertyRowMapper<State>(State.class));
	}
	
	@GetMapping("/getDemande")
	public List<Solde_client> getDemande() {
		return db.query("select * from solde_client where isvalidate = 0", new BeanPropertyRowMapper<Solde_client>(Solde_client.class));
	}
	
	@GetMapping("/addDemande")
	public String addDemande(@RequestParam("uuid") String uuid,@RequestParam("montant") float montant) {
		try {
			int idUser = getIdClient(uuid);
			db.update("insert into solde_client values ("+montant+",0,"+idUser+",default)");
			return "finish";
		} catch (Exception e) {
			return "error : "+e.toString();
		}
	}
	
	@GetMapping("/validateDemande")
	public String validateDemande(@RequestParam("id") int id) {
		try {
			db.update("update solde_client set isvalidate=1 where id="+id);
			return "finish";
		} catch (Exception e) {
			return "error : "+e.toString();
		}
	}
//	@GetMapping("/json-to-object")
//    public Couleur[] jsonToObject() throws IOException {
//		URL url = new URL("http://localhost:8080/Departement");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        int status = con.getResponseCode();
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer content = new StringBuffer();
//        while ((inputLine = in.readLine()) != null) {
//            content.append(inputLine);
//        }
//        in.close();
//        con.disconnect();
//        System.out.println(content.toString());
//        String json = content.toString();
//        Gson gson = new Gson();
//        Couleur[] facture = gson.fromJson(json, Couleur[].class);
//        return facture;
//    }
//
//    @GetMapping("/object-to-json")
//    public String objectToJson() {
//        Couleur person = new Couleur();
//        Gson gson = new Gson();
//        String json = gson.toJson(person);
//        return json;
//    }
    
}
