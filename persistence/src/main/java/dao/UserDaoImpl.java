package dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;
	private CriteriaBuilder criteriaBuilder;
	private CriteriaQuery<User> criteriaQuery;
	private Root<User> root;
	private Query<User> query;
	
	/**
	 * Setup Criteria query to perform operations.
	 */
	public void setupCriteria() {
		this.session = sessionFactory.getCurrentSession();
		this.criteriaBuilder = session.getCriteriaBuilder();
	}
	
	@Override
	public User findByLogin(String login) {

		setupCriteria();

		this.criteriaQuery = this.criteriaBuilder.createQuery(User.class);
		this.root = this.criteriaQuery.from(User.class);
		this.criteriaQuery.select(this.root).where(this.criteriaBuilder.equal(this.root.get("login"), login));
		this.query = session.createQuery(criteriaQuery);
		
		return query.setMaxResults(1).uniqueResult();
	}
	
	@Override
	public User save(User user) {
		
		setupCriteria();
		
		this.session.save(user);
		return user;
	}
}
