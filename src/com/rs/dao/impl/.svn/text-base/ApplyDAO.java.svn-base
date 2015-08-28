package com.rs.dao.impl;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rs.dao.IApplyDAO;
import com.rs.model.Apply;

/**
 * A data access object (DAO) providing persistence and search support for Apply
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rs.model.Apply
 * @author MyEclipse Persistence Tools
 */

public class ApplyDAO extends HibernateDaoSupport implements IApplyDAO{
	private static final Log log = LogFactory.getLog(ApplyDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Apply transientInstance) {
		log.debug("saving Apply instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Apply persistentInstance) {
		log.debug("deleting Apply instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Apply findById(java.lang.Integer id) {
		log.debug("getting Apply instance with id: " + id);
		try {
			Apply instance = (Apply) getHibernateTemplate().get(
					"com.rs.model.Apply", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Apply instance) {
		log.debug("finding Apply instance by example");
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
		log.debug("finding Apply instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Apply as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Apply instances");
		try {
			String queryString = "from Apply";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Apply merge(Apply detachedInstance) {
		log.debug("merging Apply instance");
		try {
			Apply result = (Apply) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Apply instance) {
		log.debug("attaching dirty Apply instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Apply instance) {
		log.debug("attaching clean Apply instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		}catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ApplyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ApplyDAO) ctx.getBean("ApplyDAO");
	}
}