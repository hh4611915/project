
public class EmployeeUser implements Record {
    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        if (employeeId == null || employeeId.trim().isEmpty())
        {
            System.out.println("Invalid ");
            return;
        }

        if (!isValidEmail(email))
        {
            System.out.println("Invalid");
            return;
        }

        if (!isValidPhone(phoneNumber))
        {
            System.out.println("Invalid phone number");
            return;
        }


        this.employeeId = employeeId.trim();
        this.name = (name != null) ? name.trim() : "";
        this.email = email.trim();
        this.address = (address != null) ? address.trim() : "";
        this.phoneNumber = phoneNumber.trim();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.toLowerCase().endsWith("@gmail.com");
    }
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }

        return true;
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