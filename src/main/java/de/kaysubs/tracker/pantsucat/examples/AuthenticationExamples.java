package de.kaysubs.tracker.pantsucat.examples;

import de.kaysubs.tracker.pantsucat.PantsuCatApi;

import java.util.Scanner;

public class AuthenticationExamples {

    private final static PantsuCatApi API = PantsuCatApi.getInstance();

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        String username = sc.next();
        System.out.print("Password: ");
        String password = sc.next();


        System.out.println("Logging in as " + username);
        String apiToken = API.login(username, password).getApiToken();
        System.out.println("Your API token is " + apiToken);
        System.out.println();


        boolean isTokenValid = API.checkApiToken(username, apiToken).isValid();
        System.out.println("Your API token is " + (isTokenValid ? "valid" : "invalid"));
        System.out.println();


        System.out.println("Let's grab a new API token");
        String newApiToken = API.refreshApiToken(username, apiToken).getUserData().getApiToken();
        System.out.println("Your new Api Token is " + newApiToken);
        System.out.println();

        boolean isNewTokenValid = API.checkApiToken(username, newApiToken).isValid();
        System.out.println("Your new API token is " + (isNewTokenValid ? "valid" : "invalid"));
        boolean isOldTokenValid = API.checkApiToken(username, apiToken).isValid();
        System.out.println("Your old API token is " + (isOldTokenValid ? "valid" : "invalid"));
    }

}
