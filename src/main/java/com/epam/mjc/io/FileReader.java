package com.epam.mjc.io;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FileReader {

    public Profile getDataFromFile(File file) {


        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            List<String> list = new ArrayList<>(List.of());
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            HashMap<String, String> map = parseToHashMap(list);
            return mapToProfile(map);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new Profile();
        }
    }

    public static HashMap<String, String> parseToHashMap(List<String> list) {
        HashMap<String, String> map = new HashMap<>();
        for (String str :
                list) {
            String[] splitedLine = str.split(":");
            map.put(splitedLine[0].trim(), splitedLine[1].trim());
        }
        return map;
    }

    public static Profile mapToProfile(HashMap<String, String> map) {
        String name = map.get("Name");
        Integer age = Integer.parseInt(map.get("Age"));
        String email = map.get("Email");
        Long phone = Long.parseLong(map.get("Phone"));

        return new Profile(name, age, email, phone);
    }

//    public static void main(String[] args) {
//
//        File file = new File(TEST_FILE);
//
//        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
//            String line;
//            List<String> list = new ArrayList<>(List.of());
//            while ((line = reader.readLine()) != null) {
//                //System.out.print((char) c);
//                list.add(line);
//            }
//            HashMap<String, String> map = parseToHashMap(list);
//            Profile profile = mapToProfile(map);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
