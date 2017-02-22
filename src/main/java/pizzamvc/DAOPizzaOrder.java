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
 * This Data Access Object will append a pizza order to a text file.
 *
 * @author John Phillips
 */
public class DAOPizzaOrder {

    public static void addOrder(PizzaOrder order, String filepath) throws IOException {
        File file = new File(filepath);

        // handle situation where toppings might be null
        String myToppings = order.getToppings();
        StringBuilder sb = new StringBuilder("");
        if (myToppings != null && myToppings.length() > 0) {
            sb.append(myToppings);
        }

        // the FileWriter true keyword indicates appending to end of file
        try (PrintWriter out = new PrintWriter(new FileWriter(file, true))) {
            out.println(order.getEmail() + "|" + order.getSize() + "|" + sb.toString());
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
