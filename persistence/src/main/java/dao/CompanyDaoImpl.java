package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Company;
import model.Computer;

@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;
	private CriteriaBuilder criteriaBuilder;
	private CriteriaQuery<Company> criteriaQuery;
	private Root<Company> root;
	private Query<Company> query;

	public CompanyDaoImpl() {
	}

	/**
	 * Setup Criteria query to perform operations.
	 */
	public void setupCriteria() {
		this.session = sessionFactory.getCurrentSession();
		this.criteriaBuilder = session.getCriteriaBuilder();
	}

	/**
	 * Get the specified company by name.
	 * 
	 * @param company the company object
	 * @return the company object
	 */
	@Override
	public Company getByName(Company company) {

		setupCriteria();

		this.criteriaQuery = this.criteriaBuilder.createQuery(Company.class);
		this.root = this.criteriaQuery.from(Company.class);
		this.criteriaQuery.where(this.criteriaBuilder.equal(this.root.get("name"), company.getName()));
		this.query = session.createQuery(criteriaQuery);

		return query.setMaxResults(1).uniqueResult();
	}

	/**
	 * Get the specified company by id.
	 * 
	 * @param company the company object
	 * @return the company object
	 */
	@Override
	public Company getById(Company company) {

		setupCriteria();

		this.criteriaQuery = this.criteriaBuilder.createQuery(Company.class);
		this.root = this.criteriaQuery.from(Company.class);
		this.criteriaQuery.where(this.criteriaBuilder.equal(this.root.get("id"), company.getId()));
		this.query = session.createQuery(criteriaQuery);

		return query.setMaxResults(1).uniqueResult();
	}

	/**
	 * Get all companies.
	 * 
	 * @return array of company objects
	 */
	@Override
	public List<Company> getAll() {

		setupCriteria();

		this.criteriaQuery = this.criteriaBuilder.createQuery(Company.class);
		this.root = this.criteriaQuery.from(Company.class);
		this.criteriaQuery.select(this.root);

		this.query = session.createQuery(this.criteriaQuery);
		return this.query.getResultList();
	}

	/**
	 * Delete a company object by it's id.
	 * 
	 * @param company the company object
	 */
	@Override
	public void delete(Company company) {

		setupCriteria();

		CriteriaDelete<Computer> deleteComputer = this.criteriaBuilder.createCriteriaDelete(Computer.class);
		Root<Computer> root = deleteComputer.from(Computer.class);
		deleteComputer.where(this.criteriaBuilder.equal(root.get("company"), company.getId()));

		Query<?> query1 = session.createQuery(deleteComputer);

		CriteriaDelete<Company> deleteCompany = this.criteriaBuilder.createCriteriaDelete(Company.class);
		this.root = deleteCompany.from(Company.class);
		deleteCompany.where(this.criteriaBuilder.equal(this.root.get("id"), company.getId()));

		Query<?> query2 = session.createQuery(deleteCompany);

		query1.executeUpdate();
		query2.executeUpdate();
	}
}
