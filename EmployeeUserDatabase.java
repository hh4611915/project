public class EmployeeUserDatabase extends Database<EmployeeUser> {

    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    protected EmployeeUser createRecordFrom(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] data = line.split(",");
        if (data.length != 5) return null;

        try {
            return new EmployeeUser(data[0], data[1], data[2], data[3], data[4]);
        } catch (IllegalArgumentException e) {
            System.out.println("Skipping invalid employee: " + e.getMessage());
            return null;
        }
    }
}