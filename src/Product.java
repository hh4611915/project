public class Product implements Record {
    private String productID, productName, manufacturerName, supplierName;
    private int quantity;
    private float price;

    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        if (productID == null || productID.trim().isEmpty()){
            System.out.println("Invalid ID");
            return;
        }
        if (quantity<0)
        {
            System.out.println("Invalid quantity");
            return;
        }
        if (price<0)
        {
            System.out.println("Invalid price");
            return;
        }
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }
    public float getPrice()
    {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String lineRepresentation() {
        return productID + "," + productName + "," + manufacturerName + "," + supplierName + "," + quantity + "," + price;
    }
    @Override
    public String getSearchKey(){
        return productID;
    }
}
