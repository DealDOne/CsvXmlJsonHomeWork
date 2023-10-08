package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static ru.netology.CsvToJson.*;
import static ru.netology.XmlToJSon.parseXML;

public class Main {

    public static <T> String listToJson(List<T> list){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<T>>() {}.getType();
        String json = gson.toJson(list, listType);
        return json;
    }

    public static void writeString(String json, String path){
        try (FileWriter file = new FileWriter(path)) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> list = parseCSV(columnMapping, "data.csv");
        String jsonFromCsv = listToJson(list);
        writeString(jsonFromCsv , "data.json");
        List<Employee> employeeList = parseXML("data.xml");
        String jsonFromXML = listToJson(employeeList);
        writeString(jsonFromXML, "data2.json");
    }
}