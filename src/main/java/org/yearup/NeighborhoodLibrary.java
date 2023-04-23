package org.yearup;

import java.util.Scanner;

public class NeighborhoodLibrary
{
    // Create scanner & books array
    static Scanner scanner = new Scanner(System.in);
    static Book[] arrayBooks = new Book[5];

    // Main method
    public static void main(String[] args)
    {
        loadBooks();
        displayMenu();
    }

    // Create 5 books & add them to the books array
    // Made some that are already checked out
    public static void loadBooks()
    {
        Book book1 = new Book(0, "17-4928-40062-37", "Harry Potter and the Sorcerer's Stone", false, "NONE");
        Book book2 = new Book(1, "53-8402-47391-55", "Goosebumps", false, "Billy Jenkins");
        Book book3 = new Book(2, "11-1777-14732-42", "The Lord of the Rings", false, "NONE");
        Book book4 = new Book(3, "99-1823-84392-61", "The Hobbit", false, "David Davidson");
        Book book5 = new Book(4, "57-1665-19340-11", "Think and Grow Rich", false, "NONE");
        arrayBooks[0] = book1;
        arrayBooks[1] = book2;
        arrayBooks[2] = book3;
        arrayBooks[3] = book4;
        arrayBooks[4] = book5;
    }

    // Check out book by ID
    // Doesn't work if book has already been checked out
    public static void checkOutBook()
    {
        int id = 0;
        String name;

        System.out.print("Enter the ID of the book you want to check out: ");
        id = scanner.nextInt();
        scanner.nextLine();

        // If ID is valid
        if(id <= arrayBooks.length - 1) {
            // If book is available
            if (arrayBooks[id].getCheckedOutTo().equals("NONE")) {
                System.out.print("Enter your name: ");
                name = scanner.nextLine();

                // Mark book as checked out
                arrayBooks[id].setCheckedOutTo(name);
                System.out.println();
                System.out.println(name + " checked out " + arrayBooks[id].getTitle() + ".");
            } else {
                // Display message that book is already checked out
                System.out.println();
                System.out.println(arrayBooks[id].getTitle() + " has already been checked out by " + arrayBooks[id].getCheckedOutTo() + ".");
            }
        }
        else
        {
            System.out.println();
            System.out.println("Invalid ID.");
        }
    }

    // Check in book by ID
    // Doesn't work if book is already checked in
    public static void checkInBook()
    {
        int id = 0;

        System.out.print("Enter the ID of the book you want to return: ");
        id = scanner.nextInt();
        scanner.nextLine();

        // If ID is valid
        if(id <= arrayBooks.length - 1) {
            // If book is checked out by someone
            if (!arrayBooks[id].getCheckedOutTo().equals("NONE")) {
                // Mark book as available
                arrayBooks[id].setCheckedOutTo("NONE");
                System.out.println();
                System.out.println(arrayBooks[id].getTitle() + " has been returned.");
            } else {
                // Display message that book is already returned
                System.out.println();
                System.out.println(arrayBooks[id].getTitle() + " is already in the library.");
            }
        }
        else
        {
            System.out.println();
            System.out.println("Invalid ID.");
        }
    }

    // Show books available for check out
    public static void showAvailableBooks()
    {
        String option = "";
        System.out.println("\n");
        System.out.println("AVAILABLE BOOKS:\n");
        System.out.println("ID    ISBN                 Title                                     Checked out to");
        System.out.println("-----------------------------------------------------------------------------------");

        // For each book in array, print ID, ISBN, Title, CheckedOutTo
        for (int i = 0; i < arrayBooks.length; i++)
        {
            if(arrayBooks[i] != null && arrayBooks[i].getCheckedOutTo().equals("NONE")) {
                System.out.printf("%-5d %-20s %-40s %10s", arrayBooks[i].getId(), arrayBooks[i].getIsbn(), arrayBooks[i].getTitle(), arrayBooks[i].getCheckedOutTo());
                System.out.println();
            }
        }

        // While user doesn't want to quit
        while(!option.equalsIgnoreCase("X")) {
            // Display options
            System.out.print("\nWhat do you want to do?\n" +
                    "C) Check out a book\n" +
                    "X) Exit to menu\n");
            System.out.print("Enter an option: ");
            option = scanner.nextLine();

            // Perform based on user input
            if (option.equalsIgnoreCase("C")) {
                checkOutBook();
            } else if (option.equalsIgnoreCase("X")) {
                break;
            } else {
                System.out.println();
                System.out.println("Invalid option.");
            }
        }
    }

    // Show books that are already checked out
    public static void showCheckedOutBooks()
    {
        String option = "";
        System.out.println("\n");
        System.out.println("CHECKED OUT BOOKS:\n");
        System.out.println("ID    ISBN                 Title                                    Checked out to");
        System.out.println("-----------------------------------------------------------------------------------");

        // For each book in array, print ID, ISBN, Title, CheckedOutTo
        for (int i = 0; i < arrayBooks.length; i++)
        {
            if(arrayBooks[i] != null && !arrayBooks[i].getCheckedOutTo().equals("NONE")) {
                System.out.printf("%-5d %-20s %-40s %10s", arrayBooks[i].getId(), arrayBooks[i].getIsbn(), arrayBooks[i].getTitle(), arrayBooks[i].getCheckedOutTo());
                System.out.println();
            }
        }

        // While user doesn't want to quit
        while(!option.equalsIgnoreCase("X")) {
            System.out.print("\nWhat do you want to do?\n" +
                    "C) Return a book\n" +
                    "X) Exit to menu\n");
            System.out.print("Enter an option: ");
            option = scanner.nextLine();

            // Perform based on user input
            if (option.equalsIgnoreCase("C")) {
                checkInBook();
            } else if (option.equalsIgnoreCase("X")) {
                break;
            } else {
                System.out.println();
                System.out.println("Invalid option.");
            }
        }
    }

    // Display main menu
    public static void displayMenu()
    {
        int option = 0;

        while(option != 3)
        {
            // Display menu
            System.out.println();
            System.out.println("*-----------------*");
            System.out.println("*----MAIN-MENU----*");
            System.out.println("*-----------------*");
            System.out.println();
            System.out.println("What do you want to do?\n" +
                    "1) Show available books\n" +
                    "2) Show checked out books\n" +
                    "3) Quit");
            System.out.print("Enter an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            // Perform based on user input
            switch(option)
            {
                case 1:
                    // show available books
                    showAvailableBooks();
                    break;
                case 2:
                    // show checked out books
                    showCheckedOutBooks();
                    break;
                case 3:
                    System.out.println("\nQuitting...");
                    break;
                default:
                    System.out.println("\nInvalid option.");
                    break;
            }
        }
    }

}