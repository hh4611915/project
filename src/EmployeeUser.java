public class EmployeeUser implements Record {
    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        if (employeeId == null || employeeId.trim().isEmpty())
            throw new IllegalArgumentException("Employee ID cannot be empty.");
        if (!isValidEmail(email))
            throw new IllegalArgumentException("Email must end with '@gmail.com'.");
        if (!isValidPhone(phoneNumber))
            throw new IllegalArgumentException("Phone number must contain only digits and be 10–15 digits.");

        this.employeeId = employeeId.trim();
        this.name = (name != null) ? name.trim() : "";
        this.email = email.trim();
        this.address = (address != null) ? address.trim() : "";
        this.phoneNumber = phoneNumber.trim();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.toLowerCase().endsWith("@gmail.com");
    }

    private boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10,15}");
    }

    @Override
    public String lineRepresentation() {
        return employeeId + "," + name + "," + email + "," + address + "," + phoneNumber;
    }

    @Override
    public String getSearchKey() {
        return employeeId;
    }
}
