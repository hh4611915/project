public class AdminRole {
    private EmployeeUserDatabase database = new EmployeeUserDatabase("employee.txt");

    public AdminRole() {
    }
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber){
        database.readFromFile();
        EmployeeUser user = new EmployeeUser(employeeId, name, email,  address, phoneNumber);
        database.insertRecord(user);
        logout();
    }
    public EmployeeUser[] getListOfEmployees(){
        database.readFromFile();
        EmployeeUser[] employees = new EmployeeUser[database.records.toArray().length];
        for(int i = 0;i<database.records.toArray().length;i++){
            employees[i] = database.createRecordFrom(database.records.get(i).lineRepresentation());
        }
        return employees;
    }
    public void removeEmployee(String key){
        database.readFromFile();
        database.deleteRecord(key);
        logout();
    }
    public void logout(){
        database.saveToFile();
    }
}
