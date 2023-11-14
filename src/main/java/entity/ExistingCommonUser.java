//package entity;
//
//import java.time.LocalDateTime;
//
//public class ExistingCommonUser implements ExistingUser{
//
//    private final String name;
//<<<<<<< HEAD:src/main/java/entity/InitialCommonUser.java
//    private final int id;
//=======
//    private final String id;
//>>>>>>> parent of 29b2155 (Revert "Pulling the newest changes to the branch"):src/main/java/entity/ExistingCommonUser.java
//
//    private final String password;
//    private final LocalDateTime creationTime;
//    private String token;
//
//    /**
//     * Requires: should match entries in server database
//     *
//     * @param name
//     * @param id
//     * @param password
//     */
//<<<<<<< HEAD:src/main/java/entity/InitialCommonUser.java
//    ExistingCommonUser(String name, int id, String password, LocalDateTime creationTime, String token) {
//=======
//    ExistingCommonUser(String name, String id, String password, LocalDateTime creationTime, String token) {
//>>>>>>> parent of 29b2155 (Revert "Pulling the newest changes to the branch"):src/main/java/entity/ExistingCommonUser.java
//        this.name = name;
//        this.id = id;
//        this.password = password;
//        this.creationTime = creationTime;
//        this.token = token;
//    }
//
//    @Override
//    public String getName() {
//        return name;
//    }
//
//<<<<<<< HEAD:src/main/java/entity/InitialCommonUser.java
//    public int getID() {
//=======
//    public String getID() {
//>>>>>>> parent of 29b2155 (Revert "Pulling the newest changes to the branch"):src/main/java/entity/ExistingCommonUser.java
//        return id;
//    }
//
//    @Override
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    @Override
//    public String getToken() {
//        return token;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    public LocalDateTime getCreationTime() {
//        return creationTime;
//    }
//}
