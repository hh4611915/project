import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class EmployeeRole {
    private ProductDatabase pd = new ProductDatabase("product.txt");
    private CustomerProductDatabase cpd = new CustomerProductDatabase("CustomerProducts.txt");

    public EmployeeRole(){}



    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity)
    {
        Product p = new Product(productID, productName, manufacturerName, supplierName, quantity,1000);
        pd.readFromFile();
        pd.insertRecord(p);
        cpd.readFromFile();
        logout();
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price)
    {
        Product p = new Product(productID, productName, manufacturerName, supplierName, quantity,price);
        pd.readFromFile();
        if(p.getSearchKey()!=null) {
            pd.insertRecord(p);
        }
        cpd.readFromFile();
        logout();
    }

    public Product[] getListOfProducts(){
        pd.readFromFile();
        Product[] p = new Product[pd.records.toArray().length];
        for(int i = 0;i<pd.records.toArray().length;i++){
            p[i] = pd.createRecordFrom(pd.records.get(i).lineRepresentation());
        }
        return p;
    }

    public CustomerProduct[] getListOfPurchasingOperations(){
        cpd.readFromFile();
        CustomerProduct[] cp = new CustomerProduct[cpd.records.toArray().length];
        for(int i = 0;i<cpd.records.toArray().length;i++){
            cp[i] = cpd.createRecordFrom(cpd.records.get(i).lineRepresentation());
        }
        return cp;
    }
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate){
        pd.readFromFile();
        cpd.readFromFile();
        int i;

        if(!pd.contains(productID)) {
            System.out.println("productID not found");
            return false;
        }
        Product[] p = getListOfProducts();

        for(i = 0; i<p.length;i++ ){
            if(p[i].getSearchKey().equals(productID))
                break;
        }
        if(p[i].getQuantity() == 0)
        {
            System.out.println("product quantity = 0");
            return false;
        }
        p[i].setQuantity(p[i].getQuantity()-1);
        CustomerProduct cp = new CustomerProduct(customerSSN,productID,purchaseDate);
        cpd.insertRecord(cp);
        pd.records.remove(i);
        pd.records.add(p[i]);
        logout();
        return true;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate ,LocalDate returnDate)
    {
        pd.readFromFile();
        cpd.readFromFile();
        int i, j ;
        boolean flag = true;
        if(returnDate.isBefore(purchaseDate))
        {
            System.out.println("invalid date");
            return  -1;
        }
        else if(!pd.contains(productID))
        {
            System.out.println("product not found");
            return -1;
        }
        CustomerProduct[] cp = getListOfPurchasingOperations();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String line = customerSSN + "," + productID + "," + purchaseDate.format(formatter);

        for(i=0;i<cp.length;i++)
        {
            if(line.equalsIgnoreCase(cp[i].getSearchKey()))
            {
                flag=false;
                break;
            }
        }
        if(flag)
        {
            System.out.println("purchase not found");
            return -1;
        }
        if(ChronoUnit.DAYS.between(purchaseDate,returnDate) > 14)
        {
            System.out.println("more than 14 days have passed cant return product");
            return -1;
        }
        cpd.deleteRecord(cp[i].getSearchKey());
        Product[] p = getListOfProducts();

        for(j = 0; j<p.length;j++ ){
            if(p[j].getSearchKey().equals(productID)) {
                p[j].setQuantity(p[j].getQuantity() + 1);
                break;
            }
        }
        pd.records.remove(j);
        pd.insertRecord(p[j]);
        logout();
        return p[j].getPrice();
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate){
        pd.readFromFile();
        cpd.readFromFile();
        CustomerProduct[] cp = getListOfPurchasingOperations();
        int i;
        boolean flag = false;
        for(i = 0; i<cp.length;i++)
        {
            if(cp[i].getCustomerSSN().equals(customerSSN) && cp[i].getPurchaseDate().equals(purchaseDate))
            {
                flag = true;
                break;
            }
        }
        if(!flag)
        {
            System.out.println("PURCHASE NOT FOUND!!!");
            return false;
        }
        if(cp[i].isPaid())
        {
            System.out.println("PURCHASE ALREADY PAID!!!");
            return false;
        }
        cp[i].setPaid(true);
        cpd.records.remove(i);
        cpd.insertRecord(cp[i]);
        logout();
        return true;

    }

    public void logout(){
        pd.saveToFile();
        cpd.saveToFile();
    }


}
