
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mucah
 */
public class Main {
    
    public static void main(String[] args){
        
        boolean isSameProduct = false;
        
        List<Product> products = new ArrayList<>();
        List<Product> secondPhase = new ArrayList<>();
        List<Product> finalPhase = new ArrayList<>();
        
        Product product1 = new Product(4611, "L101-4611-D00000-1801", LocalDateTime.of(2020, 1, 15, 10, 20, 10), "L101", "KUTU DOKME", 0);
        Product product2 = new Product(4611, "L101-4622-D00000-0109", LocalDateTime.of(2020, 3, 20, 9, 3, 7) , "L102", "GUCSAN", 1);
        Product product3 = new Product(4611, "L101-4622-S00018-0102", LocalDateTime.of(2020, 8, 9, 5, 15, 5) , "L101", "MEBANT", 0);
        Product product4 = new Product(4611, "L101-4611-D00000-1801", LocalDateTime.of(2021, 1, 7, 9, 30, 4) , "L101", "KUTU DOKME", 1);
        Product product5 = new Product(4611, "L038-8622-D00000-0712", LocalDateTime.of(2021, 7, 5, 23, 37, 8) , "L102", "KUTU DOKME", 1);
        Product product6 = new Product(4611, "L101-4622-S00018-0102", LocalDateTime.of(2020, 8, 10, 4, 17, 16) , "L101", "MEBANT", 1);
        Product product7 = new Product(4611, "L101-4622-S00018-0102", LocalDateTime.of(2021, 8, 11, 6, 17, 55) , "L101", "MEBANT", 0);
        Product product8 = new Product(4611, "L101-4611-D00000-1801", LocalDateTime.of(2021, 3, 15, 10, 30, 34) , "L101", "KUTU DOKME", 0);
        Product product9 = new Product(4611, "L101-4611-D00000-1801", LocalDateTime.of(2021, 5, 11, 7, 30, 27) , "L101", "KUTU DOKME", 1);       
        
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        
        System.out.println("Products\n-----");
        for(int i = 0; i < products.size(); i++){
            System.out.println(products.get(i).boxType + " / " + products.get(i).status + " / " + products.get(i).date + " / " +
                               products.get(i).supplierCode + " / " + products.get(i).supplier + " / " + products.get(i).followProduct);
        }
        System.out.println();
        
        int [] frequencyOfProducts = new int [products.size()];  
        for(int i = 0; i < products.size(); i++){
            frequencyOfProducts[i] = 1;
        }
        
        //finding unique products and compare more than one products with their date
        for(int i = 0; i < products.size(); i++){            
            for(int j = 0; j < products.size(); j++){
                if((i!=j) &&(products.get(i).status.equals(products.get(j).status)) && frequencyOfProducts[j] != -1){
                            isSameProduct = true;
                            secondPhase.add(products.get(j));
                            frequencyOfProducts[j] = -1;
                }         
            }
            if(!secondPhase.contains(products.get(i)) && frequencyOfProducts[i] != -1 && isSameProduct){
                        secondPhase.add(products.get(i));
                        frequencyOfProducts[i] = -1;
                        isSameProduct = false;
                        
                        LocalDateTime lastDate = secondPhase.get(0).date;
                        for(int k = 1; k < secondPhase.size(); k++){
                            if(lastDate.isBefore(secondPhase.get(k).date)){
                                lastDate = secondPhase.get(k).date;
                            }
                        }

                        for(int x = 0; x < secondPhase.size(); x++){
                            if(lastDate.isEqual(secondPhase.get(x).date)){
                                finalPhase.add(secondPhase.get(x));
                                break;
                            }
                        }
                        
                        secondPhase.removeAll(products);           
            }     
        }
        
        for(int i = 0; i < frequencyOfProducts.length; i++){
            if(frequencyOfProducts[i] == 1){
                finalPhase.add(products.get(i));
            }
        }

        System.out.println("Unique Products\n--------------");
        for(int i = 0; i < finalPhase.size(); i++){
            System.out.println(finalPhase.get(i).boxType + " / " + finalPhase.get(i).status + " / " + finalPhase.get(i).date + " / " +
                               finalPhase.get(i).supplierCode + " / " + finalPhase.get(i).supplier + " / " + finalPhase.get(i).followProduct);
        }
        
        
        System.out.println("\n1's and 0's\n--------");
        System.out.println("->1's");
        for(int i = 0; i < finalPhase.size(); i++){
            if(finalPhase.get(i).followProduct == 1){
                System.out.println(finalPhase.get(i).boxType + " / " + finalPhase.get(i).status + " / " + finalPhase.get(i).date + " / " +
                               finalPhase.get(i).supplierCode + " / " + finalPhase.get(i).supplier + " / " + finalPhase.get(i).followProduct);
            }
        }
        
        System.out.println("\n->0's");
        for(int i = 0; i < finalPhase.size(); i++){
            if(finalPhase.get(i).followProduct == 0){
                System.out.println(finalPhase.get(i).boxType + " / " + finalPhase.get(i).status + " / " + finalPhase.get(i).date + " / " +
                               finalPhase.get(i).supplierCode + " / " + finalPhase.get(i).supplier + " / " + finalPhase.get(i).followProduct);
            }
        }
        System.out.println();
        
        
        System.out.println("\nSuppliers\n--------");
        int [] suppliersFrequency = new int [finalPhase.size()]; 
        
        for(int i = 0; i < suppliersFrequency.length; i++){
            suppliersFrequency[i] = 1;
        }
        
        List<String> suppliers = new ArrayList<>();
        List<Product> productSuppliers = new ArrayList<>();
        
        for(int i = 0; i < finalPhase.size(); i++){

            for(int j = 0; j < finalPhase.size(); j++){
                if(i != j && finalPhase.get(i).supplier.equals(finalPhase.get(j).supplier) && suppliersFrequency[j] != -1){
                    suppliers.add(finalPhase.get(j).supplier);
                    productSuppliers.add(finalPhase.get(j));
                    suppliersFrequency[j] = -1;
                }
            }
            if(suppliers.contains(finalPhase.get(i).supplier) && suppliersFrequency[i] != -1){
                productSuppliers.add(finalPhase.get(i));
                suppliersFrequency[i] = -1;
            }
        }
        for(int i = 0; i < suppliersFrequency.length; i++){
            if(suppliersFrequency[i] == 1){
                suppliers.add(finalPhase.get(i).supplier);
                productSuppliers.add(finalPhase.get(i));
            }
        }
        
        //printing suppliers
        for(int i = 0; i < suppliers.size(); i++){
            System.out.println(suppliers.get(i));
        }
        
        System.out.println();
        
        for(int i = 0; i < suppliers.size(); i++){
            System.out.println(suppliers.get(i)+ "\n--------");
            for(int j = 0; j < finalPhase.size(); j++){
                
                if(suppliers.get(i).equals(productSuppliers.get(j).supplier)){
                    System.out.println(productSuppliers.get(j).boxType + " / " + productSuppliers.get(j).status + " / " + productSuppliers.get(j).date + " / " +
                               productSuppliers.get(j).supplierCode + " / " + productSuppliers.get(j).supplier + " / " + productSuppliers.get(j).followProduct);
                }
            }
            System.out.println();
        }
        
        
        System.out.println("\nSupplier Codes\n--------");
        int [] supplierCodesFrequency = new int [finalPhase.size()]; 
        
        for(int i = 0; i < supplierCodesFrequency.length; i++){
            supplierCodesFrequency[i] = 1;
        }
        
        List<String> supplierCodes = new ArrayList<>();
        
        for(int i = 0; i < finalPhase.size(); i++){
            for(int j = 0; j < finalPhase.size(); j++){
                if(i != j && finalPhase.get(i).supplierCode.equals(finalPhase.get(j).supplierCode) && supplierCodesFrequency[j] != -1){
                    if(!supplierCodes.contains(finalPhase.get(j).supplierCode)){
                        supplierCodes.add(finalPhase.get(j).supplierCode);
                        supplierCodesFrequency[j] = -1;
                    }
                    
                }
            }
            if(supplierCodes.contains(finalPhase.get(i).supplierCode) && supplierCodesFrequency[i] != -1){
                supplierCodesFrequency[i] = -1;
            }
        }
        
        for(int i = 0; i < supplierCodesFrequency.length; i++){
            if(supplierCodesFrequency[i] == 1){
                supplierCodes.add(finalPhase.get(i).supplierCode);
            }
        }
        
        //printing supplier codes
        for(int i = 0; i < supplierCodes.size(); i++){
            System.out.println(supplierCodes.get(i));
        }
        
        System.out.println();
        
        for(int i = 0; i < supplierCodes.size(); i++){
            System.out.println(supplierCodes.get(i)+ "\n--------");
            for(int j = 0; j < finalPhase.size(); j++){
                
                if(supplierCodes.get(i).equals(finalPhase.get(j).supplierCode)){
                    System.out.println(finalPhase.get(j).boxType + " / " + finalPhase.get(j).status + " / " + finalPhase.get(j).date + " / " +
                               finalPhase.get(j).supplierCode + " / " + finalPhase.get(j).supplier + " / " + finalPhase.get(j).followProduct);
                }
            }
            System.out.println();
        }
  
    }
      
}
