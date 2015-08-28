package com.rs.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rs.dao.IPositionDAO;
import com.rs.model.Position;

/**
 * A data access object (DAO) providing persistence and search support for
 * Position entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rs.model.Position
 * @author MyEclipse Persistence Tools
 */

public class PositionDAO extends HibernateDaoSupport implements IPositionDAO{
	private static final Log log = LogFactory.getLog(PositionDAO.class);
	// property constants
	public static final String PNAME = "pname";
	public static final String COUNT = "count";
	public static final String REQUIREMENT = "requirement";

	protected void initDao() {
		// do nothing
	}

	public boolean save(Position transientInstance) {
		boolean flag=false;
		log.debug("saving Position instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
			flag=true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return flag;
	}

	public void delete(Position persistentInstance) {
		log.debug("deleting Position instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Position findById(java.lang.Integer id) {
		log.debug("getting Position instance with id: " + id);
		try {
			Position instance = (Position) getHibernateTemplate().get(
					"com.rs.model.Position", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Position instance) {
		log.debug("finding Position instance by example");
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
		log.debug("finding Position instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Position as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

//	public List findByPname(Object pname) {
//		return findByProperty(PNAME, pname);
//	}
	
	public List findByPname(Object pname) {//模糊匹配职位名称
		try {
			String queryString = "from Position as model where model.pname like '%"+pname+"%'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by pname failed", re);
			throw re;
		}
	}
	
	public List findByDateline(Date dateline){
		java.sql.Date date2=new java.sql.Date(dateline.getTime());
		try {
			String queryString = "from Position as model where model.deadline <= '" + date2+"'";//时间字符串加上引号
			
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by date failed", re);
			throw re;
		}
	}
	
	public List findByDId(Integer DId){
		try {
			String queryString = "from Position as model where model.department.did = "+DId;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by department failed", re);
			throw re;
		}
	}
	
	public List findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List findByRequirement(Object requirement) {
		return findByProperty(REQUIREMENT, requirement);
	}

	public List findAll() {
		log.debug("finding all Position instances");
		try {
			String queryString = "from Position";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Position merge(Position detachedInstance) {
		log.debug("merging Position instance");
		try {
			Position result = (Position) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public boolean attachDirty(Position instance) {
		boolean flag=false;
		log.debug("attaching dirty Position instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
			flag=true;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
		return flag;
	}

	public void attachClean(Position instance) {
		log.debug("attaching clean Position instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PositionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PositionDAO) ctx.getBean("PositionDAO");
	}
}