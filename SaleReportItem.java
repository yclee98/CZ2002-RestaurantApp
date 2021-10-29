public class SaleReportItem implements Comparable<SaleReportItem>{
    private String name;
    private int quantity;

    public SaleReportItem(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public String getName(){
        return this.name;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }

    public boolean equals(Object o){
        if(o instanceof String){
            String name = (String)o;
            return this.name.equals(name);
        }
        return false;
    }

    @Override
    public int compareTo(SaleReportItem o) {
        return this.name.compareTo(o.getName());
    }
}