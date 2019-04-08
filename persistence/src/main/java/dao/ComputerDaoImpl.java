package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Computer;

@Repository
@Transactional
public class ComputerDaoImpl implements ComputerDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;
	private CriteriaBuilder criteriaBuilder;
	private CriteriaQuery<Computer> criteriaQuery;
	private Root<Computer> root;
	private Query<Computer> query;

	public ComputerDaoImpl() {
	}

	/**
	 * Setup Criteria query to perform operations.
	 */
	public void setupCriteria() {
		this.session = sessionFactory.getCurrentSession();
		this.criteriaBuilder = session.getCriteriaBuilder();
	}

	/**
	 * Get the total number of computer rows from Database.
	 * 
	 * @return the total number of computers
	 */
	public Long count() {

		setupCriteria();
		CriteriaQuery<Long> critQuery = this.criteriaBuilder.createQuery(Long.class);
		this.root = critQuery.from(Computer.class);
		critQuery.select(this.criteriaBuilder.count(this.root));

		Query<Long> query = session.createQuery(critQuery);
		return query.getSingleResult();
	}

	/**
	 * Search a computer from Database.
	 * 
	 * @param computer the computer object
	 * @return the computers list
	 */
	public List<Computer> search(Computer computer) {

		setupCriteria();
		this.criteriaQuery = this.criteriaBuilder.createQuery(Computer.class);
		this.root = this.criteriaQuery.from(Computer.class);
		this.criteriaQuery.select(this.root)
				.where(this.criteriaBuilder.like(this.root.<String>get("name"), computer.getName() + "%"));

		this.query = session.createQuery(this.criteriaQuery);
		return this.query.getResultList();
	}

	/**
	 * Delete a computer from Database.
	 * 
	 * @param computer the computer object
	 */
	@Override
	public void delete(Long id) {

		setupCriteria();
		CriteriaDelete<Computer> delete = this.criteriaBuilder.createCriteriaDelete(Computer.class);
		this.root = delete.from(Computer.class);
		delete.where(this.criteriaBuilder.equal(this.root.get("id"), id));

		Query<?> query = session.createQuery(delete);
		query.executeUpdate();
	}

	/**
	 * Update computer values in Database.
	 * 
	 * @param computer computer object
	 */
	@Override
	public void update(Computer computer) {

		setupCriteria();
		CriteriaUpdate<Computer> update = this.criteriaBuilder.createCriteriaUpdate(Computer.class);
		this.root = update.from(Computer.class);

		update.set("name", computer.getName());
		update.set("introduced", computer.getIntroduced());
		update.set("discontinued", computer.getDiscontinued());
		update.set("company", computer.getCompany());
		update.where(this.criteriaBuilder.equal(this.root.get("id"), computer.getId()));

		Query<?> query = session.createQuery(update);
		query.executeUpdate();
	}

	/**
	 * Add a new computer in Database.
	 * 
	 * @param computer the computer object
	 */
	@Override
	public void add(Computer computer) {

		setupCriteria();

		this.session.save(computer);

	}

	/**
	 * Return the specified computer
	 * 
	 * @param computer the computer object
	 * @return the computer object specified
	 */
	@Override
	public Computer get(Long id) {

		setupCriteria();

		this.criteriaQuery = this.criteriaBuilder.createQuery(Computer.class);
		this.root = this.criteriaQuery.from(Computer.class);
		this.criteriaQuery.where(this.criteriaBuilder.equal(this.root.get("id"), id));
		this.query = session.createQuery(this.criteriaQuery);

		return query.setMaxResults(1).uniqueResult();
	}

	/**
	 * Return a list of all computers.
	 * 
	 * @param page     offset of the request
	 * @param nbOfRows limit of the request
	 * @param sort     the desired sort choice
	 * 
	 * @return array of computers objects
	 */
	@Override
	public List<Computer> getAll(int page, int nbOfRows, String sort) {

		setupCriteria();

		this.criteriaQuery = this.criteriaBuilder.createQuery(Computer.class);
		this.root = this.criteriaQuery.from(Computer.class);

		switch (sort) {
		case "name":
			this.criteriaQuery.select(root).orderBy(this.criteriaBuilder.asc(this.root.get("name")));
			break;
		case "introduced":
			this.criteriaQuery.select(root).orderBy(this.criteriaBuilder.asc(this.root.get("introduced")));
			break;
		case "discontinued":
			this.criteriaQuery.select(root).orderBy(this.criteriaBuilder.asc(this.root.get("discontinued")));
			break;
		case "company":
			this.criteriaQuery.select(root).orderBy(this.criteriaBuilder.asc(this.root.get("company")));
			break;
		default:
			this.criteriaQuery.select(root).orderBy(this.criteriaBuilder.asc(this.root.get("id")));
		}

		this.query = session.createQuery(criteriaQuery).setFirstResult((page - 1) * nbOfRows).setMaxResults(nbOfRows);
		return query.getResultList();
	}
}
