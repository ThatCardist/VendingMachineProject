package App;

import Controller.VendingMachineLibrary;
import DAO.InsufficientFundsException;
import DAO.NoItemInventoryException;
import DAO.VendingMachineDaoImpl;
import UI.MenuView;
import UI.UserIO;
import UI.UserIOImpl;

public class app {
    public static void main(String[] args) throws NoItemInventoryException, InsufficientFundsException {
        UserIO myIo = new UserIOImpl();
        MenuView myView = new MenuView(myIo);
        VendingMachineDaoImpl myDao = new VendingMachineDaoImpl();
        VendingMachineLibrary controller = new VendingMachineLibrary(myDao, myView);
        controller.run();
    }
}
