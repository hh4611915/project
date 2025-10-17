
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
        return email != null && email.contains("@mail.com");
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
        if (!isValidEmployee()) {
            System.out.println("⚠️ Employee " + employeeId + " not stored: invalid data.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(getRepresentation());
            writer.newLine();
            System.out.println("✅ Employee " + name + " stored successfully.");
        } catch (IOException e) {
            System.out.println("Error storing employee: " + e.getMessage());
        }
    }
}






