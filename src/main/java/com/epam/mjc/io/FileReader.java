package com.epam.mjc.io;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            List<String> list = new ArrayList<>(List.of());
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            Map<String, String> map = parseToHashMap(list);
            return mapToProfile(map);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new Profile();
        }
    }

    public static Map<String, String> parseToHashMap(List<String> list) {
        HashMap<String, String> map = new HashMap<>();
        for (String str :
                list) {
            String[] splitedLine = str.split(":");
            map.put(splitedLine[0].trim(), splitedLine[1].trim());
        }
        return map;
    }

    public static Profile mapToProfile(Map<String, String> map) {
        String name = map.get("Name");
        Integer age = Integer.parseInt(map.get("Age"));
        String email = map.get("Email");
        Long phone = Long.parseLong(map.get("Phone"));

        return new Profile(name, age, email, phone);
    }
}
