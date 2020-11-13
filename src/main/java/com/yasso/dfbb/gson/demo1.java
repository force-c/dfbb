package com.yasso.dfbb.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Set;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/11/9 14:39
 */
public class demo1 {

    public static void main(String[] args) {
        Gson gson = new Gson();
        String ttk = gson.toJson(new Book("001", "ttk"));
        System.out.println(ttk);

        String json = "{\"id\":\"2\",\"name\":\"Json技术\"}";
        Book book = gson.fromJson(json, Book.class);
        System.out.println(book.toString());

        String json2 = "[{\"id\":\"1\",\"name\":\"Json技术\"},{\"id\":\"2\",\"name\":\"java技术\"}]";

        List list = gson.fromJson(json2, new TypeToken<List>() {}.getType());
        System.out.println(list);

        JsonElement jsonElement = JsonParser.parseString("");
    }
}
