package controller;

import java.util.ArrayList;

import model.Brand;
import model.Category;
import model.Product;

import java.util.*;

// Manager quan li CRUD 1 cai Product: create, read, update, delete
public class Manager {
    List<Product> listProduct = new ArrayList<>();
    // new: gui don ly di den toa an RAM xin phan chia tai san vai MB
    // de no luu tru do dac nha cua no hau chia tay

    List<Brand> listBrand = new ArrayList<>();
    List<Category> listCategory = new ArrayList<>();

    public static void main(String[] args) {
        Manager m = new Manager();
        //vi listProduct k static nen phai dung thong qua m.listProduct
        m.listProduct.add(new Product("P1", "A1", "B1", "C1", 2021, 100));
        m.listProduct.add(new Product("P2", "A2", "B2", "C2", 2022, 200));
        m.listProduct.add(new Product("P3", "A3", "B3", "C3", 2023, 300));
        m.listProduct.add(new Product("P4", "A4", "B4", "C4", 2024, 400));
        // m.createProduct();
        m.updateInformationByID();
        m.display();
    }
    public void display(){
        for (Product p : listProduct) {
            System.out.println(p.toString());
        }
    }
    public boolean checkIdFromList(String str, String id) {
        switch (str) {
            case "Category":
                for (Product tay : listProduct) { //listCategory sai vi mik can check cateID trong list product
                    if (tay.getCategoryId().equals(id)) {// equals: so sanh full chuoi
                        return true;
                    }
                }
                break;
            case "Brand":
                for (Product tay : listProduct) {//listBrand sai vi mik can check brandID trong list product
                    if (tay.getBrandId().equals(id)) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    // check id nhap vao co ton tai hay k: co -> tra ve true
    public boolean checkIdExist(String id) {
        for (Product tay : listProduct) {
            if (tay.getId().equals(id)) {// equals: so sanh full chuoi
                return true;
            }
        }
        return false;
    }

    public void createProduct() {
        Scanner sc = new Scanner(System.in);
        // String id = UUID.randomUUID().toString(); //tao id ngau nhien k trung
        String id, name, cateId, brandId; // tao id
        int modelYear;
        double listPrice;

        // nhap den khi nao id k ton tai moi thoi
        do {
            System.out.println("Enter id: ");
            id = sc.nextLine();// update gia tri id de check den khi nao thoa check
        } while (checkIdExist(id) == true);

        // neu name rong -> nhap lai
        do {
            System.out.println("Enter name: ");
            name = sc.nextLine();
        } while (name.isEmpty());

        
        do {
            System.out.println("Enter brandId: ");
            brandId = sc.nextLine();
        } while (checkIdFromList("Brand", brandId) == true);

        do {
            System.out.println("Enter cateId: ");
            cateId = sc.nextLine();
        } while (checkIdFromList("Category", cateId) == true);//=false la infinite loop

        do {
            System.out.println("Enter model year: ");
            modelYear = Integer.parseInt(sc.nextLine());//exception -> try catch
        } while (modelYear<1900 || modelYear>2100);

        do {
            System.out.println("Enter list price: ");
            listPrice = Double.parseDouble(sc.nextLine());
        } while (listPrice < 0); //> 0 la sai

        Product p = new Product(id, name, brandId, cateId, modelYear, listPrice);
        listProduct.add(p);
    }

    public List<Product> searchProduct(String str) {
        List<Product> result = new ArrayList<>(); // ngoi nha tinh thuong
        if (listProduct.size() == 0) {
            return null;
        }
        for (Product tay : result) {
            if (tay.getName().contains(str)) {
                result.add(tay);
            }
        }
        return result;
    }

    // updateInformation: ktr ton tai -> update
    //BTVN: implement code cho updateInformation
    public void updateInformationByID(){
        String id;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter id to find and update: ");
            id = sc.nextLine();
        } while (!checkIdExist(id));//exist moi tim, k exist nhap lai
        for (Product p : listProduct) {
            if (p.getId().equals(id)) {
                // Scanner sc = new Scanner(System.in);
                String name, cateId, brandId; 
                int modelYear;
                double listPrice;
        
                // neu name rong -> nhap lai
                do {
                    System.out.println("Enter name: ");
                    name = sc.nextLine();
                } while (name.isEmpty());
        
                do {
                    System.out.println("Enter cateId: ");
                    cateId = sc.nextLine();
                } while (checkIdFromList("Category", cateId) == true);
        
                do {
                    System.out.println("Enter brandId: ");
                    brandId = sc.nextLine();
                } while (checkIdFromList("Brand", brandId) == true);
        
                do {
                    System.out.println("Enter model year: ");
                    modelYear = Integer.parseInt(sc.nextLine());
                } while (modelYear<1900 || modelYear>2100);
        
                do {
                    System.out.println("Enter list price: ");
                    listPrice = Double.parseDouble(sc.nextLine());
                }while (listPrice < 0);

                p.setName(name);
                p.setBrandId(brandId);
                p.setCategoryId(id);
                p.setListPrice(listPrice);
                p.setModelYear(modelYear);
                System.out.println("Update data of product: "+id+" successful");
            }
        }
    }
}
