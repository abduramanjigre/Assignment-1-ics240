public class Restaurant {
    private String name; 
    private Integer price;

    public Restaurant(String s, Integer n) {
        this.name = s;
        this.price = n;
    }
    public String getName() {return name;}
    public int getPrice() {return price;}
    public void setName(String name) {this.name = name;}
    public void setPrice(int price) {this.price = price;}

    @Override
    public String toString() {
        return name + "\t" + price;
    }
    public boolean equals(Restaurant other) {
        // if there is no other object compare nothing
        if(other == null) return false;
        
        boolean namesEqual;
        // if both names are empty then there is nothing to compare
        if(this.name == null && other.name == null) {
            namesEqual = true;
        
        }else if(this.name == null || other.name == null) {
            namesEqual = false;
        }else {
            //compare both things without caring about the case (malawax is equal to MALAWAX)
            namesEqual = this.name.equalsIgnoreCase(other.name);
        }
        boolean pricesEqual = this.price == other.price;
        return namesEqual && pricesEqual;
    }

}
