package lab2;
import java.util.*;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonConsoleApp.java
 *
 *   Autor: Paweł Rogaliński
 *    Data: październik 2018 r.
 */
public class PersonConsoleApp {

    private static final String GREETING_MESSAGE =
            "Program Person - wersja konsolowa\n" +
                    "Autor: Paweł Rogaliński\n" +
                    "Data:  październik 2018 r.\n";
    /**
     * Zmodyfikowalem przykladowe menu (usunalem mozliwosc zapisu i odczytu z pliku)
     * Aby dodac do danej kolekcji osobe nalezy wpierw wczytac nowa osobe (opcja nr1 w glownym menu)
     * oraz nastepnie przejsc do menu kolekcji do ktorej chcemy zapisac dana osobe
     * usuwanie z danej kolekcji oraz wypisywanie jej elementow robi sie w odpowiednim dla danej kolekcji menu
     * Aby dodac inna osobe nalezy ponownie wczytac nowa osobe (opcja nr1 w glownym menu)
     */
    private static final String MENU =
            "    M E N U   G Ł Ó W N E  \n" +
                    "1 - Wczytaj nowa osoby \n" +
                    "2 - Usuń dane osoby        \n" +
                    "3 - Modyfikuj dane osoby   \n" +
                    "4 - Menu arrayList         \n" +
                    "5 - Menu linkedList        \n" +
                    "6 - Menu treeSet           \n" +
                    "7 - Menu hashSet           \n" +
                    "8 - Menu hashMap           \n" +
                    "9 - Menu treeMap           \n" +
                    "0 - Zakończ program        \n";

    private static final String CHANGE_MENU =
            "   Co zmienić?     \n" +
                    "1 - Imię           \n" +
                    "2 - Nazwisko       \n" +
                    "3 - Rok urodzenia  \n" +
                    "4 - Stanowisko     \n" +
                    "0 - Powrót do menu głównego\n";

    private static final String ARRAYLIST_MENU =
            "    MENU   ARRAYLIST  \n" +
                    "1 - Dodaj aktualna osobe (currentPerson) do arraylist \n" +
                    "2 - Usuń osobe z arrayList   \n" +
                    "3 - Wypisz elementy arrayList  \n" +
                    "0 - Powrót do menu głównego      \n";

    private static final String LINKEDLIST_MENU =
            "    MENU   LINKEDLIST  \n" +
                    "1 - Dodaj aktualna osobe (currentPerson) do linkedList \n" +
                    "2 - Usuń osobe z linkedList   \n" +
                    "3 - Wypisz elementy linkedList  \n" +
                    "0 - Powrót do menu głównego       \n";

    private static final String TREESET_MENU =
            "    MENU   TREESET  \n" +
                    "1 - Dodaj aktualna osobe (currentPerson) do treeSet \n" +
                    "2 - Usuń osobe z treeSet  \n" +
                    "3 - Wypisz elementy treeSet  \n" +
                    "0 - Powrót do menu głównego       \n";

    private static final String HASHSET_MENU =
            "    MENU   HASHSET  \n" +
                    "1 - Dodaj aktualna osobe (currentPerson) do hashSet \n" +
                    "2 - Usuń osobe z hashSet  \n" +
                    "3 - Wypisz elementy hashSet  \n" +
                    "0 - Powrót do menu głównego       \n";

    private static final String HASHMAP_MENU =
            "    MENU   HASHMAP  \n" +
                    "1 - Dodaj aktualna osobe (currentPerson) do hashSap \n" +
                    "2 - Usuń osobe z hashMap  \n" +
                    "3 - Wypisz elementy hashMap  \n" +
                    "0 - Powrót do menu głównego       \n";

    private static final String TREEMAP_MENU =
            "    MENU   ARRAYLIST  \n" +
                    "1 - Dodaj aktualna osobe (currentPerson) do treeMap \n" +
                    "2 - Usuń osobe z treeMap  \n" +
                    "3 - Wypisz elementy treeMap \n" +
                    "0 - Powrót do menu głównego       \n";



    /**
     * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
     * prostych metod do realizacji dialogu z użytkownikiem
     * w oknie konsoli tekstowej.
     */
    private static final ConsoleUserDialog UI = new ConsoleUserDialog();


    public static void main(String[] args) {
        // Utworzenie obiektu aplikacji konsolowej
        // oraz uruchomienie głównej pętli aplikacji.
        PersonConsoleApp application = new PersonConsoleApp();
        application.runMainLoop();
    }


