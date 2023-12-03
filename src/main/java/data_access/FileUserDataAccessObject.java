// Author: Common (mostly managed by Henry)

package data_access;

import entity.CreationUser;
import entity.ExistingUser;
import entity.ExistingUserFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, ExistingUser> accounts = new HashMap<>();

    private final ExistingUserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, ExistingUserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("id", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("creation_time", 3);
        headers.put("token", 4);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("id,username,password,creation_time,token");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    int id = Integer.parseInt(col[headers.get("id")]);
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    String token = String.valueOf(col[headers.get("token")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    ExistingUser user = userFactory.create(username, id, password, ldt, token);
                    accounts.put(username, user);
                }
            }
        }
    }

    @Override
    public ExistingUser get(String username, String password) {
        return accounts.get(username);
    }

//    @Override
//    public void delete() {
//        BufferedWriter writer;
//        try {
//            writer = new BufferedWriter(new FileWriter(csvFile, false));
//            writer.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public ExistingUser save(CreationUser user) {
        int id = 1234;
        String token = "123asdEXAMPLETOKEN";
        ExistingUser new_user = userFactory.create(user.getName(), id, user.getPassword(), user.getCreationTime(), token);
        accounts.put(user.getName(), new_user);
        this.save();
        return new_user;
    }


    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (ExistingUser user : accounts.values()) {
                String line = String.format("%s,%s,%s,%s,%s",
                        user.getID(), user.getName(), user.getPassword(), user.getCreationTime(), user.getToken());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     *
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

}
