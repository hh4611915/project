import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("====================Inventory Management System====================\n");
        while(true) {
            System.out.print("1.Admin\n2.Employee\n3.Exit\n===============\n>");

            int choice;
            Scanner scan = new Scanner(System.in);
            choice = scan.nextInt();
            if (choice == 3)
                System.exit(0);
            else if (choice == 1) {
                while (choice != 3) {
                    AdminRole ad = new AdminRole();
                    System.out.print("===============================\n1.Add Employee\n2.Remove Employee\n3.Back\n===============================\n>");
                    choice = scan.nextInt();
                    switch (choice) {
                        case 1: {
                            ad.addEmployee("E9601", "Tharwat", "tharwatayman10s@gmil.com", "Celopatra", "01212083769");
                            break;
                        }
                        case 2: {
                            ad.removeEmployee("E9601");
                            break;
                        }
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                }
            }
            else if (choice==2){
                while(choice!=5){
                    EmployeeRole er = new EmployeeRole();
                    System.out.print("===============================\n1.Add Product\n2.Sell Product\n3.Return Product\n4.Apply Payment\n5.Back\n===============================\n>");
                    choice = scan.nextInt();
                    switch (choice){
                        case 1:{
                            er.addProduct("P350172","Watch","Rolex","SteveCompany",2,999999);
                            break;
                        }
                        case 2:{
                            er.purchaseProduct("3479","P350172", LocalDate.of(2025,10,19));
                            break;
                        }
                        case 3:{
                            er.returnProduct("3479","P350172",LocalDate.of(2025,10,19),LocalDate.of(2026,10,18));
                            break;
                        }
                        case 4:{
                            er.applyPayment("3479",LocalDate.of(2025,10,19));
                        }
                        case 5:
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                }
            }

                        else System.out.println("Invalid Input!!");
        }
        /*EmployeeUserDatabase db = new EmployeeUserDatabase("employee.txt");
        AdminRole ad = new AdminRole();


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
        }*/
    }
}
