package com.speedhome.poc.service.service;

import com.speedhome.poc.service.entity.PostEntity;
import com.speedhome.poc.service.entity.PropertyEntity;
import com.speedhome.poc.service.search.HibernateSearchUtil;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class PostSearch {
    private static final String CONTENT = "content";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public PostSearch(EntityManager entityManager, EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManager;
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<PostEntity> searchPost(String search) {

        entityManager = entityManagerFactory.createEntityManager();

        FullTextEntityManager fullTextEntityManager = initializedFullTextEntityManager();

        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(PostEntity.class ).get();

        BooleanJunction<BooleanJunction> booleanJunction =
                HibernateSearchUtil.buildBooleanJunctionSearch(
                        qb,
                        search,
                        TITLE,
                        DESCRIPTION,
                        CONTENT
                        );

        FullTextQuery query =
                fullTextEntityManager.createFullTextQuery(booleanJunction.createQuery(), PropertyEntity.class);

        List<PostEntity> postEntities = query.getResultList();

        commitTransaction();

        return postEntities;
    }

    private FullTextEntityManager initializedFullTextEntityManager() {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        entityManager.getTransaction().begin();

        return fullTextEntityManager;
    }

    private void commitTransaction() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
