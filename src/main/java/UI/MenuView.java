package UI;

import DAO.NoItemInventoryException;
import DTO.Item;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class MenuView {
    public MenuView(UserIO myIo) {
    }

    //enum object for displaying coins
    public enum coins{Quarter, Dime, Nickel}
    UserIOImpl io = new UserIOImpl();

    public void displayItems(List<Item> items){
        //loop thorugh for each item and display price and item number
        int counter = 1;
        for(Item i: items){
            System.out.printf("%s. $",counter);
            counter++;
            displayOneItem(i);
            System.out.printf("%n");
        }
    }

    void displayOneItem(Item i){
        System.out.printf("Item: %s, Cost: %s", i.getName(), convertPrice(i.getPrice()));
    }


    public String convertPrice(int n){
        return String.format("%.02f", (float)n/100);
    }

    public int insertCoins(){
        io.print("Insert some money");
        int userChoice = 0;
        int input = 0;
        while(userChoice != 4){
            io.print("1. Quarter");
            io.print("2. Dime");
            io.print("3. Nickel");
            io.print("4. Exit");
            System.out.printf("Current money inserted: %s%n", convertPrice(input));
            userChoice = io.readInt("Select an option to input money");
            switch (userChoice){
                case 1:
                    input += 25;
                    break;
                case 2:
                    input += 10;
                    break;
                case 3:
                    input += 5;
                    break;
                case 4:
                    io.print("Thank you for inserting money");
                    break;
                default:
                    io.print("Invalid input, try again");
            }
        }
        return input;
    }

    public void purchaseSuccessfulBanner(Item item){
        System.out.printf("Now vending %s%n", item.getName());
    }

    public void insufficentFundsBanner(){
        io.print("Unable to purchase, not enough coins");
    }

    public int mainMenu(){
        io.print("1. Insert coins");
        io.print("2. Purchase Item");
        io.print("3. Exit");
        return io.readInt("Select an option");
    }
    public String dispenseCoins(coins coin){
        switch (coin){
            case Quarter:
                return "Now dispensing Quarter";
            case Nickel:
                return "Now dispensing Nickel";
            case Dime:
                return "Now dispensing Dime";
            default:
                return "All change dispensed";
        }
    }
}
