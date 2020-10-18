package Classes;

import java.util.*;

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

    public boolean checkId(Map<String, Integer> cartlist, String id2) {
        int counter = (int) cartlist.entrySet().stream().filter(pair -> pair.getKey().equals(id2)).count();
        return counter != 0;
    }

    //we can remove key from map in loop only with iterator, foreach won't work
    public Map<String, Integer> removeFromCartlist(Map<String, Integer> cartlist, String id) {
        Iterator<Map.Entry<String, Integer>> itr = cartlist.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();
            if (entry.getKey().equals(id)) {
                if (entry.getValue() == 1) {
                    itr.remove();
                } else {
                    cartlist.put(entry.getKey(), entry.getValue() - 1);
                }
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
