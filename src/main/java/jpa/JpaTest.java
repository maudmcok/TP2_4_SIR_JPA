package jpa;

import domain.ElectronicDevice;
import domain.Heater;
import domain.Home;
import domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaTest {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlperso");
	static EntityManager manager = factory.createEntityManager();
	static EntityTransaction tx = manager.getTransaction();


	//criteria querry
	CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		tx.begin();
		try {


			createPerson("Maud") ;

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		
		manager.close();
		factory.close();
	}

	public void getPersons(){
		List<Person> resultList = manager.createQuery("Select * From Person", Person.class).getResultList();
		System.out.println("List of Person:" + resultList.size());
		for (Person next : resultList) {
			System.out.println("Peron : " + next.getName() + " - Apt: "+ next.getId());
		}

	}

	public void getPerson(Long id ){
		List<Person> resultList = manager.createQuery("Select * From Person WHERE id="+id, Person.class).getResultList();
		System.out.println("List of Person:" + resultList.size());
		for (Person next : resultList) {
			System.out.println("Peron : " + next.getName() + " - Apt: "+ next.getId() + " - Nb Home ="+next.getHomes().size());
		}
	}

	public void getHomes(){
		List<Home> resultList = manager.createQuery("Select * From Home", Home.class).getResultList();
		System.out.println("List of Person:" + resultList.size());
		for (Home next : resultList) {
			System.out.println("Peron : " + next.getName() + " - Apt: "+ next.getId());
		}
	}

	public void addElectroToPerson(Long id,ElectronicDevice electronicDevice){
		Person p  = manager.createQuery("Select * From Person WHERE id="+ id , Person.class).getSingleResult();
		p.addElectro(electronicDevice);

/*		CriteriaQuery<Person> query =   criteriaBuilder.createQuery(Person.class);
		Root<Person> plink =  query.from(Person.class);
		query.select(plink).where(criteriaBuilder.equal(plink.get("id"),"id")) ;
		plink.
		Root<B.class> from = query.from(Bean.class);
		query.select(from.get("a"))
				.where(from.get("a").in(1, 2, 3, 4));*/


	}

	public void addHomeToPerson(Long id,Home home){
		Person p  = manager.createQuery("Select * From Person WHERE id="+ id , Person.class).getSingleResult();
		p.addHome(home);
	}

	public static void createPerson(String name){
		Person p =  new Person();
		p.setName(name);
		manager.persist(p);
	}


	public void createHome(String name){
		Home h =  new Home();
		h.setName(name);
		manager.persist(h);
	}

	public void createElectro(String name){
		ElectronicDevice e =  new ElectronicDevice();
		e.setName(name);
		manager.persist(e);
	}

	public void createHeater(String name){
		Heater h =  new Heater();
		h.setName(name);
		manager.persist(h);
	}
}
