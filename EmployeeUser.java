import java.io.BufferedReader;
import java.io.*;


public class EmployeeUser {
    protected String employeeId;
    protected String name;
    protected String email;
    protected String address;
    protected String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;

        if (!isValidName(name)) {
            System.out.println("❌ Invalid name for employee " + employeeId + "!");
        }
        if (!isValidEmail(email)) {
            System.out.println("❌ Invalid email for employee " + employeeId + "!");
        }
        if (!isValidPhone(phoneNumber)) {
            System.out.println("❌ Invalid phone number for employee " + employeeId + "!");
        }
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@gmail.com");
    }

    private boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d+");
    }

    public boolean isValidEmployee() {
        return isValidName(name) && isValidEmail(email) && isValidPhone(phoneNumber);
    }

    public String getRepresentation() {
        return String.join(",", employeeId, name, email, address, phoneNumber);
    }

    public String getSearchKey() {
        return employeeId;
    }

    public void storeInFile(String filename) {
        // Validate data first
        if (!isValidEmployee() || !isValidPhone(phoneNumber) || !isValidEmail(email)) {
            System.out.println("⚠️ Employee " + employeeId + " not stored: invalid data.");
            return;
        }

        // Check if employee ID already exists in file
        File file = new File(filename);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Assuming getRepresentation() stores something like: "1234,John Doe,1234567890,john@example.com"
                    if (line.startsWith(employeeId + ",")) {
                        System.out.println("⚠️ Employee " + employeeId + " already exists. Not stored again.");
                        return;
                    }
                }
            } catch (IOException e) {
                System.out.println("❌ Error reading file: " + e.getMessage());
                return;
            }
        }

        // If ID not found, write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(getRepresentation());
            writer.newLine();
            System.out.println("✅ Employee " + name + " stored successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error storing employee: " + e.getMessage());
        }
    }
}




