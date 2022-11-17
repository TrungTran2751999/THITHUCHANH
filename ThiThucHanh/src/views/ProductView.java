package views;

import models.Product;
import pattern.PatternString;
import services.ProductServices;
import ultis.CSVultils;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductView {
    private ProductServices productServices;
    private Scanner scanner;
    private boolean isContinue = true;
    private String name;
    private int price;
    private int quatity;
    private String description;
    private String createDate;
    private PatternString patternString;
    private CSVultils csvUltils;
    private SearchView searchView;
    private CompareView compareView;

    public ProductView(){
        productServices = new ProductServices();
        scanner = new Scanner(System.in);
        patternString = new PatternString();
        csvUltils = new CSVultils("data/products.csv");
        searchView = new SearchView();
        compareView = new CompareView();
    }
    public void showProduct(){
        System.out.printf("%-15s %-15s %-30s %-30s %-30s", "id", "name", "price", "quatity", "description");
        System.out.println("");
        ArrayList<Product> listProduct = Product.getListProduct();
        for(int i = 0; i< listProduct.size(); i++){
            int id = listProduct.get(i).getId();
            String name = listProduct.get(i).getName();
            int price = listProduct.get(i).getPrice();
            int quatity = listProduct.get(i).getQuatity();
            String description = listProduct.get(i).getDescription();
            System.out.printf("%-15s %-15s %-30s %-30s %-30s", id, name, price, quatity, description);
            System.out.println("");
        }
        System.out.println("");
    }
    public void showHead(){
       System.out.println("---- CHUONG TRINH QUAN LY SAN PHAM ----");
       System.out.println("Chon chuc nang theo so (de tiep tuc)");
       System.out.println("[1]: Xem danh sach");
       System.out.println("[2]: Them moi");
       System.out.println("[3]: Cap nhat");
       System.out.println("[4]: Xoa");
       System.out.println("[5]: Sap xep");
       System.out.println("[6]: Tim san pham dat nhat");
       System.out.println("[7]: Doc tu file");
       System.out.println("[8]: Ghi tu file");
       System.out.println("[9]: Thoat");
    }
    public void showBody(){
        boolean flag = true;
        int number = 0;
        do{
            try{
                System.out.print("Chon chuc nang: ");
                number = Integer.parseInt(scanner.nextLine());
                flag = false;
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
        }while (flag);
        switch (number){
            case 1:{
                showProduct();
                break;
            }
            case 2:{
                addProduct();
                break;
            }
            case 3:{
                editProduct();
                break;
            }
            case 4:{
                removeProduct();
                break;
            }
            case 5:{
                compareView.setFlag(true);
                compareView.showCompareView();
                break;
            }
            case 6:{
                searchView.showSearchView();
                break;
            }
            case 7:{
//                csvUltils.fileRead(Product.getListProduct());
                System.out.println("Them vao thanh cong !!!");
                break;
            }
            case 8:{
                System.out.println("Viet vao thanh cong !!!");
//                csvUltils.fileWrite(Product.getListProduct());
                break;
            }
            case 9:
                isContinue = false;
        }
    }
    public void show(){
        do{
            showHead();
            showBody();
        }while (isContinue);
    }
    public void inputProduct(){
        boolean flag = true;
        do{
            try{
                System.out.print("Nhap name: ");
                name = scanner.nextLine();
                flag = false;
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
            System.out.println("");
        }while (flag);
        do{
            try{
                System.out.print("Nhap price: ");
                price = Integer.parseInt(scanner.nextLine());
                if(price > 0){
                    flag = false;
                }else{
                    flag = true;
                }
                System.out.println("");
            }catch (Exception e){
                System.out.println("Price phai la mot so lon hon 0. Vui long nhap lai ");
                flag = true;
                scanner.reset();
            }
        }while (flag);
        do{
            try{
                System.out.print("Nhap quatity: ");
                quatity = Integer.parseInt(scanner.nextLine());
                if(quatity > 0){
                    flag = false;
                }else{
                    flag = true;
                }
            }catch (Exception e){
                System.out.println("Quatity phai la mot so lon hon 0. Vui long nhap lai ");
                flag = true;
                scanner.reset();
            }
        }while (flag);
        do{
            try{
                System.out.print("Nhap mo ta: ");
                description = scanner.nextLine();
                flag = false;
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
        }while (flag);
    }
    public void addProduct(){
        int id = Math.abs((int) Instant.now(Clock.systemUTC()).getEpochSecond());
        inputProduct();
        Product product = new Product(id,name,price,quatity,description);
        productServices.addUser(product);
        csvUltils.fileWrite(Product.getListProduct());
    }
    public void removeProduct(){
        boolean flag = true;
        int id = 0;
        do{
            try{
                System.out.print("Nhap id: ");
                id = Integer.parseInt(scanner.nextLine());
                if(checkId(id) == false){
                    System.out.println("khong tim thay id");
                    flag = true;
                }else{
                    flag = false;
                }
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
        }while (flag);
        System.out.println("Co muon xoa product nay ko ? Nhap Y/N: ");
        System.out.print("Nhap option: ");
        String inputOption= scanner.nextLine();
        switch (inputOption){
            case "Y":{
                productServices.removeUser(id);
                System.out.println("Xoa thanh cong !!!");
                break;
            }
        }
        csvUltils.fileWrite(Product.getListProduct());
    }
    public void editProduct(){
        boolean flag = true;
        int id = 0;
        do{
            try{
                System.out.print("Nhap id: ");
                id = Integer.parseInt(scanner.nextLine());
                if(checkId(id) == false){
                    System.out.println("khong tim thay id");
                    flag = true;
                }else{
                    flag = false;
                }
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
        }while (flag);

        inputEditUser(id);

        csvUltils.fileWrite(Product.getListProduct());
    }
    public void inputEditUser(int id){
        boolean flag = true;
        boolean isContinue = true;
        int number = 0;
        do{
            System.out.println("[1]: Edit Name");
            System.out.println("[2]: Edit Price");
            System.out.println("[3]: Edit Quatity");
            System.out.println("[4]: Edit Description");
            System.out.println("[5]: Exit");
            do{
                try{
                    System.out.print("Nhap number: ");
                    number = Integer.parseInt(scanner.nextLine());
                    flag = false;
                }catch (Exception e){
                    flag = true;
                    scanner.reset();
                }
            }while (flag);
            switch (number){
                case 1:{
                    optionHandleEdit("name", id);
                    break;
                }
                case 2:{
                    optionHandleEdit("price", id);
                    break;
                }
                case 3:{
                    optionHandleEdit("quatity", id);
                    break;
                }
                case 4:{
                    optionHandleEdit("description", id);
                    break;
                }
                case 5:{
                    isContinue = false;
                }
            }
        }while (isContinue);
    }
    public void optionHandleEdit(String str, int id){
        boolean flag = true;
        switch (str){
            case "name":{
                do{
                    try{
                        System.out.print("Nhap name: ");
                        name = scanner.nextLine();
                        if(name.equals("")){
                            flag = true;
                        }else{
                            flag= false;
                        }
                    }catch (Exception e){
                        flag = true;
                        scanner.reset();
                    }
                }while (flag);
                for(int i = 0; i< Product.getListProduct().size(); i++){
                    if(id == Product.getListProduct().get(i).getId()){
                        Product.getListProduct().get(i).setName(name);
                    }
                }
                break;
            }
            case "price":{
                do{
                    try{
                        System.out.print("Nhap price: ");
                        price = Integer.parseInt(scanner.nextLine());
                        if(price > 0){
                            flag = false;
                        }else{
                            flag = true;
                        }
                    }catch (Exception e){
                        flag = true;
                        scanner.reset();
                    }
                }while (flag);
                for(int i = 0; i< Product.getListProduct().size(); i++){
                    if(id == Product.getListProduct().get(i).getId()){
                        Product.getListProduct().get(i).setPrice(price);
                    }
                }
                break;
            }
            case "quatity":{
                do{
                    try{
                        System.out.print("Nhap quatity: ");
                        quatity = Integer.parseInt(scanner.nextLine());
                        if(quatity <= 0){
                            flag = true;
                        }else{
                            flag= false;
                        }
                    }catch (Exception e){
                        flag = true;
                        scanner.reset();
                    }
                }while (flag);
                for(int i = 0; i< Product.getListProduct().size(); i++){
                    if(id == Product.getListProduct().get(i).getId()){
                        Product.getListProduct().get(i).setQuatity(quatity);
                    }
                }
                break;
            }
            case "description":{
                do{
                    try{
                        System.out.print("Nhap description: ");
                        description = scanner.nextLine();
                        flag= false;
                    }catch (Exception e){
                        flag = true;
                        scanner.reset();
                    }
                }while (flag);
                for(int i = 0; i< Product.getListProduct().size(); i++){
                    if(id == Product.getListProduct().get(i).getId()){
                        Product.getListProduct().get(i).setDescription(description);
                    }
                }
                break;
            }
        }

    }
    public boolean checkId(int id){
        for(int i = 0; i< Product.getListProduct().size(); i++){
            if(id == Product.getListProduct().get(i).getId()){
                return true;
            }
        }
        return false;
    }
}
