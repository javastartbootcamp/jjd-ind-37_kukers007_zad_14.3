import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // nie zmieniaj nic w main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        main.run(scanner);
    }

    void run(Scanner scanner) {
        // usupełnij metodę
        String filename = "countries.csv";
        Map<String, Country> countries = readCountriesFromFile(filename);
        if (countries == null) {
            System.out.println("Brak pliku countries.csv");
            return;
        }
        System.out.println("Podaj kod kraju, o którym chcesz zobaczyć informacje:");
        String countryCode = scanner.nextLine();
        Country country = countries.get(countryCode);
        if (country == null) {
            System.out.println("Kod kraju " + countryCode + " nie został znaleziony.");
        } else {
            System.out.println(country.getName() + " (" + country.getCode() + ") ma " + country.getPopulation() + " ludności.");
        }
    }

    private Map<String, Country> readCountriesFromFile(String filename) {
        Map<String, Country> countries = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] records = line.split(";");
                String code = records[0];
                String name = records[1];
                int population = Integer.parseInt(records[2]);
                countries.put(code, new Country(code, name, population));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku " + filename + ".");
            return null;
        }
        return countries;
    }
}
