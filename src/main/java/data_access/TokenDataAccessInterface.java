package data_access;

import java.io.IOException;

public interface TokenDataAccessInterface {

    void save_token(int id, String token);

    void remove_token();

    // note, this could throw a file not found error
    String retrieve_token() throws IOException;

    // note, this could throw a file not found error
    int retrieve_id() throws IOException, NumberFormatException;
}
