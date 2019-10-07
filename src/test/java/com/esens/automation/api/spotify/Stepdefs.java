package com.esens.automation.api.spotify;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class Stepdefs extends SpotifyApiTest {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    @Given("My Access Token")
    public void my_access_token() {
        System.out.println("Start Test API Spotify with client : " + clientID);
        System.out.println("the token was retrieved during the import of the class SpotifyApiTest.java in getAccessToken()");
        System.out.println("This function does not retrieve the token and can be deleted, it is indicative");
    }

    @When("Get a Playlist")
    public void get_a_playlist(){
        getPlaylistById(myPlaylistID);
    }

    @When("Get Track")
    public void get_track(){
        getTrackById(trackID);
    }

    @When("Get Artist")
    public void get_artist(){
        getArtistById(artistID);
    }

    @When("Get Album")
    public void get_album(){
        getAlbumById(albumID);
    }

    @When("Get a Playlist FailTest")
    public void get_a_playlist_failtest(){
        getPlaylistById_FailTest(fakePlaylistID);
    }

    @When("Get List Of Current User Playlist")
    public void get_list_of_cureent_user_playlist(){
        getMyCurrentUsersPlaylisst();
    }

    @When("Get Playlist Tracks")
    public void getPlaylistTracksById(){
        getPlaylistTracksById(myPlaylistID);
    }

    @When("Create Playlist")
    public void create_playlist(){
        CreatePlaylist("name=Cikhou&description=soso");
    }

    @When("Add Tracks To Playlist")
    public void add_track_to_playlist(){
        AddTracksToPlaylist(myPlaylistID,tracksUris);
    }

    @When("Change Playlist Details")
    public void change_playlist_details(){
        ChangePlaylistDetails(myPlaylistID,newNameForChangePlaylistDetail);
    }
}
