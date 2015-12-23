package register.model;
// Generated 2015/12/23 �U�� 06:48:21 by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Memember.
 * @see register.model.Memember
 * @author Hibernate Tools
 */
public class MememberHome
{

    private static final Log log = LogFactory.getLog(MememberHome.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory()
    {
        try
        {
            return (SessionFactory) new InitialContext()
                    .lookup("SessionFactory");
        } catch (Exception e)
        {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException(
                    "Could not locate SessionFactory in JNDI");
        }
    }

    public void persist(Memember transientInstance)
    {
        log.debug("persisting Memember instance");
        try
        {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re)
        {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(Memember instance)
    {
        log.debug("attaching dirty Memember instance");
        try
        {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re)
        {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Memember instance)
    {
        log.debug("attaching clean Memember instance");
        try
        {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re)
        {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(Memember persistentInstance)
    {
        log.debug("deleting Memember instance");
        try
        {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re)
        {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Memember merge(Memember detachedInstance)
    {
        log.debug("merging Memember instance");
        try
        {
            Memember result = (Memember) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Memember findById(int id)
    {
        log.debug("getting Memember instance with id: " + id);
        try
        {
            Memember instance = (Memember) sessionFactory.getCurrentSession()
                    .get("register.model.Memember", id);
            if (instance == null)
            {
                log.debug("get successful, no instance found");
            } else
            {
                log.debug("get successful, instance found");
            }
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Memember> findByExample(Memember instance)
    {
        log.debug("finding Memember instance by example");
        try
        {
            List<Memember> results = (List<Memember>) sessionFactory
                    .getCurrentSession()
                    .createCriteria("register.model.Memember")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re)
        {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
}
