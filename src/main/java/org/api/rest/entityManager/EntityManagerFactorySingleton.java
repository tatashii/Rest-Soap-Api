//package org.api.rest.entityManager;
//
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//public class EntityManagerFactorySingleton {
//
//    private static final String PERSISTENCE_UNIT_NAME = "rest";
//    private static EntityManagerFactory entityManagerFactory;
//
//    private EntityManagerFactorySingleton() {} // Private constructor to prevent instantiation
//
//    public static EntityManagerFactory getEntityManagerFactory() {
//        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
//            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//        }
//        return entityManagerFactory;
//    }
//}
