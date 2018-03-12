package de.kaysubs.tracker.pantsucat.examples;

import de.kaysubs.tracker.pantsucat.PantsuCatApi;
import de.kaysubs.tracker.pantsucat.model.*;

import java.io.File;
import java.util.Scanner;

public class TorrentManagementExamples {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("username: ");
        String username = sc.next();
        System.out.print("password: ");
        String password = sc.next();

        String apiKey = PantsuCatApi.getInstance().login(username, password).getApiToken();

        System.out.print("Location of .torrent file or magnet link: ");
        String source = sc.next();

        int torrentId = uploadExampleTorrent(username, apiKey, source);
        updateExampleTorrent(username, apiKey, torrentId);
    }

    public static int uploadExampleTorrent(String username, String apiKey, String source) {
        System.out.println("Uploading example torrent");

        UploadRequest request =
                (source.startsWith("magnet:") ?
                        UploadRequest.fromMagnet(source) :
                        UploadRequest.fromSeedfile(new File(source)))
                .setUsername(username)
                .setApiKey(apiKey)
                .setName("API Example Torrent")
                .setDescription("This Torrent has been uploaded via 'k subs pantsu.cat api.")
                .setCategory(Category.anime.nonEnglish)
                .setRemake(false)
                .setAnonymous(true)
                .setWebsite("http://example.com/")
                .setLanguages(Language.CHINESE, Language.TRADITIONAL_CHINESE);

        TorrentInfo response = PantsuCatApi.getInstance().uploadTorrent(request);

        int torrentId = response.getId();
        System.out.println("The torrent can now be seen at https://pantsu.cat/view/" + torrentId);
        return torrentId;
    }

    public static void updateExampleTorrent(String username, String apiKey, int torrentId) {
        PantsuCatApi.getInstance().updateTorrent(new UpdateRequest()
                .setApiKey(apiKey)
                .setUsername(username)
                .setTorrentId(torrentId)

                .setName("Edited API Example Torrent")
                .setDescription("This Torrent has been uploaded and edited via 'k subs pantsu.cat api.")
                .setCategory(Category.literature.nonEnglish)
                .setRemake(true)
                .setAnonymous(false)
                .setWebsite("http://edited-example.com/")
                .setLanguages(Language.GERMAN, Language.DUTCH)
        );
    }

}
