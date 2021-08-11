package com.fullstack.springbootecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import com.fullstack.springbootecommerce.entity.Countries;
import com.fullstack.springbootecommerce.entity.Product;
import com.fullstack.springbootecommerce.entity.ProductCategory;

import com.fullstack.springbootecommerce.entity.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    EntityManager entityManager;

    @Autowired
    MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        theUnSupportedAction(config);

        exposeId(config);

    }
    // disable the HttpMethod for Entity Classes for single item and collection.
    private void theUnSupportedAction(RepositoryRestConfiguration config) {

        Class[] entityClass = {States.class, Countries.class, ProductCategory.class, Product.class};
        HttpMethod[] theUnSupportedAction = { HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT };

        for (Class entity : entityClass) {
            disableHttpMethods(config, entity, theUnSupportedAction);
        }

    }

    private void disableHttpMethods(RepositoryRestConfiguration config, Class Entity, HttpMethod[] theUnSupportedAction) {
        config.getExposureConfiguration().forDomainType(Entity)
                .withItemExposure((metaDeta, httpMethod) -> httpMethod.disable(theUnSupportedAction))
                .withCollectionExposure((metaDeta, httpMethod) -> httpMethod.disable(theUnSupportedAction));
    }


    private void exposeId(RepositoryRestConfiguration config) {

        // get a list of all entity classes from entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        List<Class> getEntities = new ArrayList<>();

        // Map the Entity Types into Java Class 
        for (EntityType entity : entities) {
            getEntities.add(entity.getJavaType());
        }

        // expose the entity id for domain type
        config.exposeIdsFor(getEntities.toArray(new Class[0]));
    }

}
