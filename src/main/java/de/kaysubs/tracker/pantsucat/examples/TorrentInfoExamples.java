package de.kaysubs.tracker.pantsucat.examples;

import de.kaysubs.tracker.pantsucat.PantsuCatApi;
import de.kaysubs.tracker.pantsucat.model.TorrentInfo;

import java.util.Arrays;
import java.util.Scanner;

public class TorrentInfoExamples {

    // Example ids: 1004294, 1004113
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Torrent id: ");
        int torrentId = sc.nextInt();
        System.out.println();

        TorrentInfo info = PantsuCatApi.getInstance().getTorrentInfo(torrentId);

        System.out.println("name: " + info.getName());
        System.out.println("description:\n" + info.getDescription());
        System.out.println("uploader: " + info.getUploaderName() + " (https://pantsu.cat/user/"+info.getUploaderId()+")");
        System.out.println("category: " + info.getCategory().getMainCategory().name() + "." + info.getCategory().name());
        System.out.println("hash: " + info.getHash());
        System.out.println("language: " + Arrays.toString(info.getLanguages()));
        System.out.println("size: " + info.getFilesize());
        System.out.println("seeders: " + info.getSeeders());
        System.out.println("leechers: " + info.getLeechers());
        System.out.println("completed: " + info.getCompleted());
        System.out.println("comments (" + info.getComments().length + "):");
        for(TorrentInfo.Comment c : info.getComments()) {
            System.out.println("[Comment]");
            System.out.println("user: " + c.getUsername() + " (https://pantsu.cat/user/"+ c.getUserId()+ ")");
            System.out.println("date: " + c.getDate());
            System.out.println("avatar: " + c.getUserAvatar());
            System.out.println("isFromUploader: " + c.isFromUploader());
            System.out.println("userStatus: " + c.getUserStatus());
            System.out.println("message:\n" + c.getMessage());
        }
    }

}
