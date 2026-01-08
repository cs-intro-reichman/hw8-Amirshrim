/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }

    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
    public User getUser(String name) {
        //// Replace the following statement with your code
        for(int i=0;i<userCount;i++){
            if (users[i].getName().equals(name)){
                return users[i];
            }
        }
        return null;
    }

    /** Adds a new user with the given name to this network.
    *  If ths network is full, does nothing and returns false;
    *  If the given name is already a user in this network, does nothing and returns false;
    *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {
    if (userCount == users.length) {
        return false;
    }
    if (getUser(name) != null) {
        return false; 
    }
    users[userCount] = new User(name);
    userCount++; 
    return true;
}

    
    public boolean addFollowee(String name1, String name2) {        
        User u1 = getUser(name1);
        User u2 = getUser(name2);
        if (u1 == null || u2 == null) {
            return false;
        }
        if (name1.equals(name2)) {
            return false;
        }
        return u1.addFollowee(name2);
    }
    
    public String recommendWhoToFollow(String name) {
    User user = getUser(name);
    
    if (user == null) {
        return null;
    }
    User bestPerson = null;
    int maxMutual = -1; 
    for (int i = 0; i < userCount; i++) {
        User currentCandidate = users[i];
        if (currentCandidate.getName().equals(name)) {
        } else {
            int count = user.countMutual(currentCandidate);
            if (count > maxMutual) {
                maxMutual = count;   
                bestPerson = currentCandidate; 
            }
        }
    }
    if (bestPerson == null) {
        return null;
    } else {
        return bestPerson.getName();
    }
}

    /** Computes and returns the name of the most popular user in this network: 
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
    // 1. טיפול במקרה של רשת ריקה (Case 2 בלוג השגיאות)
    if (userCount == 0) {
        return null;
    }

    String popularName = null;
    int maxFollowers = -1;

    for (int i = 0; i < users.length; i++) {
        if (users[i] != null) {
            String currentName = users[i].getName();            
            int currentFollowers = followeeCount(currentName); 
            if (currentFollowers > maxFollowers) {
                maxFollowers = currentFollowers;
                popularName = currentName;
            }
        }
    }
    return popularName;
}

    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        //// Replace the following statement with your code
        int counter=0;
        for (int i=0;i<users.length;i++){
            if(users[i].getName().equals(name)){}
            else if (users[i].follows(name)) {
                counter++;
            }
        }
        return counter;
    }

    // Returns a textual description of all the users in this network, and who they follow.
    public String toString() {
       //// Replace the following statement with your code
       String str = "";
       for(int i=0;i<userCount;i++){
        if (users[i]==null){}
        else { 
            str += "/n" + users[i].toString();
        }
       }
       return str;
    }
}
