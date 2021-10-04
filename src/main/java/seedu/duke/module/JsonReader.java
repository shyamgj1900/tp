package seedu.duke.module;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonReader {

    public static String filePath = "data/moduleDetails.json";

    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            List<ModuleDetails> modules = gson.fromJson(reader, new TypeToken<List<ModuleDetails>>() {}.getType());
            System.out.println(modules.get(0).getModuleCode() + " " + modules.get(0).getTitle());
            System.out.println(modules.get(0).getFaculty());
            System.out.println(modules.get(0).getDescription());
            System.out.println(modules.get(0).getModuleCredit());
            System.out.println(modules.get(0).getDepartment());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
