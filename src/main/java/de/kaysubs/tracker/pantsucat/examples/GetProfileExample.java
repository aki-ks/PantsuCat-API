package de.kaysubs.tracker.pantsucat.examples;

import de.kaysubs.tracker.pantsucat.PantsuCatApi;
import de.kaysubs.tracker.pantsucat.model.UserProfile;

import java.util.Scanner;

public class GetProfileExample {

    // example user ids: 18031, 16757, 9364
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("UserId: ");
        int userId = sc.nextInt();

        UserProfile profile = PantsuCatApi.getInstance().getProfile(userId);
        System.out.println("id: " + profile.getUserId());
        System.out.println("name: " + profile.getUsername());
        System.out.println("email-md5: " + profile.getEmailHash());
        System.out.println("status: " + profile.getStatus());
        System.out.println("registration date: " + profile.getCreatedAt());
        System.out.println("like count: " + profile.getLikedCount());
        System.out.println("liking count: " + profile.getLikingCount());
    }

}
