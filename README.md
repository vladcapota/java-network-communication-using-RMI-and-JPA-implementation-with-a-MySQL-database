This application uses Java SE 8 in order to exemplify network communication between server and client through the use of RMI, while using JPA (EclipseLink implementation) to store the information into a MySQL database. 

For this purpose I have used swing to create a basic interface that would allow multiple clients to add products to an inventory (server). The products are succinctly defined by use of a name and a price.

In order to run the application I have used the following tools:

- Java IDE: NetBeans;

- Web server package: XAMPP;

- Database design tool: MySQL Workbench.

The architecture of the projects is designed following the MVC design pattern as follows:

The server application:
  
- dao
  > the Data Access Object package holds the class that contains the direct operations with the database; 
  > the class receives an EntityManager given as a dependency through the constructor;
  
- entities
  > this package holds the class that describes the object that is to be mapped in the database;

- server
  > implements a Registry Pattern that can contain multiple implementations for remote interfaces;

- service
  > this package contains the class that inherits UnicastRemoteObject and implements the WebService interface;
  > the service class instantiates an EntityManagerFactory in the constructor and is responsible for implementing the 
    use-cases by overriding the methods of the Web Service methods using working instances of the Dto and Dao objects;

The library application:

- dto
  > the Data Transer Object package contains a serializable version of the object that that allows the object to be 
    transferred through a flux;
  > the classes placed in the ‘entities’ package of the server cannot reach the client because of the annotations that mark 
    the classes 
    in that package, which implement the JPA standard; 
  > the client does not use JPA, as he only consumes the services offered by the server; as such, separate classes are needed
    that represent the object and describe precisely what it transfers;
   
 - ws
   > the Web Service package contains the interface that extends the Remote interface and has the role of exposing the 
     services offered by the server for the clients to consume;
 
 The client application:
 
 - controller
   > this package holds a Singleton class that serves to decouple the service call to the server from the graphical interface;
 
 - gui
   > the  package contians the Graphical User Interface that clients initialise in order to define products and add them to 
     the common inventory;
