import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static Map<Integer, String> friendsWithReservedSeats;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        friendsWithReservedSeats = new HashMap<>();
        List<String> friends = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());

        String input = scanner.nextLine();
        while (!input.equals("generate")) {
            String[] friendData = input.split(" - ");
            String name = friendData[0];
            Integer seat = Integer.parseInt(friendData[1]);

            if (friends.contains(name)) {
                friends.remove(name);
                friendsWithReservedSeats.put(seat, name);
            }
            input = scanner.nextLine();
        }

        String[] friendsWithoutSeats = new String[friends.size()];
        for (int i = 0; i < friendsWithoutSeats.length; i++) {
            friendsWithoutSeats[i] = friends.get(i);
        }

        permute(0, friendsWithoutSeats);
    }

    private static void permute(int index, String[] array) {
        if (index >= array.length) {
            printFriendsBySeat(array);
            return;
        }

        permute(index + 1, array);
        for (int i = index + 1; i < array.length; i++) {
            swap(index, i, array);
            permute(index + 1, array);
            swap(index, i, array);
        }
    }

    private static void swap(int firstIndex, int secondIndex, String[] array) {
        String temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    private static void printFriendsBySeat(String[] friendsWithoutSeats) {
        int numberOfAllFriends = friendsWithoutSeats.length + friendsWithReservedSeats.size();
        int indexOfFriendsWithoutSeats = 0;
        StringBuilder builder = new StringBuilder();
        for (int seat = 1; seat <= numberOfAllFriends; seat++) {
            if (friendsWithReservedSeats.get(seat) != null) {
                builder.append(friendsWithReservedSeats.get(seat));
            } else {
                builder.append(friendsWithoutSeats[indexOfFriendsWithoutSeats++]);
            }
            builder.append(" ");
        }

        System.out.println(builder.toString().trim());
    }


}
