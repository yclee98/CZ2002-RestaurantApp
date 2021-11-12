package OrdersPackage;

/**
 * This entity class represent the sales of each items sold in the restaurant
 * It implement Comparable to allow it to be sorted easily 
 */
public class SaleReportItem implements Comparable<SaleReportItem>{
    /**
     * The name of the item  
     */
    private String name;
    /**
     * The quantity sold for this item 
     */
    private int quantity;

    private double totalSales;
    /**
     * Constructor of the class to create new sale report item
     * @param name of the item
     * @param quantity sold for this item 
     */
    public SaleReportItem(String name, int quantity, double sales){
        this.name = name;
        this.quantity = quantity;
        this.totalSales = sales;
    }

    /**
     * @return the name of this sale report item
     */
    public String getName(){
        return this.name;
    }

    /**
     * Get the quantity 
     * @return the quantity of this sale report item
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * To increment the quantity of this sale report item 
     * @param quantity to increase by 
     */
    public void addQuantity(int quantity){
        this.quantity += quantity;
    }

    /**
     * To increment the total sales of this item
     * @param sales sales to increase by
     */
    public void addSales(double sales){
        this.totalSales += sales;
    }

    /**
     * Get the total sales 
     * @return the total sales of this sale report item
     */
    public double getTotalSales(){
        return this.totalSales;
    };

    /**
     * Indicates whether input object o is "equal to" this class base on the name 
     * @return true if this object is the same as the obj
     */
    public boolean equals(Object o){
        if(o instanceof String){
            String name = (String)o;
            return this.name.equals(name);
        }
        return false;
    }

    /**
     * Compare this class name with the input parameter o name
     * @param o the sale report item object to compare to
     * @return a negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(SaleReportItem o) {
        return this.name.compareTo(o.getName());
    }
}