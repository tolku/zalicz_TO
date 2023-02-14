package person.state;

import entity.*;

import javax.persistence.EntityManager;

public class HavingGun implements PersonState{
    @Override
    public void getPhysicalCertificate(EntityManager entityManager, int peselNumber) {
        System.out.println("ALREADY GOT PHYSICAL CERTIFICATE");
    }

    @Override
    public void getPsychicalCertificate(EntityManager entityManager, int peselNumber) {
        System.out.println("ALREADY GOT PSYCHICAL CERTIFICATE");
    }

    @Override
    public void getGunCertificate(EntityManager entityManager, PersonEntity personEntity) {
        System.out.println("ALREADY GOT A GUN CERTIFICATE");
    }

    @Override
    public void registerGun(EntityManager entityManager, PersonEntity personEntity) {
        GunRegistryEntity.addGunRegistryEntry(entityManager, personEntity,
                GunCertificateEntity.getGunCertificateByCriteria(entityManager, personEntity).get());
    }

    @Override
    public void addToPoliceReg(EntityManager entityManager, PersonEntity personEntity){
        PoliceRegisterEntity.createPoliceRegisterEntry(entityManager, personEntity);
    }
}
