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
 * Home object for domain model class TeamUser.
 * @see register.model.TeamUser
 * @author Hibernate Tools
 */
public class TeamUserHome
{

    private static final Log log = LogFactory.getLog(TeamUserHome.class);

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

    public void persist(TeamUser transientInstance)
    {
        log.debug("persisting TeamUser instance");
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

    public void attachDirty(TeamUser instance)
    {
        log.debug("attaching dirty TeamUser instance");
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

    public void attachClean(TeamUser instance)
    {
        log.debug("attaching clean TeamUser instance");
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

    public void delete(TeamUser persistentInstance)
    {
        log.debug("deleting TeamUser instance");
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

    public TeamUser merge(TeamUser detachedInstance)
    {
        log.debug("merging TeamUser instance");
        try
        {
            TeamUser result = (TeamUser) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public TeamUser findById(register.model.TeamUserId id)
    {
        log.debug("getting TeamUser instance with id: " + id);
        try
        {
            TeamUser instance = (TeamUser) sessionFactory.getCurrentSession()
                    .get("register.model.TeamUser", id);
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

    public List<TeamUser> findByExample(TeamUser instance)
    {
        log.debug("finding TeamUser instance by example");
        try
        {
            List<TeamUser> results = (List<TeamUser>) sessionFactory
                    .getCurrentSession()
                    .createCriteria("register.model.TeamUser")
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
