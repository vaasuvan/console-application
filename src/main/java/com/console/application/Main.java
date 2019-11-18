package com.console.application;

import com.console.application.model.Organization;
import com.console.application.model.Ticket;
import com.console.application.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
//        User user[] = null;
        try {
            String userFileName = "json/users.json";
            String organizationFileName = "json/organizations.json";
            String ticketsFileName = "json/tickets.json";

            ClassLoader classLoader = new Main().getClass().getClassLoader();

           /* TypeFactory typeFactory = objectMapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(
                    List.class, DocumentationTool.Location.class);*/
           // user json convert
//            File file = new File(classLoader.getResource(fileName).getFile());
           /* User[] user = objectMapper.readValue(file, User[].class);
            System.out.println(user.toString());*/


            // organization json convert
          /*  File file = new File(classLoader.getResource(organizationFileName).getFile());
            Organization[] organizations = objectMapper.readValue(file, Organization[].class);
            System.out.println(organizations.toString());*/

            // organization json convert
            File file = new File(classLoader.getResource(ticketsFileName).getFile());
            Ticket[] tickets = objectMapper.readValue(file, Ticket[].class);
            System.out.println(tickets.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
