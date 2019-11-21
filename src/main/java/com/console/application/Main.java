package com.console.application;

import com.console.application.model.Organization;
import com.console.application.model.Ticket;
import com.console.application.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This the root class all call methods start here
 * and all search functions implemented here.
 */
public class Main {

    private static String userFileName = "json/users.json";
    private static String organizationFileName = "json/organizations.json";
    private static String ticketsFileName = "json/tickets.json";
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        ClassLoader classLoader = new Main().getClass().getClassLoader();

        printConsolePrompt(classLoader);
    }

    /**
     * this will print user console prompt
     *
     * @param classLoader
     */
    private static void printConsolePrompt(ClassLoader classLoader) {
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
                printConsoleSearchCriteria(classLoader);
                break;
            case "2":
                printSearchableFiels(classLoader);
                break;
            case "3":
                System.exit(0);
                break;
            default:
                System.out.println("INVALID SELECTION TRY AGAIN !");
                printConsolePrompt(classLoader);
                break;
        }
    }

    /**
     * this will allow user to select the entity intends to search
     *
     * @param classLoader
     */
    private static void printConsoleSearchCriteria(ClassLoader classLoader) {
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;
        // Display menu graphics
        System.out.println();
        System.out.println("*****************************************");
        System.out.println("| Select :                              |");
        System.out.println("|        1. ORGANIZATIONS               |");
        System.out.println("|        2. USERS                       |");
        System.out.println("|        3. TICKETS                     |");
        System.out.println("*****************************************");
        System.out.println();
        READ_MENU = userInput.next();

        // display menu based on user selection
        switch (READ_MENU) {
            case "1":
                getOrganizationSearchingTerms(classLoader);
                break;
            case "2":
                getUsersSearchingTerms(classLoader);
                break;
            case "3":
                getTicketsSearchingTerms(classLoader);
                break;
            default:
                System.out.println("INVALID SELECTION TRY AGAIN !");
                printConsoleSearchCriteria(classLoader);
                break;
        }
    }


    private static void printSearchableFiels(ClassLoader classLoader){

        File organizationFile = new File(classLoader.getResource(organizationFileName).getFile());
        ArrayList<Map<String,Object>> organizations = null;
        try {
            organizations = mapper.readValue(organizationFile,
                    new TypeReference<ArrayList<Map<String,Object>>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Set<String>> orgList = organizations != null ? organizations.stream().map(m -> m.keySet()).collect(Collectors.toList()) : null;
        System.out.println("*******ORGANIZATION SEARCHABLE FIELDS***********");
        Objects.requireNonNull(orgList).get(0).forEach(System.out::println);
        System.out.println("************************************************");

        File userFile = new File(classLoader.getResource(userFileName).getFile());
        ArrayList<Map<String,Object>> users = null;
        try {
            users = mapper.readValue(userFile,
                    new TypeReference<ArrayList<Map<String,Object>>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Set<String>> userList = users != null ? users.stream().map(m -> m.keySet()).collect(Collectors.toList()) : null;
        System.out.println("**********USER SEARCHABLE FIELDS****************");
        Objects.requireNonNull(userList).get(0).forEach(System.out::println);
        System.out.println("************************************************");

        File ticketFile = new File(classLoader.getResource(ticketsFileName).getFile());
        ArrayList<Map<String,Object>> tickets = null;
        try {
            tickets = mapper.readValue(ticketFile,
                    new TypeReference<ArrayList<Map<String,Object>>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Set<String>> ticketList = tickets != null ? tickets.stream().map(m -> m.keySet()).collect(Collectors.toList()) : null;
        System.out.println("**********TICKET SEARCHABLE FIELDS**************");
        Objects.requireNonNull(ticketList).get(0).forEach(System.out::println);
        System.out.println("************************************************");

    }

    /**
     * related to organizations entity search
     *
     * @param classLoader
     */
    private static void getOrganizationSearchingTerms(ClassLoader classLoader) {

        ArrayList<Organization> organizations = getOrganizations(classLoader);
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;
        System.out.println("ENTER ORGANIZATION ID : ");

        READ_MENU = userInput.next();
        Integer orgId = Integer.parseInt(READ_MENU);
        List<Organization> organizationList = organizations.stream().filter(organization -> organization.get_id().equals(orgId)).collect(Collectors.toList());
        if (organizationList.size() > 0) {
            organizationList.forEach((organization) -> {
                System.out.format("%-15s - %4d\n", "ID", organization.get_id());
                System.out.format("%-15s - %4s\n", "URL", organization.getUrl());
                System.out.format("%-15s - %4s\n", "EXTERNAL_ID ", organization.getExternal_id());
                System.out.format("%-15s - %4s\n", "NAME ", organization.getName());
                System.out.format("%-15s - %4s\n", "DOMAIN_NAMEs ", organization.getDomain_names());
                System.out.format("%-15s - %4s\n", "CREATED_AT ", organization.getCreated_at());
                System.out.format("%-15s - %4s\n", "DETAILS ", organization.getDetails());
                System.out.format("%-15s - %4s\n", "SHARE_TICKETS ", organization.getShared_tickets());
                System.out.format("%-15s - %4s\n", "TAGS ", organization.getTags());
                System.out.format("%-15s - %4s\n", "USERNAME ", getUserNameByOrgId(classLoader, organization.get_id()));
                System.out.format("%-15s - %4s\n", "TICKET_SUBJECTS ", getTicketSubjectBtOrgId(classLoader, organization.get_id()));
            });
        } else {
            System.out.printf("******NO RESULT FOUND**********");
        }
        printConsoleSearchCriteria(classLoader);
    }

    private static List<String> getUserNameByOrgId(ClassLoader classLoader, Integer orgId) {
        ArrayList<User> users = getUsers(classLoader);
        List<User> userList = users.stream().filter(user -> orgId.equals(user.getOrganization_id())).collect(Collectors.toList());
        List<String> stringList = userList.stream().map(user -> user.getName()).collect(Collectors.toList());
        return stringList;
    }

    private static List<String> getTicketSubjectBtOrgId(ClassLoader classLoader, Integer orgId) {
        ArrayList<Ticket> tickets = getTickets(classLoader);
        List<Ticket> ticketList = tickets.stream().filter(ticket -> orgId.equals(ticket.getOrganization_id())).collect(Collectors.toList());
        List<String> stringList = ticketList.stream().map(ticket -> ticket.getSubject()).collect(Collectors.toList());
        return stringList;
    }

    /**
     * related to user entity search
     *
     * @param classLoader
     */
    private static void getUsersSearchingTerms(ClassLoader classLoader) {
        ArrayList<User> users = getUsers(classLoader);
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;
        System.out.println("ENTER USER ID : ");

        READ_MENU = userInput.next();
        Integer userId = Integer.parseInt(READ_MENU);

        List<User> userList = users.stream().filter(user -> user.get_id().equals(userId)).collect(Collectors.toList());
        if (userList.size() > 0) {
            userList.forEach((user) -> {
                System.out.format("%-20s - %4d\n", "ID", user.get_id());
                System.out.format("%-20s - %4s\n", "URL", user.getUrl());
                System.out.format("%-20s - %4s\n", "EXTERNAL_ID ", user.getExternal_id());
                System.out.format("%-20s - %4s\n", "NAME ", user.getName());
                System.out.format("%-20s - %4s\n", "ALIAS ", user.getAlias());
                System.out.format("%-20s - %4s\n", "CREATED_AT ", user.getCreated_at());
                System.out.format("%-20s - %4s\n", "ACTIVE ", user.getActive());
                System.out.printf("%-20s - %4s\n", "VERIFIED ", user.getVerified());
                System.out.printf("%-20s - %4s\n", "SHARED ", user.getShared());
                System.out.format("%-20s - %4s\n", "LOCALE ", user.getLocale());
                System.out.printf("%-20s - %4s\n", "TIME_ZONE ", user.getTimezone());
                System.out.printf("%-20s - %4s\n", "LAST_LOGIN_AT ", user.getLast_login_at());
                System.out.printf("%-20s - %4s\n", "EMAIL ", user.getEmail());
                System.out.format("%-20s - %4s\n", "PHONE ", user.getPhone());
                System.out.printf("%-20s - %4s\n", "SIGNATURE", user.getSignature());
                System.out.printf("%-20s - %4s\n", "ORGANIZATION_ID ", user.getOrganization_id());
                System.out.printf("%-20s - %4s\n", "TAGS ", user.getTags());
                System.out.printf("%-20s - %4s\n", "SUSPENDED ", user.getSuspended());
                System.out.printf("%-20s - %4s\n", "ROLE ", user.getRole());
                List<Ticket> ticketList = getTickets(classLoader);
                List<Ticket> tickets = ticketList.stream().filter(ticket -> userId.equals(ticket.getAssignee_id())).collect(Collectors.toList());
                List<String> list = tickets.stream().map(ticket -> ticket.getSubject()).collect(Collectors.toList());
                System.out.printf("%-20s - %4s\n", "ASSIGNEE_SUBJECT ", list);
                List<String> ticketsSubmitted = ticketList.stream().filter(ticket -> userId.equals(ticket.getSubmitter_id())).map(t -> t.getSubject()).collect(Collectors.toList());
                System.out.printf("%-20s - %4s\n", "SUBMITTED_SUBJECT ", ticketsSubmitted);
                List<Organization> organizations = getOrganizations(classLoader);
                List<String> listOrg = organizations.stream().filter(organization -> user.getOrganization_id().equals(organization.get_id())).map(o->o.getName()).collect(Collectors.toList());
                System.out.printf("%-20s - %4s\n", "ORGANIZATIONS_NAMES ", listOrg);
            });
        } else {
            System.out.printf("******NO RESULT FOUND**********");
        }
        printConsoleSearchCriteria(classLoader);
    }

    private static ArrayList<Ticket> getTickets(ClassLoader classLoader) {
        File ticketFile = new File(classLoader.getResource(ticketsFileName).getFile());
        ArrayList<Ticket> tickets = null;
        try {
            tickets = mapper.readValue(ticketFile,
                    new TypeReference<ArrayList<Ticket>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    private static ArrayList<Organization> getOrganizations(ClassLoader classLoader) {
        File organizationFile = new File(classLoader.getResource(organizationFileName).getFile());
        ArrayList<Organization> organizations = null;
        try {
            organizations = mapper.readValue(organizationFile,
                    new TypeReference<ArrayList<Organization>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return organizations;
    }

    private static ArrayList<User> getUsers(ClassLoader classLoader) {
        File userFile = new File(classLoader.getResource(userFileName).getFile());
        ArrayList<User> users = null;
        try {
            users = mapper.readValue(userFile,
                    new TypeReference<ArrayList<User>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * related to Tickets entity search
     *
     * @param classLoader
     */
    private static void getTicketsSearchingTerms(ClassLoader classLoader) {

        ArrayList<Ticket> tickets = getTickets(classLoader);
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;
        System.out.println("ENTER TICKET ID : ");

        READ_MENU = userInput.next();
        String ticketId = READ_MENU;

        List<Ticket> ticketList = tickets.stream().filter(ticket -> ticket.get_id().equals(ticketId)).collect(Collectors.toList());
        if (ticketList.size() > 0) {
            ticketList.forEach((ticket) -> {
                System.out.format("%-19s - %4s\n", "ID", ticket.get_id());
                System.out.format("%-19s - %4s\n", "URL", ticket.getUrl());
                System.out.format("%-19s - %4s\n", "EXTERNAL_ID ", ticket.getExternal_id());
                System.out.format("%-19s - %4s\n", "TYPE ", ticket.getType());
                System.out.format("%-19s - %4s\n", "SUBJECT ", ticket.getSubject());
                System.out.format("%-19s - %4s\n", "DESCRIPTION ", ticket.getDescription());
                System.out.format("%-19s - %4s\n", "PRIORITY ", ticket.getPriority());
                System.out.printf("%-19s - %4s\n", "STATUS ", ticket.getStatus());
                System.out.printf("%-19s - %4s\n", "SUBMITTED_ID ", ticket.getSubmitter_id());
                System.out.format("%-19s - %4s\n", "ASSIGNEE_ID ", ticket.getAssignee_id());
                System.out.printf("%-19s - %4s\n", "ORGANIZATION_ID ", ticket.getOrganization_id());
                System.out.printf("%-19s - %4s\n", "TAGS ", ticket.getTags());
                System.out.printf("%-19s - %4s\n", "HAS_INCIDENTS ", ticket.getHas_incidents());
                System.out.format("%-19s - %4s\n", "DUE_AT ", ticket.getDue_at());
                System.out.printf("%-19s - %4s\n", "VIA", ticket.getVia());

                List<User> list = getUsers(classLoader);
                List<String> assigneeName= list.stream().filter(user-> ticket.getAssignee_id().equals(user.get_id())).map(user -> user.getName()).collect(Collectors.toList());
                System.out.printf("%-19s - %4s\n", "ASSIGNEE_NAME", assigneeName);
                List<String> submitterName= list.stream().filter(user-> ticket.getSubmitter_id().equals(user.get_id())).map(user -> user.getName()).collect(Collectors.toList());
                System.out.printf("%-19s - %4s\n", "SUBMITTER_NAME", submitterName);
                List<Organization> organizations = getOrganizations(classLoader);
                List<String> listOrg = organizations.stream().filter(organization -> ticket.getOrganization_id().equals(organization.get_id())).map(o->o.getName()).collect(Collectors.toList());
                System.out.printf("%-19s - %4s\n", "ORGANIZATIONS_NAMES ", listOrg);

            });
        } else {
            System.out.printf("******NO RESULT FOUND**********");
        }
        printConsoleSearchCriteria(classLoader);
    }
}
