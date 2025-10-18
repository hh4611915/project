/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author ASUS
 */
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CustomerProductDatabase {

    private ArrayList<CustomerProduct> records;
    private String filename;

    // Constructor
    public CustomerProductDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    // 1. Read all records from file
    public void readFromFile() throws IOException {
        records.clear();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            CustomerProduct cp = createRecordFrom(line);
            if (cp != null) {
                records.add(cp);
            }
        }

        br.close();
    }

    // 2. Create a CustomerProduct object from a line of text
    public CustomerProduct createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            return null;
        }

        String customerSSN = parts[0];
        String productID = parts[1];
        String dateString = parts[2];
        boolean paid = Boolean.parseBoolean(parts[3]);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);

        CustomerProduct cp = new CustomerProduct(customerSSN, productID, date);
        cp.setPaid(paid);

        return cp;
    }

    // 3. Return all records
    public ArrayList<CustomerProduct> returnAllRecords() {
        return records;
    }

    // 4. Check if record exists
    public boolean contains(String key) {
        for (CustomerProduct cp : records) {
            if (cp.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    // 5. Get a specific record
    public CustomerProduct getRecord(String key) {
        for (CustomerProduct cp : records) {
            if (cp.getSearchKey().equals(key)) {
                return cp;
            }
        }
        return null;
    }

    // 6. Insert a new record
    public void insertRecord(CustomerProduct record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
        }
    }

    // 7. Delete a record
    public void deleteRecord(String key) {
        CustomerProduct toDelete = null;
        for (CustomerProduct cp : records) {
            if (cp.getSearchKey().equals(key)) {
                toDelete = cp;
                break;
            }
        }
        if (toDelete != null) {
            records.remove(toDelete);
        }
    }

    // 8. Save all records to file
    public void saveToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for (CustomerProduct cp : records) {
            bw.write(cp.lineRepresentation());
            bw.newLine();
        }
        bw.close();
    }
}
