import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filename = "employees.txt";

        try {
            File file = new File(filename);
            if (file.createNewFile()) {
                System.out.println("📁 File created: " + file.getName());
            } else {
                System.out.println("📁 File already exists, new data will be added.");
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return;
        }

        EmployeeUser[] employees = {
                new EmployeeUser("E101", "Sara", "sara@mail.com", "Cairo", "0101234567"),
                new EmployeeUser("E102", "", "ali@mail.com", "Alexandria", "0109876543"),
                new EmployeeUser("E103", "Omar", "omar@gmail.com", "Giza", "0105555666"),
                new EmployeeUser("E104", "Nada", "nada@mail.com", "Tanta", "01A23456"),
                new EmployeeUser("E105", "Hodar", "hoda@mail.com", "Cairo", "0119998888")
        };

        for (EmployeeUser emp : employees) {
            emp.storeInFile(filename);
        }

        System.out.println("\n✅ Program finished. Check employees.txt for valid employees only.");
    }
}