    /*
     *  Referencja do obiektu, który zawiera dane aktualnej osoby.
     */
    private Person currentPerson = null;
    private ArrayList<Person> arrayList = new ArrayList<Person>();
    private LinkedList<Person> linkedList = new LinkedList<Person>();
    private TreeSet<Person> treeSet = new TreeSet<Person>();
    private HashSet<Person> hashSet = new HashSet<Person>();
    private HashMap<String,Person> hashMap= new HashMap<String,Person>();
    private TreeMap<String,Person> treeMap = new TreeMap<String,Person>();

    /*
     *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
     *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
     *         w której program się zatrzymuje aż do zakończenia
     *         działania za pomocą metody System.exit(0);
     */
    public void runMainLoop() {
        UI.printMessage(GREETING_MESSAGE);

        while (true) {
            UI.clearConsole();
            showCurrentPerson();

            try {
                switch (UI.enterInt(MENU + "==>> ")) {
                    case 1:
                        // utworzenie nowej osoby
                        currentPerson = createNewPerson();
                        break;
                    case 2:
                        // usunięcie danych aktualnej osoby.
                        currentPerson = null;
                        UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
                        break;
                    case 3:
                        // zmiana danych dla aktualnej osoby
                        if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
                        changePersonData(currentPerson);
                        break;
                    case 4:
                        //menu ArrayList
                        switch (UI.enterInt(ARRAYLIST_MENU + "==>> ")){
                            //dodawanie do arraylist
                            case 1: {
                                arrayList.add(currentPerson);
                            }
                            break;
                            //usuwanie elementow z ArrayList
                            case 2:{
                                int index = UI.enterInt("Prosze podac index elementu do usuniecia");
                                arrayList.remove(index);
                            }
                            break;
                            //wyswietlanie elementow ArrayList
                            case 3:{
                                Iterator myIterator = arrayList.iterator();
                                int i = 0;
                                while(myIterator.hasNext()){
                                    System.out.print(i++ + ". ");
                                    System.out.println(myIterator.next());
                                }

                            }
                            break;
                            case 0: return;

                        }

                    case 5: {
                        //menu LinkedList
                        switch (UI.enterInt(LINKEDLIST_MENU + "==>> ")){
                            //dodawanie do LinkedList
                            case 1:{
                                linkedList.add(currentPerson);
                            }
                            break;
                            //usuwanie elementow z LinkedList
                            case 2:{
                                int index = UI.enterInt("Prosze podac index elementu do usuniecia");
                                linkedList.remove(index);
                            }
                            break;
                            //wyswietlanie elementow LinkedList
                            case 3:{
                                Iterator myIterator = linkedList.iterator();
                                int i = 0;
                                while(myIterator.hasNext()){
                                    System.out.print(i++ + ". ");
                                    System.out.println(myIterator.next());
                                }
                            }
                            break;
                            case 0: return;
                        }
                    }

                    break;
                    case 6:{
                        //menu TreeSet
                        switch (UI.enterInt(TREESET_MENU + "==>> ")){
                            //dodawanie do TreeSet
                            case 1:{
                                treeSet.add(currentPerson);
                            }
                            break;
                            //usuwanie elementow z TreeSet
                            case 2:{
                                int index = UI.enterInt("Prosze podac index elementu do usuniecia");
                                Iterator myIterator = treeSet.iterator();
                                for(int i = 0;i<index;i++){
                                    myIterator.hasNext();
                                    myIterator.next();
                                }myIterator.remove();
                            }
                            break;
                            //wyswietlanie elementow TreeSet
                            case 3:{
                                Iterator myIterator = treeSet.iterator();
                                int i = 0;
                                while(myIterator.hasNext()){
                                    System.out.print(i++ + ". ");
                                    System.out.println(myIterator.next());
                                }
                            }
                            break;
                            case 0: return;
                        }
                    }
                    break;
                    case 7:{
                        //menu HashSet
                        switch (UI.enterInt(HASHSET_MENU + "==>> ")){
                            //dodawanie do HashSet
                            case 1:{
                                hashSet.add(currentPerson);
                            }
                            break;
                            //usuwanie elementow z HashSet
                            case 2:{
                                int index = UI.enterInt("Prosze podac index elementu do usuniecia");
                                Iterator myIterator = hashSet.iterator();
                                for(int i = 0;i<index;i++){
                                    myIterator.hasNext();
                                    myIterator.next();
                                }myIterator.remove();
                            }
                            break;
                            //wyswietlanie elementow HashSet
                            case 3:{
                                Iterator myIterator = hashSet.iterator();
                                int i = 1;
                                while(myIterator.hasNext()){
                                    System.out.print(i++ + ". ");
                                    System.out.println(myIterator.next());
                                }
                            }
                            break;
                            case 0: return;
                        }
                    }
                    break;
                    case 8:{
                        //menu HashMap
                        switch (UI.enterInt(HASHMAP_MENU + "==>> ")){
                            //dodawanie do HashMap
                            case 1:{
                                String index = UI.enterString("Prosze podac klucz");
                                hashMap.put(index,currentPerson);
                            }
                            break;
                            //usuwanie elementow z HashMap
                            case 2:{
                                String index = UI.enterString("Prosze podac klucz do usuniecia");
                                hashMap.remove(index);
                            }
                            break;
                            //wyswietlanie elementow HashMap
                            case 3:{
                                for(HashMap.Entry<String,Person> set: hashMap.entrySet()){
                                    System.out.println(set.getKey() + "=" + set.getValue());
                                }
                            }
                            break;
                            case 0: return;

                        }
                    }
                    break;
                    case 9:{
                        //menu TreeMap
                        switch (UI.enterInt(TREEMAP_MENU + "==>> ")){
                            //dodawanie do TreeMap
                            case 1:{
                                String index = UI.enterString("Prosze podac klucz");
                                treeMap.put(index,currentPerson);
                            }
                            break;
                            //usuwanie elementow z TreeMap
                            case 2:{
                                String index = UI.enterString("Prosze podac klucz do usuniecia");
                                treeMap.remove(index);
                            }
                            break;
                            //wyswietlanie elementow TreeMap
                            case 3:{
                                for(HashMap.Entry<String,Person> set: treeMap.entrySet()){
                                    System.out.println(set.getKey() + "=" + set.getValue());
                                }
                            }
                            break;
                            case 0: return;
                        }
                    }
                    break;

                    case 0:
                        // zakończenie działania programu
                        UI.printInfoMessage("\nProgram zakończył działanie!");
                        System.exit(0);
                } // koniec instrukcji switch
            } catch (PersonException e) {
                // Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
                // gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
                // poszczególnych atrybutów.
                // Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
                UI.printErrorMessage(e.getMessage());
            }
        } // koniec pętli while
    }


