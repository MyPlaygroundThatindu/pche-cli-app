import java.util.Scanner;

public class Excersice {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        /*
        * Create a CLI application to manage (create, display, edit) customer information
        * which will be used by a bank.
        * Customer details required are,
        * Name
        * City
        * Telephone
        * AC number
        * */
        String[][] customerDetails = new String[0][4];

        /*
        *
        *
        * */


        boolean appRun = true;
        while (appRun) {

            int option = -1;

            while (option < 1 || option > 5) {
                System.out.println("\nWelcome to PCHE Bank!");
                System.out.println("Please select an option: ");
                System.out.println("1. Add a new customer");
                System.out.println("2. Display all customers");
                System.out.println("3. Update customer details");
                System.out.println("4. Delete a customer");
                System.out.println("5. Exit\n");

                System.out.print("Selected option : ");

                try {
                    option = scanner.nextInt();
                } catch (Exception e) {
                    option = -1;
                    System.out.println("Invalid string input. Program will terminate now. Please try again.");
                    throw new RuntimeException(e.getMessage());
                }

                if (option < 1 || option > 5) {
                    System.out.println("Invalid option. Select an option between 1 and 4");
                }
            }

            switch (option) {
                case 1:
                    System.out.println("Enter new Customer Details\n");

                    boolean valid;
                    String customerName;
                    String customerCity;

                    do {
                        valid = false;
                        System.out.print("Enter customer name: ");
                        customerName = scanner.next().trim();

                        if(customerName.isEmpty() || !customerName.matches("^[a-zA-Z ]{2,}$")){
                            System.out.print("Invalid input. Please use alphabetical characters only.\n\n");
                        } else {
                            valid = true;
                        }
                    }while (!valid);

                    do {
                        valid = false;
                        System.out.print("Enter customer city: ");
                        customerCity = scanner.next().trim();

                        if(customerCity.isEmpty() || !customerCity.matches("^[a-zA-Z ]+$")){
                            System.out.print("Invalid input. Please use alphabetical characters, numbers and \"-\" only.\n\n");
                        } else {
                            valid = true;
                        }
                    }while (!valid);

                    System.out.print("Enter customer phone number: ");
                    String customerPhoneNumber = scanner.next();
                    System.out.print("Enter customer account number: ");
                    String customerAccountNumber = scanner.next();

                    String[][] tempArr = new String[customerDetails.length+1][4];
                    for (int i = 0; i < customerDetails.length; i++) {
                        tempArr[i][0] = customerDetails[i][0];
                        tempArr[i][1] = customerDetails[i][1];
                        tempArr[i][2] = customerDetails[i][2];
                        tempArr[i][3] = customerDetails[i][3];
                    }

                    tempArr[tempArr.length-1][0] = customerName;
                    tempArr[tempArr.length-1][1] = customerCity;
                    tempArr[tempArr.length-1][2] = customerPhoneNumber;
                    tempArr[tempArr.length-1][3] = customerAccountNumber;

                    customerDetails = tempArr;
                    System.out.println("\nCustomer details saved successfully\n");
                    break;
                case 2:
                    System.out.println("Display Customer Details\n");
                    System.out.printf("\t|%-7s|%-15s|%-15s|%-15s|%-15s\n", "SN", "Name", "City", "Phone Number", "Account Number");
                    for (int i = 0; i < customerDetails.length; i++) {
                        if(customerDetails[i][0] != null && customerDetails[i][1] != null && customerDetails[i][2] != null && customerDetails[i][3] != null) {
                            System.out.printf("\t|%-7s|%-15s|%-15s|%-15s|%-15s\n", i + 1, customerDetails[i][0], customerDetails[i][1], customerDetails[i][2], customerDetails[i][3]);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Update Customer Details\n\n");
                    String customerSN;
                    do {
                        valid = false;
                        System.out.print("Enter customer SN to be deleted: ");
                        customerSN = scanner.next().trim();

                        if(customerSN.isEmpty() || !customerSN.matches("^[0-9]+$")){
                            System.out.print("Invalid input. Please enter a positive number only.\n\n");
                        } else if (Integer.parseInt(customerSN) > customerDetails.length) {
                            System.out.print("Invalid input. Please enter a number in valid range.\n\n");
                        } else {
                            valid = true;
                        }
                    }while (!valid);
                    //Allow user to input new customer details
                    do {
                        valid = false;
                        System.out.print("Enter customer new name: ");
                        customerName = scanner.next().trim();

                        if(customerName.isEmpty() || !customerName.matches("^[a-zA-Z ]{2,}$")){
                            System.out.print("Invalid input. Please use alphabetical characters only.\n\n");
                        } else {
                            valid = true;
                        }
                    }while (!valid);

                    do {
                        valid = false;
                        System.out.print("Enter customer new city: ");
                        customerCity = scanner.next().trim();

                        if(customerCity.isEmpty() || !customerCity.matches("^[a-zA-Z ]+$")){
                            System.out.print("Invalid input. Please use alphabetical characters, numbers and \"-\" only.\n\n");
                        } else {
                            valid = true;
                        }
                    }while (!valid);

                    System.out.print("Enter customer new phone number: ");
                    customerPhoneNumber = scanner.next();
                    System.out.print("Enter customer new account number: ");
                    customerAccountNumber = scanner.next();

                    //Add new details to the relavant SN/ index
                    int editIndex = Integer.parseInt(customerSN)-1;
                    customerDetails[editIndex][0] = customerName;
                    customerDetails[editIndex][1] = customerCity;
                    customerDetails[editIndex][2] = customerPhoneNumber;
                    customerDetails[editIndex][3] = customerAccountNumber;

                    break;
                case 4:
                    System.out.println("Delete a customer\n");
                    do {
                        valid = false;
                        System.out.print("Enter customer SN to be deleted: ");
                        customerSN = scanner.next().trim();

                        if(customerSN.isEmpty() || !customerSN.matches("^[0-9]+$")){
                            System.out.print("Invalid input. Please enter a positive number only.\n\n");
                        } else if (Integer.parseInt(customerSN) > customerDetails.length) {
                            System.out.print("Invalid input. Please enter a number in valid range.\n\n");
                        } else {
                            valid = true;
                        }
                    }while (!valid);

                    String[][] temp = new String[customerDetails.length][4];
                    for (int i = 0; i < customerDetails.length; i++) {
                        if (i == (Integer.parseInt(customerSN)-1)) {
                            continue;
                        }
                        temp[i][0] = customerDetails[i][0];
                        temp[i][1] = customerDetails[i][1];
                        temp[i][2] = customerDetails[i][2];
                        temp[i][3] = customerDetails[i][3];
                    }
                    customerDetails = temp;
                    System.out.println("\nCustomer details deleted successfully\n");
                    break;
                case 5:
                    System.out.println("Exiting from application\n");
                    appRun = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }
}
