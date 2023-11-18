package data_access;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileTokenDataAccessObject implements TokenDataAccessInterface{
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

    @Override
    // note, this could throw a file not found error
    public String retrieve_token() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        String line = br.readLine();
        String[] values = line.split(",");
        return values[1];
    }

    @Override
    // note, this could throw a file not found error
    public int retrieve_id() throws IOException, NumberFormatException{
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        String line = br.readLine();
        String[] values = line.split(",");
        return Integer.parseInt(values[0]);
    }

    public static void main(String[] args) {
        FileTokenDataAccessObject fileTokenDataAccessObject = new FileTokenDataAccessObject();
        fileTokenDataAccessObject.save_token(12, "ABCDEFGAA");

        try {
            // print the saved token
            System.out.println(fileTokenDataAccessObject.retrieve_token());
        }
        catch (FileNotFoundException e) {
            System.out.println("file is not found");
        }
        catch (IOException e) {
            System.out.println("Token file was configured/saved incorrectly");
        }

        try {
            // print the saved id
            System.out.println(fileTokenDataAccessObject.retrieve_id());
        }
        catch (FileNotFoundException e) {
            System.out.println("file is not found");
        }
        catch (IOException | NumberFormatException e) {
            System.out.println("Token file was configured/saved incorrectly");
        }

        // reset the token file
        fileTokenDataAccessObject.remove_token();

        try {
            // print the default token after removal
            System.out.println(fileTokenDataAccessObject.retrieve_token());
        }
        catch (FileNotFoundException e) {
            System.out.println("file is not found");
        }
        catch (IOException e) {
            System.out.println("Token file was configured/saved incorrectly");
        }

        try {
            // print the default id
            System.out.print(fileTokenDataAccessObject.retrieve_id());
        }
        catch (FileNotFoundException e) {
            System.out.println("file is not found");
        }
        catch (IOException | NumberFormatException e) {
            System.out.println("Token file was configured/saved incorrectly");
        }
    }
}
