package pizzamvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Data Access Object for pizza orders. Here we use a text file to
 * store the data. This approach should not be used if there is a chance that
 * more than one user might write to the file at the same time. File corruption
 * could occur. This approach would be fine if multiple users only read
 * from a file or if only a single user writes to a file. Because of this a
 * multiuser database approach is often preferred.
 *
 * @author John Phillips
 */
public class DAOPizzaOrder {

    public static void addOrder(PizzaOrder order, String filepath) throws IOException {
        File file = new File(filepath);

        // the FileWriter true keyword indicates appending to end of file
        try (PrintWriter out = new PrintWriter(new FileWriter(file, true))) {
            out.println(order.getEmail() + "|" + order.getSize() + "|" + order.getToppings());
        }
    }

    public static List<PizzaOrder> getOrderList(String filepath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filepath));
        List<PizzaOrder> list = new ArrayList();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
//            System.out.println("line=" + line);
            String[] lineArray = line.split("[|]");
            String email = lineArray[0];
            String size = lineArray[1];
            String toppings = lineArray[2];
            PizzaOrder order = new PizzaOrder(email, size, toppings);
            list.add(order);
        }
//        System.out.println("list=" + list);
        return list;
    }
}
