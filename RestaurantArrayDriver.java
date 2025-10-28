import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;             

public class RestaurantArrayDriver {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);   // Create a Scanner to read user input from the console

        System.out.print("Enter file name: "); // Prompt the user for the file name
        String filename = s.nextLine();        // Store the file name they type in

        File file = new File(filename);        // Create a File object that represents the given file

        try {
            // Scanner that reads from the file
            Scanner fileScanner = new Scanner(file);

            //an array that can hold up to 30 Restaurant objects
            Restaurant[] restaurantArr = new Restaurant[30];
            int index = 0; // to Keep track of how many Restaurants added

            // Read the file line by line until there’s no more data
            while (fileScanner.hasNext()) { 
                String name = fileScanner.next();  // Read the String (restaurant name)
                int price = fileScanner.nextInt(); // Read the integer (restaurant price)

                // Create a new Restaurant object using the data we just read
                restaurantArr[index] = new Restaurant(name, price);
                index++; // Move to the next slot in the array
            }
            fileScanner.close(); // Close the file scanner

            // Print out all Restaurant objects we read in (up to index)
            for (int i = 0; i < index; i++) {
                System.out.println(restaurantArr[i]);
            }
            
            System.out.println("overallAvg: " + overallAvg(restaurantArr));
            System.out.println("count(\"malawax\",6): " + count(restaurantArr, new Restaurant("malawax", 6)));
            System.out.println("groupAvg(\"rice\"): " + groupAvg(restaurantArr, "rice"));
            System.out.println("lessThan(..., 10) size: " + lessThan(restaurantArr, 10).length);
            groupEdit(restaurantArr, "fries", 2);
            System.out.println("After groupEdit(\"fries\", +2):");
            for (Restaurant r : restaurantArr) {
                if (r != null && r.getName().equalsIgnoreCase("fries")) {
                    System.out.println(r);
                }
            }

        } catch (FileNotFoundException e) {
            // If the file doesn’t exist, print an error message
            System.out.println("File not found " + filename);
        }

        s.close(); // Close the console Scanner
    }

    // ---------------- Part 3 Methods ---------------- //

    // Calculates the overall average price of all Restaurants in the array
    public static int overallAvg(Restaurant[] a) {
        int sum = 0, count = 0; // Keep running total and count of valid items
        for (Restaurant r : a) {           // Loop through each element
            if (r != null) {               // Only consider non-null objects
                sum += r.getPrice();       // Add this restaurant’s price to sum
                count++;                   // Count how many we’ve added
            }
        }
        // Return average (guard against divide by zero if array has no items)
        return (count == 0) ? 0 : sum / count;
    }

    // Counts how many Restaurants in the array are equal to a given Restaurant object
    public static int count(Restaurant[] a, Restaurant o) {
        int cnt = 0; // Counter for matches
        for (Restaurant r : a) {
            if (r != null && r.equals(o)) { // If this object exists and equals o
                cnt++;                      // Increase the counter
            }
        }
        return cnt; // Return total number of matches
    }

    // Calculates the average price of all Restaurants whose name matches the given string
    public static int groupAvg(Restaurant[] a, String s) {
        int sum = 0, cnt = 0; // Running total and count of matches
        for (Restaurant r : a) {
            if (r != null && r.getName().equalsIgnoreCase(s)) {
                sum += r.getPrice(); // Add this restaurant’s price
                cnt++;                // Count it
            }
        }
        // Return average or 0 if no matches
        return (cnt == 0) ? 0 : sum / cnt;
    }

    // Returns a new array containing only Restaurants with a price less than i
    public static Restaurant[] lessThan(Restaurant[] a, Integer i) {
        int cnt = 0; // Count how many match
        for (Restaurant r : a) {
            if (r != null && r.getPrice() < i) cnt++;
        }
        // Create a new array just big enough to hold all matches
        Restaurant[] result = new Restaurant[cnt];
        int k = 0; // Index for result array
        for (Restaurant r : a) {
            if (r != null && r.getPrice() < i) {
                result[k++] = r; // Copy this restaurant into result
            }
        }
        return result; // Return the new array
    }

    // Adds n to the price of every Restaurant whose name matches s
    public static void groupEdit(Restaurant[] a, String s, Integer n) {
        for (Restaurant r : a) {
            if (r != null && r.getName().equalsIgnoreCase(s)) {
                // Update the Restaurant’s price by adding n
                r.setPrice(r.getPrice() + n);
            }
        }
    }
}