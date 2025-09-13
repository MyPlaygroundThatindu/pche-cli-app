import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "👷 Welcome to DEP-11 Phase-1 Assignment Marks Collector";
        final String ADD_ASSIGNMENT_MARKS = "➕  Add Assignment Marks";
        final String PRINT_DETAILS = "🖨  Print Assignment Marks";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String screen = DASHBOARD;
        String[][] students = new String[][] {
                { "DEP-01", "Kasun", "75" },
                { "DEP-02", "Nuwan", "32.25" },
                { "DEP-03", "Ruwan", "65" },
                { "DEP-04", "Upul", "100" },
                { "DEP-05", "Supun", "58" },
                { "DEP-06", "Tharindu", "88.21" }
        };

        do {
            final String APP_TITLE = String.format("%s%s%s",
                    COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch (screen) {
                case DASHBOARD: {
                    System.out.println("\t[1]. Add Assignment Marks");
                    System.out.println("\t[2]. Print Assignment Marks");
                    System.out.println("\t[3]. Exit");
                    System.out.print("\n\t Enter an option to continue: ");

                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option) {
                        case 1:
                            screen = ADD_ASSIGNMENT_MARKS;
                            break;
                        case 2:
                            screen = PRINT_DETAILS;
                            break;
                        case 3:
                            System.out.println(CLEAR);
                            System.exit(0);
                    }
                    break;
                }
                case ADD_ASSIGNMENT_MARKS: {
                    String id;
                    String name;
                    double marks;
                    boolean valid;

                    /* Let's Validate ID */
                    idValidation: do {
                        valid = true;
                        System.out.print("\tEnter Student ID: ");
                        id = SCANNER.nextLine().strip();

                        /* Empty */
                        if (id.isEmpty()) {
                            valid = false;
                            System.out.printf(ERROR_MSG, "ID Can't be empty");
                            continue;
                        }

                        /* Format */
                        if (!id.startsWith("DEP-") || id.length() != 6) {
                            valid = false;
                            System.out.printf(ERROR_MSG, "Invalid ID format");
                            continue;
                        } else {
                            // DEP-01 => 01
                            String numberPart = id.substring(5);
                            for (int i = 0; i < numberPart.length(); i++) {
                                if (!Character.isDigit(numberPart.charAt(i))) {
                                    valid = false;
                                    System.out.printf(ERROR_MSG, "Invalid ID format");
                                    continue idValidation;
                                }
                            }
                        }

                        /* Already Exists */
                        for (int row = 0; row < students.length; row++) {
                            if (students[row][0].equals(id)) {
                                valid = false;
                                System.out.printf(ERROR_MSG, "Student ID already exists");
                                continue idValidation;
                            }
                        }
                    } while (!valid);

                    /* Let's Validate Name */
                    nameValidation: do {
                        valid = true;
                        System.out.print("\tEnter Student Name: ");
                        name = SCANNER.nextLine().strip();

                        /* Empty */
                        if (name.isEmpty()) {
                            valid = false;
                            System.out.printf(ERROR_MSG, "Name can't be empty");
                            continue;
                        }

                        /* Format */
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) ||
                                    Character.isSpaceChar(name.charAt(i)))) {
                                valid = false;
                                System.out.printf(ERROR_MSG, "Invalid Name");
                                continue nameValidation;
                            }
                        }
                    } while (!valid);

                    /* Let's Validate Marks */
                    do {
                        valid = true;
                        System.out.print("\tEnter Student Marks: ");
                        marks = SCANNER.nextDouble();
                        SCANNER.nextLine();

                        /* Range */
                        if (marks < 0 || marks > 100) {
                            valid = false;
                            System.out.printf(ERROR_MSG, "Invalid Marks");
                        }
                    } while (!valid);

                    /*
                     * Now we have to store this new assignment marks,
                     * So let's scale the `student` array by one
                     */
                    String[][] tempStudents = new String[students.length + 1][3];

                    /*
                     * Let's copy previous recrods from old `student` array to new `student` array
                     */
                    for (int i = 0; i < students.length; i++) {
                        tempStudents[i] = students[i];
                    }

                    /* Let's add new assignment marks */
                    tempStudents[tempStudents.length - 1][0] = id;
                    tempStudents[tempStudents.length - 1][1] = name;
                    tempStudents[tempStudents.length - 1][2] = marks + "";

                    /* Let's swap arrays' memory locations */
                    students = tempStudents;

                    System.out.println();
                    System.out.printf(SUCCESS_MSG, String.format("%s:%s added successfully \n", id, name));
                    System.out.print("\tDo you want to continue adding marks? (Y/n)");
                    if (!SCANNER.nextLine().toUpperCase().strip().equals("Y"))
                        screen = DASHBOARD;
                    break;
                }
                case PRINT_DETAILS: {
                    /* Let's sort the `students` array via a sorting algorithm */
                    for (int i = 0; i < students.length; i++) {
                        String[] student = students[0];
                        int index = 0;

                        for (int k = 1; k < students.length - i; k++) {
                            if (Double.valueOf(students[k][2]) > Double.valueOf(student[2])) {
                                student = students[k];
                                index = k;
                            }
                        }

                        String[] temp = students[students.length - i - 1];
                        students[students.length - i - 1] = student;
                        students[index] = temp;
                    }

                    /* Let's print the table header */
                    final String LINE = "\t+".concat("-".repeat(7)).concat("+").concat("-".repeat(15)).concat("+")
                            .concat("-".repeat(20)).concat("+");
                    System.out.println(LINE);
                    System.out.printf("\t|%-7s|%-15s|%-20s| \n", "ID", "NAME", "MARKS");
                    System.out.println(LINE);
                    /* Let's print the table body */
                    for (int i = students.length - 1; i >= 0; i--) {

                        String grade;
                        String color;
                        double marks = Double.valueOf(students[i][2]);
                        if (marks >= 75) {
                            grade = "A";
                            color = "\033[44m";
                        } else if (marks >= 65) {
                            grade = "B";
                            color = "\033[42m";
                        } else if (marks >= 55) {
                            grade = "C";
                            color = "\033[43m";
                        } else {
                            grade = "F";
                            color = "\033[41m";
                        }

                        int spaceCount = (int) (marks / 100 * 8);
                        String progress = String.format(" [%-17s]",
                                String.format("%s%s%s", color, " ".repeat(spaceCount), RESET));

                        grade += String.format(" %6.2f", marks).concat("%").concat(progress);

                        System.out.printf("\t|%-7s|%-15s|%-29s| \n",
                                students[i][0],
                                students[i][1],
                                grade);
                    }
                    System.out.println(LINE);
                    System.out.print("\n\tDo you want to go back? (Y/n) ");
                    if (SCANNER.nextLine().toUpperCase().strip().equals("Y")) screen = DASHBOARD;
                    break;
                }
            }
        } while (true);
    }
}