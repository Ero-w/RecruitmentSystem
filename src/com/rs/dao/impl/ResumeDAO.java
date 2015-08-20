package com.rs.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rs.model.Resume;

/**
 * A data access object (DAO) providing persistence and search support for
 * Resume entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rs.model.Resume
 * @author MyEclipse Persistence Tools
 */

public class ResumeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ResumeDAO.class);
	// property constants
	public static final String EDUCATION = "education";
	public static final String EXPERIENCE = "experience";
	public static final String SKILL = "skill";

	protected void initDao() {
		// do nothing
	}

	public void save(Resume transientInstance) {
		log.debug("saving Resume instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Resume persistentInstance) {
		log.debug("deleting Resume instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Resume findById(java.lang.Integer id) {
		log.debug("getting Resume instance with id: " + id);
		try {
			Resume instance = (Resume) getHibernateTemplate().get(
					"com.rs.model.Resume", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Resume instance) {
		log.debug("finding Resume instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Resume instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Resume as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEducation(Object education) {
		return findByProperty(EDUCATION, education);
	}

	public List findByExperience(Object experience) {
		return findByProperty(EXPERIENCE, experience);
	}

	public List findBySkill(Object skill) {
		return findByProperty(SKILL, skill);
	}

	public List findAll() {
		log.debug("finding all Resume instances");
		try {
			String queryString = "from Resume";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Resume merge(Resume detachedInstance) {
		log.debug("merging Resume instance");
		try {
			Resume result = (Resume) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Resume instance) {
		log.debug("attaching dirty Resume instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Resume instance) {
		log.debug("attaching clean Resume instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ResumeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ResumeDAO) ctx.getBean("ResumeDAO");
	}
}