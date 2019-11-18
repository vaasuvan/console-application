package com.console.application;

import com.console.application.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
//        User user[] = null;
        try {
            String fileName = "json/users.json";
            ClassLoader classLoader = new Main().getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
           /* TypeFactory typeFactory = objectMapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(
                    List.class, DocumentationTool.Location.class);*/
            User[] user = objectMapper.readValue(file, User[].class);
            System.out.println(user.toString());
//            System.out.println(user.getEmail());
//            System.out.println(user.getTags().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
