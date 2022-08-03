package DAO;

public class NoItemInventoryException extends Exception{
    public NoItemInventoryException(String msg){
        System.out.printf("%s",msg);
    }
}
