public class ProductDatabase extends Database<Product> {
    public ProductDatabase(String filename) {
        super(filename);
    }
    public Product createRecordFrom(String line){
        if (line == null || line.trim().isEmpty()) return null;
        String[] data = line.split(",");
        if (data.length != 6) return null;
        try {
            int quantity = Integer.parseInt(data[4]);
            float price = Float.parseFloat(data[5]);
            return new Product(data[0], data[1], data[2], data[3], quantity, price);
        } catch (IllegalArgumentException e) {
            System.out.println("Skipping invalid product: " + e.getMessage());
            return null;
        }

    }
}
