package views;

import models.Product;

import java.util.ArrayList;

public class SearchView {
    private ArrayList<Product> listMaxProduct;
    public SearchView(){
        listMaxProduct = new ArrayList<>();
    };
    public void showSearchView(){
        maxpriceProduct();
        System.out.printf("%-15s %-15s %-30s %-30s %-30s", "id", "name", "price", "quatity", "description");
        for(int i=0; i<listMaxProduct.size(); i++){
            int id = listMaxProduct.get(i).getId();
            String name = listMaxProduct.get(i).getName();
            int price = listMaxProduct.get(i).getPrice();
            int quatity = listMaxProduct.get(i).getQuatity();
            String des = listMaxProduct.get(i).getDescription();
            System.out.println("");
            System.out.printf("%-15s %-15s %-30s %-30s %-30s", id, name, price, quatity, des);
        }
        listMaxProduct.clear();
        System.out.println("");
    }
    public int maxprice(){
        int max = 0;
        for(int i=0; i<Product.getListProduct().size(); i++){
            if(Product.getListProduct().get(i).getPrice() > max){
                max = Product.getListProduct().get(i).getPrice();
            }
        }
        return max;
    }
    public void maxpriceProduct(){
        int max = maxprice();
        for(int i=0; i<Product.getListProduct().size(); i++){
            if(Product.getListProduct().get(i).getPrice() == max){
                listMaxProduct.add(Product.getListProduct().get(i));
            }
        }
    }
}
