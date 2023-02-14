package view;

public class View {
    private View() {
    }

    public static void printMenu() {
        System.out.println("1. DODAJ OSOBĘ\n" +
                "2. WYŚWIETL WSZYSTKIE OSOBY\n" +
                "3. EDYTUJ OSOBĘ\n" +
                "4. USUN OSOBE\n" +
                "5. WYDAJ ZAŚWIADCZENIE LEKARSKIE\n" +
                "6. WYDAJ ZASWIADCZENIE PSYCHOLOGICZNE\n" +
                "7. DODAJ DO REJESTRU POLICYJNEGO\n" +
                "8. WYDAJ POZWOLENIE NA BRON\n" +
                "9. ZAREJESTRUJ BRON\n" +
                "10. \n" +
                "0. WYJŚCIE");
    }

    public static void printHintWhenDataEntering() {
        System.out.println("FOR DATA: \n" +
                "data should be provided in specific format: yyyy-[m]m-[d]d\n" +
                "year should be greater than 1800, and lesser than current year\n" +
                "single digit month should be prefixed with 0, should be greater than 0 and lesser than 12\n" +
                "single digit day should be prefixed with 0, should be greater than 0 and lesser than maximum number of days available per certain month\n");
    }

    public static void printHintForEnteringData() {
        System.out.println("write name then enter\n" +
                "write surname then enter\n" +
                "write birthdate then enter\n" +
                "write TRUE if citizen of RP or FALSE if not\n");

    }

    public static void printHintForQueryingByCriteria() {
        System.out.println("write the pesel number by which you want to find certain person");
    }

    public static void printHintForEditingData(){
        System.out.println("write name then enter\n" +
                "write surname then enter\n" +
                "write birthdate then enter\n" +
                "write TRUE if citizen of RP or FALSE if not\n" +
                "write date of death if relevant, if not hit enter\n");
    }
}
