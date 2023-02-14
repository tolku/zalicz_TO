package person.state;

import entity.*;

import javax.persistence.EntityManager;

public class HavingPermission implements PersonState{
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
        GunCertificateEntity.addGunCertificateEntry(entityManager, personEntity,
                PsychicalCertificateEntity.getPsychicalCertificateByCriteria(entityManager, personEntity).get(),
                PhysicalCertificateEntity.getPhysicalCertificateByCriteria(entityManager, personEntity).get());
    }

    @Override
    public void registerGun(EntityManager entityManager, PersonEntity personEntity) {
        System.out.println("CANNOT DO THAT");
    }

    @Override
    public void addToPoliceReg(EntityManager entityManager, PersonEntity personEntity){
        PoliceRegisterEntity.createPoliceRegisterEntry(entityManager, personEntity);
    }
}
