package person.state;

import entity.PersonEntity;

import javax.persistence.EntityManager;

public class Dead implements PersonState{
    @Override
    public void getPhysicalCertificate(EntityManager entityManager, int peselNumber) {
        System.out.println("CANNOT GET PHYSICAL CERTIFICATE");
    }

    @Override
    public void getPsychicalCertificate(EntityManager entityManager, int peselNumber) {
        System.out.println("CANNOT GET PSYCHICAL CERTIFICATE");
    }

    @Override
    public void getGunCertificate(EntityManager entityManager, PersonEntity personEntity) {
        System.out.println("CANNOT GET GUN CERTIFICATE");
    }

    @Override
    public void registerGun(EntityManager entityManager, PersonEntity personEntity) {
        System.out.println("CANNOT REGISTER A GUN");
    }

    @Override
    public void addToPoliceReg(EntityManager entityManager, PersonEntity personEntity){
        System.out.println("CANNOT DO THAT");
    }
}
