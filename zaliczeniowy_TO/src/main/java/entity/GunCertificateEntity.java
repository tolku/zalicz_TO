package entity;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "gun_certificate", schema = "zaliczeniowy_to", catalog = "")
public class GunCertificateEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "gun_certificate_id")
    private int gunCertificateId;
    @OneToOne
    @JoinColumn(name = "physical_certificate_number_fk", referencedColumnName = "physical_certificate_id")
    private PhysicalCertificateEntity physicalCertificateNumberFk;
    @OneToOne
    @JoinColumn(name = "psychical_certificate_number_fk", referencedColumnName = "psychical_certificate_id")
    private PsychicalCertificateEntity psychicalCertificateNumberFk;
    @OneToOne
    @JoinColumn(name = "person_id_fk", referencedColumnName = "person_id")
    private PersonEntity personIdFk;
    @Basic
    @Column(name = "gun_type")
    private int gunType;
    @Basic
    @Column(name = "ammo_type")
    private int ammoType;
    @Basic
    @Column(name = "release_date")
    private Date releaseDate;
    @OneToOne(mappedBy = "gunCertificateNumberFk")
    private GunRegistryEntity gunRegistry;

    public int getGunCertificateId() {
        return gunCertificateId;
    }

    public void setGunCertificateId(int gunCertificateId) {
        this.gunCertificateId = gunCertificateId;
    }

    public PhysicalCertificateEntity getPhysicalCertificateNumberFk() {
        return physicalCertificateNumberFk;
    }

    public void setPhysicalCertificateNumberFk(PhysicalCertificateEntity physicalCertificateNumberFk) {
        this.physicalCertificateNumberFk = physicalCertificateNumberFk;
    }

    public int getGunType() {
        return gunType;
    }

    public void setGunType(int gunType) {
        this.gunType = gunType;
    }

    public int getAmmoType() {
        return ammoType;
    }

    public void setAmmoType(int ammoType) {
        this.ammoType = ammoType;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public PsychicalCertificateEntity getPsychicalCertificateNumberFk() {
        return psychicalCertificateNumberFk;
    }

    public void setPsychicalCertificateNumberFk(PsychicalCertificateEntity psychicalCertificateNumberFk) {
        this.psychicalCertificateNumberFk = psychicalCertificateNumberFk;
    }

    public void setPersonIdFk(PersonEntity personIdFk) {
        this.personIdFk = personIdFk;
    }

    public PersonEntity getPersonIdFk() {
        return personIdFk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GunCertificateEntity that = (GunCertificateEntity) o;
        return gunCertificateId == that.gunCertificateId && physicalCertificateNumberFk == that.physicalCertificateNumberFk && psychicalCertificateNumberFk == that.psychicalCertificateNumberFk && personIdFk == that.personIdFk && gunType == that.gunType && ammoType == that.ammoType && Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gunCertificateId, physicalCertificateNumberFk, psychicalCertificateNumberFk, personIdFk, gunType, ammoType, releaseDate);
    }

    public static void addGunCertificateEntry(EntityManager entityManager, PersonEntity personEntity,
                                              PsychicalCertificateEntity psychicalCertificateEntity,
                                              PhysicalCertificateEntity physicalCertificateEntity) {
        GunCertificateEntity gunCertificateEntity = new GunCertificateEntity();
        entityManager.getTransaction().begin();
        gunCertificateEntity.setPhysicalCertificateNumberFk(physicalCertificateEntity);
        gunCertificateEntity.setPsychicalCertificateNumberFk(psychicalCertificateEntity);
        gunCertificateEntity.setPersonIdFk(personEntity);
        gunCertificateEntity.setGunType(1); //to be changed
        gunCertificateEntity.setAmmoType(1); //to be changed
        gunCertificateEntity.setReleaseDate(Date.valueOf(LocalDate.now()));
        entityManager.persist(gunCertificateEntity);
        entityManager.getTransaction().commit();
        if (entityManager.getTransaction().isActive()){
            entityManager.getTransaction().rollback();
        }
    }

    public static Optional<GunCertificateEntity> getGunCertificateByCriteria(EntityManager entityManager,
                                                                             PersonEntity personEntity){
        try {
            return Optional.of((GunCertificateEntity) entityManager.createQuery(
                         "SELECT gunCertificate " +
                            "FROM GunCertificateEntity gunCertificate " +
                            "WHERE gunCertificate.personIdFk = :personEn")
                    .setParameter("personEn", personEntity)
                    .getSingleResult());
        } catch (NoResultException noResultException){
            return Optional.empty();
        }
    }

    public static void removeGunCertificate(EntityManager entityManager, PersonEntity personEntity){
        entityManager.getTransaction().begin();
        entityManager.remove(getGunCertificateByCriteria(entityManager, personEntity));
        entityManager.getTransaction().rollback();
        if (entityManager.getTransaction().isActive()){
            entityManager.getTransaction().rollback();
        }
    }
}
