package org.example;

import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {
        printHeader();

        Person p = new Person("Valerii", "Priadchenko", 19);

        Gson gson = new Gson();

        String json = gson.toJson(p);
        Person personFromJson = gson.fromJson(json, Person.class);

        boolean personFromJsonEqualsInitial = p.equals(personFromJson);

        print("Created person: \n" + p);
        print("\nJson object: " + json);
        print("\nPerson from Json: \n" + personFromJson);
        print("\ncreatedObject.equals(initialObject) -> " +
              personFromJsonEqualsInitial);
    }


    public static void printHeader() {
        print("""
                .____          ___.     ________   ____   ____      .__    __________        .__            .___
                │    │   _____ \\_ │__   \\_____  \\  \\   \\ /   /____  │  │   \\______   \\_______│__│____     __│ _/
                │    │   \\__  \\ │ __ \\   /  ____/   \\   Y   /\\__  \\ │  │    │     ___/\\_  __ \\  \\__  \\   / __ │\s
                │    │___ / __ \\│ \\_\\ \\ /       \\    \\     /  / __ \\│  │__  │    │     │  │ \\/  │/ __ \\_/ /_/ │\s
                │_______ (____  /___  / \\_______ \\    \\___/  (____  /____/  │____│     │__│  |__(____  /\\____ │\s
                        \\/    \\/    \\/          \\/                \\/                                 \\/      \\/\s
                """);


        print("""
                Tasks:
                Step 1. Implement the equals method for the Person class,
                which has several fields (last name, first name, age).
                
                Step 2. Implement the following scenario:
                    a. Create a Person instance
                    b. Convert it to JSON*
                    c. Convert it back to an object*
                    d. Verify the original and resulting objects using equals
                """);
    }

    public static void print(String s) {
        System.out.println(s);
    }

}