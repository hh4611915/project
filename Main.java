
public class Main {
    public static void main(String[] args) {

        EmployeeUserDatabase db = new EmployeeUserDatabase("employee.txt");

        db.readFromFile();

        try {

            EmployeeUser e1 = new EmployeeUser("E250", "Ali", "ali@gmail.com", "Cairo", "0100000000");


            db.insertRecord(e1);
            db.deleteRecord("E200");

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
