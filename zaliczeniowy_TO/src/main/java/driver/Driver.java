package driver;

import entity.*;
import exception.PersonNotFoundException;
import mapper.PersonEntityMapper;
import person.Person;
import person.state.EligibleForInspection;
import validator.InputValidator;
import view.View;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

@SuppressWarnings({"java:S2189", "java:S106"})
public class Driver {

    public static void main(String... args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("default")
                .createEntityManager();
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                View.printMenu();
                switch (InputValidator.validateMenuInput(scanner.nextLine())) {
                    case 1:
                        View.printHintForEnteringData();
                        View.printHintWhenDataEntering();
                        addPerson(scanner, entityManager);
                        break;
                    case 2:
                        getPersonList(entityManager);
                        break;
                    case 3:
                        View.printHintForQueryingByCriteria();
                        editPersonData(entityManager, scanner);
                        break;
                    case 4:
                        View.printHintForQueryingByCriteria();
                        deletePerson(entityManager, scanner);
                        break;
                    case 5:
                        getPhysicalCertificate(entityManager, scanner);
                        break;
                    case 6:
                        getPsychologicalCertificate(entityManager, scanner);
                        break;
                    case 7:
                        addToPoliceRegistry(entityManager, scanner);
                        break;
                    case 8:
                        getGunCertificate(entityManager, scanner);
                        break;
                    case 9:
                        registerGun(entityManager, scanner);
                        break;
                    case 0:
                        exit(0);
                }
            } while (true);
        }

    }

    private static void addPerson(Scanner scanner, EntityManager entityManager) {
        PersonEntity.createPersonInDatabase(createPerson(scanner), entityManager);
        System.out.println(PersonEntity.PERSON_ADDED_TO_DATABASE_MESSAGE);
    }

    private static Person createPerson(Scanner scanner) {
        return new Person.PersonBuilder()
                .setName(InputValidator.validateString(scanner.nextLine()))
                .setSurname(InputValidator.validateString(scanner.nextLine()))
                .setBirthDate(InputValidator.validateDate(scanner.nextLine()))
                .setPersonState(new EligibleForInspection())
                .setCitizenOfRP(InputValidator.validateBoolean(scanner.nextLine()))
                .build();
    }

    private static List<PersonEntity> getAllPeople(EntityManager entityManager) {
        return PersonEntity.queryPersonTable(entityManager);
    }

    private static List<PersonEntity> getPeopleByCriteria(EntityManager entityManager, int peselNumber) {
        return PersonEntity.queryPersonByCriteria(entityManager, peselNumber);
    }

    private static void editPersonData(EntityManager entityManager, Scanner scanner) {
        View.printHintForEditingData();
        PersonEntity.alterPersonData(getPeopleByCriteria(entityManager, InputValidator.validatePeselNumber(scanner.nextLine()))
                        .stream()
                        .findFirst()
                        .orElseThrow(PersonNotFoundException::new),
                entityManager,
                scanner);
    }

    private static void deletePerson(EntityManager entityManager, Scanner scanner) {
        PersonEntity.deletePerson(getPeopleByCriteria(entityManager, InputValidator.validatePeselNumber(scanner.nextLine()))
                        .stream()
                        .findFirst()
                        .orElseThrow(PersonNotFoundException::new),
                entityManager);
    }

    private static void getPersonList(EntityManager entityManager) {
        PersonEntityMapper.mapToPersonList(getAllPeople(entityManager)).forEach(System.out::println);
    }

    private static void getPhysicalCertificate(EntityManager entityManager, Scanner scanner) {
        int peselNumber = InputValidator.validatePeselNumber(scanner.nextLine());
        PersonEntity personEntity = getPeopleByCriteria(entityManager, peselNumber).stream()
                .findFirst()
                .orElseThrow(PersonNotFoundException::new);
        Person person = PersonEntityMapper.mapToPerson(personEntity);
        person.setPersonState(entityManager, peselNumber);
        person.getPersonState().getPhysicalCertificate(entityManager, peselNumber);
    }

    private static void getPsychologicalCertificate(EntityManager entityManager, Scanner scanner) {
        int peselNumber = InputValidator.validatePeselNumber(scanner.nextLine());
        PersonEntity personEntity = getPeopleByCriteria(entityManager, peselNumber).stream()
                .findFirst()
                .orElseThrow(PersonNotFoundException::new);
        Person person = PersonEntityMapper.mapToPerson(personEntity);
        person.setPersonState(entityManager, peselNumber);
        person.getPersonState().getPsychicalCertificate(entityManager, peselNumber);
    }

    private static void addToPoliceRegistry(EntityManager entityManager, Scanner scanner) {
        PersonEntity personEntity = getPeopleByCriteria(entityManager, InputValidator.validatePeselNumber(scanner.nextLine())).stream()
                .findFirst()
                .orElseThrow(PersonNotFoundException::new);
        Person person = PersonEntityMapper.mapToPerson(personEntity);
        person.setPersonState(entityManager, InputValidator.validatePeselNumber(scanner.nextLine()));
        person.getPersonState().addToPoliceReg(entityManager, personEntity);
    }

    private static void getGunCertificate(EntityManager entityManager, Scanner scanner){
        PersonEntity personEntity = getPeopleByCriteria(entityManager, InputValidator.validatePeselNumber(scanner.nextLine())).stream()
                .findFirst()
                .orElseThrow(PersonNotFoundException::new);
        Person person = PersonEntityMapper.mapToPerson(personEntity);
        person.setPersonState(entityManager, InputValidator.validatePeselNumber(scanner.nextLine()));
        person.getPersonState().getGunCertificate(entityManager, personEntity);
    }

    private static void registerGun(EntityManager entityManager, Scanner scanner){
        PersonEntity personEntity = getPeopleByCriteria(entityManager, InputValidator.validatePeselNumber(scanner.nextLine())).stream()
                .findFirst()
                .orElseThrow(PersonNotFoundException::new);
        Person person = PersonEntityMapper.mapToPerson(personEntity);
        person.setPersonState(entityManager, InputValidator.validatePeselNumber(scanner.nextLine()));
        person.getPersonState().registerGun(entityManager, personEntity);
    }
}
