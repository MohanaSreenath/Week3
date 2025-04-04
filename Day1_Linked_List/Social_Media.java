import java.util.ArrayList;
import java.util.List;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friendIds;
    User next;

    User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }
}

class SocialMedia {
    private User head;

    public void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
    }

    public void addFriendConnection(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        if (user1 != null && user2 != null) {
            if (!user1.friendIds.contains(userId2)) user1.friendIds.add(userId2);
            if (!user2.friendIds.contains(userId1)) user2.friendIds.add(userId1);
        }
    }

    public void removeFriendConnection(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        if (user1 != null && user2 != null) {
            user1.friendIds.remove(Integer.valueOf(userId2));
            user2.friendIds.remove(Integer.valueOf(userId1));
        }
    }

    public List<Integer> findMutualFriends(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        List<Integer> mutualFriends = new ArrayList<>();
        if (user1 != null && user2 != null) {
            for (int friendId : user1.friendIds) {
                if (user2.friendIds.contains(friendId)) {
                    mutualFriends.add(friendId);
                }
            }
        }
        return mutualFriends;
    }

    public List<Integer> displayFriends(int userId) {
        User user = findUserById(userId);
        return user != null ? user.friendIds : new ArrayList<>();
    }

    public User searchUser(String name, int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.name.equals(name) || temp.userId == userId) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public int countFriends(int userId) {
        User user = findUserById(userId);
        return user != null ? user.friendIds.size() : 0;
    }

    private User findUserById(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public static void main(String[] args) {
        SocialMedia sm = new SocialMedia();
        sm.addUser(1, "Alice", 25);
        sm.addUser(2, "Bob", 30);
        sm.addUser(3, "Charlie", 20);

        sm.addFriendConnection(1, 2);
        sm.addFriendConnection(1, 3);

        System.out.println(sm.displayFriends(1));
        System.out.println(sm.findMutualFriends(1, 2));
        sm.removeFriendConnection(1, 2);
        System.out.println(sm.displayFriends(1));
        System.out.println(sm.countFriends(1));
        System.out.println(sm.searchUser("Charlie", 0).name);
    }
}