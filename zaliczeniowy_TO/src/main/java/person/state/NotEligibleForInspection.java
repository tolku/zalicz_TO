package person.state;

import entity.PersonEntity;
import entity.PoliceRegisterEntity;

import javax.persistence.EntityManager;

public class NotEligibleForInspection implements PersonState{
    @Override
    public void getPhysicalCertificate(EntityManager entityManager, int peselNumber) {
        System.out.println("NOT ALLOWED");
    }

    @Override
    public void getPsychicalCertificate(EntityManager entityManager, int peselNumber) {
        System.out.println("NOT ALLOWED");
    }

    @Override
    public void getGunCertificate(EntityManager entityManager, PersonEntity personEntity) {
        System.out.println("NOT ALLOWED");
    }

    @Override
    public void registerGun(EntityManager entityManager, PersonEntity personEntity) {
        System.out.println("NOT ALLOWED");
    }

    @Override
    public void addToPoliceReg(EntityManager entityManager, PersonEntity personEntity){
        PoliceRegisterEntity.createPoliceRegisterEntry(entityManager, personEntity);
    }
}
