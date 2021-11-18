
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mucah
 */
public class Product {
    public int boxType;
    public String status;
    public LocalDateTime date;
    public String supplierCode;
    public String supplier;
    public int followProduct;
    
    public Product(int boxType, String status, LocalDateTime date, String supplierCode, String supplier, int followProduct){
        this.boxType = boxType;
        this.status = status;
        this.date = date;   
        this.supplierCode = supplierCode;
        this.supplier = supplier;
        this.followProduct = followProduct;

    }
    

    
}
