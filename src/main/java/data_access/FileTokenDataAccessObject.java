package data_access;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A class that contains the data access object to store and retrieve user tokens and id
 * Used to keep track of whether a user is already logged in or not
 *
 * @author Henry
 */
public class FileTokenDataAccessObject implements TokenDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    // just in case you want to specify a specific path to save token
    public FileTokenDataAccessObject(String csvPath) {
        csvFile = new File(csvPath);
        headers.put("id", 0);
        headers.put("token", 1);

    }

    // we can probably always use this
    public FileTokenDataAccessObject() {
        csvFile = new File("token.csv");
        headers.put("id", 0);
        headers.put("token", 1);

    }

    /**
     * Use this method to save a token and id to file
     * Should be executed on successful login
     *
     * @param id
     * @param token
     */
    @Override
    public void save_token(int id, String token) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();


            String line = String.format("%s,%s", id, token);
            writer.write(line);
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Use this method to reset the token in the save file
     * Should be used on logout
     */
    @Override
    public void remove_token() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();


            String line = String.format("%s,%s", 0, "DEFAULT TOKEN-NONE SAVED");
            writer.write(line);
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Use this method to retrieve the current token in the save file
     *
     * @return token in the save file
     * @throws IOException
     */
    @Override
    // note, this could throw a file not found error
    public String retrieve_token() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        String line = br.readLine();
        String[] values = line.split(",");
        return values[1];
    }

    /**
     * Use this method to retrieve the id currently in the save file
     *
     * @return the id currently in the save file
     * @throws IOException
     * @throws NumberFormatException
     */
    @Override
    // note, this could throw a file not found error
    public int retrieve_id() throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        String line = br.readLine();
        String[] values = line.split(",");
        return Integer.parseInt(values[0]);
    }

//    public static void main(String[] args) throws IOException {
//        FileTokenDataAccessObject fileTokenDataAccessObject = new FileTokenDataAccessObject();
//        System.out.println(fileTokenDataAccessObject.retrieve_id());
//        System.out.println(fileTokenDataAccessObject.retrieve_token());
//    }
}
