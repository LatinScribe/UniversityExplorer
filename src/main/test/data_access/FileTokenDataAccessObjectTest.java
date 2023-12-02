package data_access;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileTokenDataAccessObjectTest {

    @Test
    void save_token() throws IOException {
        FileTokenDataAccessObject fileTokenDataAccessObject = new FileTokenDataAccessObject("token.csv");
        fileTokenDataAccessObject.save_token(12, "ABCDEFGAA");

        assert (fileTokenDataAccessObject.retrieve_token().equals("ABCDEFGAA"));
        assert (fileTokenDataAccessObject.retrieve_id() == 12);
    }

    @Test
    void remove_token() throws IOException {

        FileTokenDataAccessObject fileTokenDataAccessObject = new FileTokenDataAccessObject();
        fileTokenDataAccessObject.save_token(12, "ABCDEFGAA");

        assert (fileTokenDataAccessObject.retrieve_token().equals("ABCDEFGAA"));
        assert (fileTokenDataAccessObject.retrieve_id() == 12);

        fileTokenDataAccessObject.remove_token();

        assert (fileTokenDataAccessObject.retrieve_token().equals("DEFAULT TOKEN-NONE SAVED"));
        assert (fileTokenDataAccessObject.retrieve_id() == 0);

    }
}