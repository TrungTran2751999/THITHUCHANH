package services;

import models.Product;
import ultis.CSVultils;

import java.util.ArrayList;

public class ProductServices {
    private Product product;
    private CSVultils csvUltils;
    public ProductServices(){
        product = new Product();
        csvUltils = new CSVultils("data/products.csv");
        csvUltils.fileRead(Product.getListProduct());
    }
    public void addUser(Product product){
        Product.getListProduct().add(product);
    }
    public void removeUser(int id){
        ArrayList<Product> listProduct = Product.getListProduct();
        for(int i = 0; i< listProduct.size(); i++){
            if(id == Product.getListProduct().get(i).getId()){
                Product.getListProduct().remove(Product.getListProduct().get(i));
            }
        }
    }
}