    /*
     *  Metoda wyświetla w oknie konsoli dane aktualnej osoby
     *  pamiętanej w zmiennej currentPerson.
     */
    void showCurrentPerson() {
        showPerson(currentPerson);
    }


    /*
     * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej
     * przez obiekt klasy Person
     */
    static void showPerson(Person person) {
        StringBuilder sb = new StringBuilder();

        if (person != null) {
            sb.append("Aktualna osoba: \n")
                    .append("      Imię: ").append(person.getFirstName()).append("\n")
                    .append("  Nazwisko: ").append(person.getLastName()).append("\n")
                    .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
                    .append("Stanowisko: ").append(person.getJob()).append("\n");
        } else
            sb.append( "Brak danych osoby\n" );
        UI.printMessage( sb.toString() );
    }


    /*
     * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
     * klasy Person i wypełnia atrybuty wczytanymi danymi.
     * Walidacja poprawności danych odbywa się w konstruktorze i setterach
     * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
     * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
     */
    static Person createNewPerson(){
        String first_name = UI.enterString("Podaj imię: ");
        String last_name = UI.enterString("Podaj nazwisko: ");
        String birth_year = UI.enterString("Podaj rok ur.: ");
        UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
        String job_name = UI.enterString("Podaj stanowisko: ");
        Person person;
        try {
            // Utworzenie nowego obiektu klasy Person oraz
            // ustawienie wartości wszystkich atrybutów.
            person = new Person(first_name, last_name);
            person.setBirthYear(birth_year);
            person.setJob(job_name);
        } catch (PersonException e) {
            // Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
            // gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
            // poszczególnych atrybutów.
            // Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
            UI.printErrorMessage(e.getMessage());
            return null;
        }
        return person;
    }


    /*
     * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów
     * obiekty person i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
     * Walidacja poprawności wczytanych danych odbywa się w setterach
     * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
     * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
     */
    static void changePersonData(Person person)
    {
        while (true) {
            UI.clearConsole();
            showPerson(person);

            try {
                switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
                    case 1:
                        person.setFirstName(UI.enterString("Podaj imię: "));
                        break;
                    case 2:
                        person.setLastName(UI.enterString("Podaj nazwisko: "));
                        break;
                    case 3:
                        person.setBirthYear(UI.enterString("Podaj rok ur.: "));
                        break;
                    case 4:
                        UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
                        person.setJob(UI.enterString("Podaj stanowisko: "));
                        break;
                    case 0: return;
                }  // koniec instrukcji switch
            } catch (PersonException e) {
                // Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
                // gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
                // poszczególnych atrybutów.
                // Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
                UI.printErrorMessage(e.getMessage());
            }
        }
    }


}  // koniec klasy PersonConsoleApp