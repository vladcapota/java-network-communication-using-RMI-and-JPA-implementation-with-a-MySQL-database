/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.ProductService;

/**
 *
 * @author Vlad
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Registry registry = LocateRegistry.createRegistry(4321);
            registry.rebind("registry", new ProductService());
        }catch(RemoteException ex){
            ex.printStackTrace();
        }
    }
    
}
