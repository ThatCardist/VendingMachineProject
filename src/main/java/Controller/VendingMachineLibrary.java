package Controller;

import DAO.InsufficientFundsException;
import DAO.VendingMachineDaoImpl;
import DTO.Item;
import UI.MenuView;
import UI.UserIO;
import UI.UserIOImpl;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineLibrary {
    private UserIO io = new UserIOImpl();
    private MenuView view;
    private VendingMachineDaoImpl dao;

    public VendingMachineLibrary(VendingMachineDaoImpl dao, MenuView view){
        this.dao = dao;
        this.view = view;
    }
    public void run() throws InsufficientFundsException {
        int menuSelection = 0;
        int insertedMoney = 0;
        List<Item> allItems;
        allItems = dao.giveValidStock();
        view.displayItems(allItems);
        insertedMoney += view.insertCoins();
        while(menuSelection != 3){
            menuSelection = view.mainMenu();
            switch (menuSelection){
                //Insert more money
                case 1:
                    insertedMoney += view.insertCoins();
                    break;
                //purchase items
                case 2:
                    insertedMoney -= sell(allItems, insertedMoney);
                //exit
                case 3:
                    exit(insertedMoney);
                    break;
            }
        }
    }
    int sell(List<Item> items, int money) throws InsufficientFundsException {
        view.displayItems(items);
        int itemSelected = io.readInt("Enter an item number to purchace") - 1;
        if(items.get(itemSelected).getPrice() > money){
            throw new InsufficientFundsException("Not Enough Money");
        }else{
            view.purchaseSuccessfulBanner(items.get(itemSelected));
            dao.changeItemStock(items.get(itemSelected),-1);
            return money - items.get(itemSelected).getPrice();
        }
    }
    void exit(int money){
        //dispense change
        while(money > 0){
            if(money > 25){
                view.dispenseCoins(MenuView.coins.Quarter);
            }else if(money > 10){
                view.dispenseCoins(MenuView.coins.Dime);
            }else{
                view.dispenseCoins(MenuView.coins.Nickel);
            }
        }
    }
}
