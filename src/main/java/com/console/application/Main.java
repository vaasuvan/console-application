package com.console.application;

import com.console.application.model.Organization;
import com.console.application.model.Ticket;
import com.console.application.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static String userFileName = "json/users.json";
    private static String organizationFileName = "json/organizations.json";
    private static String ticketsFileName = "json/tickets.json";
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            printConsolePrompt();



            ClassLoader classLoader = new Main().getClass().getClassLoader();

            // user json convert
            File userFile = new File(classLoader.getResource(userFileName).getFile());
            ArrayList<User> users = objectMapper.readValue(userFile,
                    new TypeReference<ArrayList<User>>() {
                    });
            System.out.println(users.toString());

            File organizationFile = new File(classLoader.getResource(organizationFileName).getFile());
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Organization> organizations = mapper.readValue(organizationFile,
                    new TypeReference<ArrayList<Organization>>() {
                    });

            System.out.println(organizations.toString());
            // organization json convert
            File ticketFile = new File(classLoader.getResource(ticketsFileName).getFile());
            ArrayList<Ticket> tickets = objectMapper.readValue(ticketFile,
                    new TypeReference<ArrayList<Ticket>>() {
                    });
            System.out.println(tickets.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void printConsolePrompt(){
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;
        // Display menu graphics
        System.out.println("*****************************************");
        System.out.println("|   SEARCH FROM JSON FILE EXAMPLE      	|");
        System.out.println("*****************************************");
        System.out.println("| Select a Search Options:              |");
        System.out.println("|        1. GET RECORDS SEARCH OPTION   |");
        System.out.println("|        2. GET SEARCHABLE FIELDS       |");
        System.out.println("|        3. EXIT                        |");
        System.out.println("*****************************************");

        READ_MENU = userInput.next();

        // display menu based on user selection
        switch (READ_MENU) {
            case "1":
                printConsoleSearchCriteria();
                break;
            case "2":
//                getById();
                break;
            case "3":
//                checkAvailableUsername();
                break;
            case "4":
                System.exit(0);
                break;
            default:
                System.out.println("INVALID SELECTION");
                break;
        }


    }

    private static void printConsoleSearchCriteria(){
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;
        // Display menu graphics
        System.out.println("*****************************************");
        System.out.println("| Select :                              |");
        System.out.println("|        1. USERS                       |");
        System.out.println("|        2. TICKETS                     |");
        System.out.println("|        3. ORGANIZATIONS               |");
        System.out.println("*****************************************");

        READ_MENU = userInput.next();

        // display menu based on user selection
        switch (READ_MENU) {
            case "1":
                getUserSearchingTerms();
                break;
            case "2":
//                getById();
                break;
            case "3":
//                checkAvailableUsername();
                break;
            default:
                System.out.println("INVALID SELECTION");
                break;
        }

    }

    private static void getUserSearchingTerms(){
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;
        System.out.println("ENTER SEARCH TERM : ");

        READ_MENU = userInput.next();



    }


}
