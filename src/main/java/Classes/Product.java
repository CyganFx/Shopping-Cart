package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Product implements Comparator<Product>, Comparable<Product> {
    public static final String TABLE_NAME = "product";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CATEGORY = "category";
    public static final String PRICE = "price";
    public static final String IMAGE = "image";

    private int id;
    private String name;
    private int price;
    private String category;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Product> lowToHigh(ArrayList<Product> list) {
        Collections.sort(list);
        return list;
    }

    public ArrayList<Product> highToLow(ArrayList<Product> list) {
        list.sort(Collections.reverseOrder());
        return list;
    }

    public boolean checkId(ArrayList<String> cartlist, String id2) {
        for (String id : cartlist) {
            if (id.equals(id2))
                return true;
        }
        return false;
    }

    public ArrayList<String> removeFromCartlist(ArrayList<String> cartlist, String id) {
        for (String cid : cartlist) {
            if (cid.equals(id)) {
                cartlist.remove(cid);
                break;
            }
        }
        return cartlist;
    }

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getPrice() - o2.getPrice();
    }

    @Override
    public int compareTo(Product o) {
        return this.price - o.getPrice();
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category
                + ", image=" + image + "]";
    }
}
