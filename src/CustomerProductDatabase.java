package lab4;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProductDatabase extends Database<CustomerProduct> {

    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    protected CustomerProduct createRecordFrom(String line) {
        try {
            String[] data = line.split(",");
            if (data.length != 4) return null;

            String customerSSN = data[0];
            String productID = data[1];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate purchaseDate = LocalDate.parse(data[2], formatter);
            boolean paid = Boolean.parseBoolean(data[3]);

            CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
            cp.setPaid(paid);
            return cp;

        } catch (Exception e) {
            System.out.println(" Skipping invalid line in CustomerProducts.txt");
            return null;
        }
    }
}
