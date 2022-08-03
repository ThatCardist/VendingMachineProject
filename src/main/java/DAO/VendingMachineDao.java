package DAO;

import DTO.Item;

import java.util.List;

public interface VendingMachineDao {
    void addItem(String name, int Price, int Stock);
    void changeItemStock(Item item, int numAdded);
    void readStock() throws NoItemInventoryException;
    void writeStock() throws NoItemInventoryException;
    List<Item> giveValidStock();
    boolean canBuy(int curMoney, Item item);

}
