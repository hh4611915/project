
import java.io.*;
import java.util.ArrayList;

public abstract class Database<Type extends Record> {
    protected ArrayList<Type> records;
    protected String filename;

    public Database(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    // 1. Read from file
    public void readFromFile() {
        records.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Type record = createRecordFrom(line);
                if (record != null)  // only add valid records
                    records.add(record);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 2. Each subclass must implement this
    protected abstract Type createRecordFrom(String line);

    // 3. Return all records
    public ArrayList<Type> returnAllRecords() {
        return records;
    }

    // 4. Check if record exists by key
    public boolean contains(String key) {
        for (Type record : records) {
            if (record.getSearchKey().equals(key))
                return true;
        }
        return false;
    }

    // 5. Insert record if unique and valid
    public void insertRecord(Type record) {
        if (record == null) return;
        if (contains(record.getSearchKey())) {
            System.out.println("Record with ID " + record.getSearchKey() + " already exists. Skipping.");
            return;
        }
        records.add(record);
    }
    public void deleteRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getSearchKey().equals(key)) {
                records.remove(i);
                System.out.println("Record with ID " + key + " deleted successfully.");
                return;
            }
        }
        System.out.println("Record with ID " + key + " not found.");
    }

    // 6. Save all records to file
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Type record : records) {
                bw.write(record.lineRepresentation());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

}

