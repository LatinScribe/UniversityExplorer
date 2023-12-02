package entity;

import java.time.LocalDateTime;

// This is the factory for users upon INITIAL creation. It should NOT be used
// outside of the signup use case

public class CreationCommonUserFactory implements CreationUserFactory {
    /**
     * Requires: password is valid.
     *
     * @param name
     * @param password
     * @return
     */

    @Override
    public CreationUser create(String name, String password, LocalDateTime ltd) {
        return new CreationCommonUser(name, password, ltd);
    }
}
