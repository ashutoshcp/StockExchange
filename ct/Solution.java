package ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Pair;

public class Solution {
    private BufferedReader bufferedReader;
    public static void main(String[] args) throws IOException {
        new Solution().process();
    }

    private void process() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        PropertyProcessor propertyProcessor = new PropertyProcessor();
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        listInstructions();
        char c = 'Y';
        while (c != 'N') {
            String l = bufferedReader.readLine();
            c = l.charAt(0);
            switch (c) {
                case 'N':
                    System.out.println("you stopped");
                    System.exit(1);
                    break;
                case 'A':
                    System.out.println("Enter user name: ");
                    String name = bufferedReader.readLine();
                    String message;
                    try {
                        message = userProcessor.addUser(name);
                    } catch (Exception ex) {
                        message = ex.getMessage();
                    }
                    System.out.println(message);
                    break;
                case 'B':
                    System.out.println("Register Property: ");
                    String[] input = bufferedReader.readLine().trim().split(",");
                    if (input.length != 7) {
                        System.out.println("Invalid input for property");
                    } else {
                        Property p = preprocessInput(input);
                        propertyProcessor.listProperty(p, input[0]);
                    }
                    break;
                case 'C':
                    String[] search = bufferedReader.readLine().split(",");
                    propertyProcessor.search(search);
                    break;
                case 'D':
                    String[] shortList = bufferedReader.readLine().trim().split(",");
                    userProcessor.shortListProperty(Integer.parseInt(shortList[0].trim()), shortList[1].trim());
                    break;
                case 'E':
                    String userName = bufferedReader.readLine().trim();
                    userProcessor.viewShortlistedProperties(userName);
                    break;
                case 'F':
                    break;
                case 'G':
                    // pId, Unvavailal, avail
                    break;
                default:
                    System.out.println("Please enter listed choices only");
                    break;
            }
            listInstructions();
        }
    }

    private Property preprocessInput(String[] input) {
        Property property = new Property(input);
        return property;
    }

    private void listInstructions() {
        System.out.println("Press N to stop");
        System.out.println("Press A to Register user");
        System.out.println("Press B to Register property");
        System.out.println("Press C to Search property");
        System.out.println("Press D to Shortlist property");
        System.out.println("Press E to View shortlisted");
        System.out.println("Press F to Search");
        System.out.println("Press G to update status");
    }
}
