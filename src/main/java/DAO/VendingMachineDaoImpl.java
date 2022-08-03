package DAO;

import DTO.Item;
import UI.MenuView;
import UI.UserIO;
import UI.UserIOImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineDaoImpl implements VendingMachineDao{
    List<Item> allItems = new ArrayList<>();
    String stockFile = "stock_file.txt";
    public static final String DELIMITER = "::";

    public VendingMachineDaoImpl() throws NoItemInventoryException {
        readStock();
    }

    private Item unmarshallItem (String itemAsText){
        String[] itemTokens = itemAsText.split(DELIMITER);
        return new Item(itemTokens[0], Integer.parseInt(itemTokens[1]), Integer.parseInt(itemTokens[2]));
    }
    @Override
    public void addItem(String name, int price, int stock) {
        allItems.add(new Item(name,stock, price));
    }

    @Override
    public void changeItemStock(Item item, int numAdded) {
        Item tempItem = new Item(item.getName(), item.getStock() + numAdded, (int)item.getPrice()* 100);
        allItems.set(allItems.indexOf(tempItem), item);
    }

    @Override
    public void readStock() throws NoItemInventoryException {
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(stockFile)));
        } catch (FileNotFoundException e) {
            throw new NoItemInventoryException("Unable to read file");
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Item currentItem;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentItem = unmarshallItem(currentLine);
            System.out.printf("%s%n", currentItem.getName());
            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            allItems.add(currentItem);
        }
        // close scanner
        scanner.close();
    }

    @Override
    public void writeStock() throws NoItemInventoryException {//writes stock back to file
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
        PrintWriter out;
        //create file of old library and delete it and make a new file for the new library
        File oldLibrary = new File(stockFile);
        oldLibrary.delete();
        new File(stockFile);
        //crete writer for new file
        try {
            out = new PrintWriter(new FileWriter(stockFile));
        } catch (IOException e) {
            throw new NoItemInventoryException("Could not save inventory data.");
        }
        //create string to write to file
        String itemAsText;
        //put movies into new doc, delete old one and rename new one

        for (Item curItem : allItems) {
            // turn a Student into a String
            itemAsText = curItem.getName() + "::" + curItem.getStock() + "::" + curItem.getPrice();
            // write the Student object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public List<Item> giveValidStock() {
        List<Item> validStock = new ArrayList<>();
        for (Item i:allItems) {
            if(i.getStock() > 0){
                validStock.add(i);
            }
        }
        return validStock;
    }

    @Override
    public boolean canBuy(int curMoney, Item item) {
        if(curMoney > item.getPrice() && item.getStock() > 0){
            return true;
        }else{
            return false;
        }
    }
}
