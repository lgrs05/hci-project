package com.hci.hci_project;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyAuth {

    private static final List<Map.Entry<User, String>> TUTORS = new ArrayList<>();
    static {
        TUTORS.add(new AbstractMap.SimpleEntry<>(new User("foo@example.com", "Luis",
                "Rivera", "hello", false, "ICOM"), "Physics I"));

        TUTORS.add(new AbstractMap.SimpleEntry<>(new User("bar@example.com","Ernesto",
                "Sanchez", "world", true, "ICOM"), "Physics II"));

        TUTORS.add(new AbstractMap.SimpleEntry<>(new User("bar@example.com","Ernesto",
                "Sanchez", "world", true, "ICOM"), "Calculus II"));
    }
    private static final HashMap<String, HashMap<String, List<Message>>> MESSAGES = new HashMap<>();
    private static final HashMap<String, User> USERS = new HashMap<>();
    static{
        USERS.put("test", new User("test", "Luis", "Rivera", "test", false, "ICOM"));
        USERS.put("foo@example.com", new User("foo@example.com", "Luis", "Rivera", "hello", false, "ICOM"));
        USERS.put("bar@example.com", new User("bar@example.com", "Ernesto", "Sanchez", "world", true, "ICOM"));
        USERS.put("hey@example.com", new User("hey@example.com", "Luis", "Rivera", "hello", false, "ICOM"));
        USERS.put("ba@example.com", new User("ba@example.com", "Ernesto", "Sanchez", "world", true, "ICOM"));
        USERS.put("fo@example.com", new User("fo@example.com", "Luis", "Rivera", "hello", false, "ICOM"));
        USERS.put("barr@example.com", new User("barr@example.com", "Ernesto", "Sanchez", "world", true, "ICOM"));
        USERS.put("heyy@example.com", new User("heyy@example.com", "Luis", "Rivera", "hello", false, "ICOM"));
        USERS.put("baa@example.com", new User("baa@example.com", "Ernesto", "Sanchez", "world", true, "ICOM"));

    }
    protected static User currentUser;

    public static List<Map.Entry<User, String>> getTUTORS() {
        return TUTORS;
    }

    public static void saveMessage(User target, Message message){
        if(!MESSAGES.containsKey(currentUser.getEmail())){
            createList(target);
        }
        MESSAGES.get(currentUser.getEmail()).get(target.getEmail()).add(message);
    }

    private static void createList(User target) {
        HashMap<String, List<Message>> mMap = new HashMap<>();
        mMap.put(target.getEmail(), new ArrayList<Message>());
        MESSAGES.put(currentUser.getEmail(),mMap );
    }

    public static List<Message> getMessages(User target){
        if(!MESSAGES.containsKey(currentUser.getEmail()) ||
                !MESSAGES.get(currentUser.getEmail()).containsKey(target.getEmail())) {
            createList(target);
            return MESSAGES.get(currentUser.getEmail()).get(target.getEmail());
        }

        return MESSAGES.get(currentUser.getEmail()).get(target.getEmail());
    }

    public static ArrayList<User> getUsers(){
        return new ArrayList<>(USERS.values());
    }

    public static boolean register(String first, String last, String email, String password, boolean isProfessor, String major){
        if(USERS.containsKey(email)) return false;
        USERS.put(email, new User(email, first, last, password, isProfessor, major));
        login(email, password);
        return true;
    }

    public static User login(String email, String password){
        if(!USERS.containsKey(email)) return null;
        currentUser = USERS.get(email).getPassword().equals(password)? USERS.get(email) : null;
        return currentUser;
    }

    public static void logout(){
        currentUser = null;
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public static boolean emailExists(String email){
        return USERS.containsKey(email);
    }


}

class User {
    private String email;
    private String first;
    private String last;
    private String password;
    private boolean isProfessor;
    private String major;

    public User(String email, String first, String last, String password, boolean isProfessor, String major) {
        this.email = email;
        this.first = first;
        this.last = last;
        this.password = password;
        this.isProfessor = isProfessor;
        this.major = major;
    }

    public String getName(){
        return first + " " + last;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getPassword() {
        return password;
    }

    public boolean isProfessor() {
        return isProfessor;
    }

    public String getMajor() {
        return major;
    }

//    @Override
////    protected Boolean doInBackground(Void... params) {
////        // TODO: attempt authentication against a network service.
////
////        try {
////            // Simulate network access.
////            Thread.sleep(2000);
////        } catch (InterruptedException e) {
////            return false;
////        }
////
////
////
////        if(DUMMY_CREDENTIALS.containsKey(mEmail)){
////            return mPassword.equals(DUMMY_CREDENTIALS.get(mEmail));
////        }
////
////        // TODO: register the new account here.
////        return false;
////    }
////
////    @Override
////    protected void onPostExecute(final Boolean success) {
////        DummyAuth.currentUser = null;
////        showProgress(false);
////
////        if (success) {
////            finish();
////        } else {
////            mPasswordView.setError(getString(R.string.error_incorrect_password));
////            mPasswordView.requestFocus();
////        }
////    }
////
////    @Override
////    protected void onCancelled() {
////        mAuthTask = null;
////        showProgress(false);
////    }

}
