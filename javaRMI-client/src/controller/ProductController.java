/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.ProductDto;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ws.ProductWebService;

/**
 *
 * @author Vlad
 */
public class ProductController {
    
    private Registry registry;
    private ProductWebService service;
    
    private ProductController(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4321);
            service = (ProductWebService) registry.lookup("registry");
            
        } catch (Exception ex) {
            
            ex.printStackTrace();
        }
    }
    
    private static final class SingletonHolder{
        private static final ProductController INSTANCE = new ProductController();
    }
    
    public static ProductController getInstance(){
        return SingletonHolder.INSTANCE;
    }
    
    public void addProduct(String name, double price) throws RemoteException{
        
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setPrice(price);
        service.addProduct(productDto);
        
    }
    
    public List<ProductDto> getProducts() throws RemoteException{
        
        return service.getProducts();
        
    }
    
    public void deleteProduct(ProductDto productDto) throws RemoteException{
        
        service.deteleProduct(productDto);
        
    }
    
}
