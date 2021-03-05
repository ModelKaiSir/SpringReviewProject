package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseHibernateDao<T> {

    @Autowired
    private HibernateTemplate template;

    public void add(T obj){

        template.save(obj);
    }

    public void update(T obj){

        template.update(obj);
    }

    public HibernateTemplate getTemplate() {
        return template;
    }
}
