
public class Main {
    public static void main(String[] args) {
        // Create a database linked to employee.txt
        EmployeeUserDatabase db = new EmployeeUserDatabase("employee.txt");

        // Read existing records from file
        db.readFromFile();

        try {
            // Create a valid employee
            EmployeeUser e1 = new EmployeeUser("E250", "Ali", "ali@gmail.com", "Cairo", "0100000000");

            // Insert if not already there
            db.insertRecord(e1);

            // Save to file
            db.saveToFile();
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }


        System.out.println("All Employees:");
        for (EmployeeUser emp : db.returnAllRecords()) {
            System.out.println(emp.lineRepresentation());
        }
    }
}
