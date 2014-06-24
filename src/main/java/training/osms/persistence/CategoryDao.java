package training.osms.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import training.osms.business.Category;

public class CategoryDao {

	public void saveCategory(Category category) {

		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;

		EntityManager manager = factory.createEntityManager();

		EntityTransaction transaction = manager.getTransaction();

		transaction.begin();

		manager.persist(category);

		transaction.commit();

	}

	public Object searchCategory(String name) {

		return null;
	}

}
