package com.esens.automation.api.spotify;

import com.wrapper.spotify.model_objects.specification.*;
import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

public class Stepdefs extends SpotifyApiSpec{

    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println();
        if (!scenario.isFailed()) {
            System.out.println(ANSI_GREEN + "Passed" + ANSI_RESET);
        }
    }

    @Given("My Access Token")
    public void my_access_token() {
        System.out.println("Start Test API Spotify with client : " + clientID);
        System.out.println("the token was retrieved during the import of the class SpotifyApiSpec.java in getAccessToken()");
        System.out.println("My Access Token :" + spotifyApi.getAccessToken());
        System.out.println("This function does not retrieve the token and can be deleted, it is indicative");
    }

    @When("Get a Playlist")
    public void get_a_playlist(){
        getPlaylistById(myPlaylistID);
    }

    @When("Get Track")
    public void get_track(){
        Track myTrack = getTrackById(trackID);
        assertNotNull(myTrack);
    }
    @When("Get Artist")
    public void get_artist(){
        Artist myArtist = getArtistById(artistID);
        assertNotNull(myArtist);
    }

    @When("Get Album")
    public void get_album(){
        Album myAlbum = getAlbumById(albumID);
        assertNotNull(myAlbum);
    }

    @When("Get a Playlist FailTest")
    public void get_a_playlist_failtest(){
        Playlist myFakePlaylist = getPlaylistById_FailTest(fakePlaylistID);
        assertNull(myFakePlaylist);
    }

    @When("Add Track To Playlist")
    public void add_track_to_playlist(){
        int nbTracksInMyPlaylist = getPlaylistById(myPlaylistID).getTracks().getTotal();
        AddTracksToPlaylist(userID,myPlaylistID,tracksUris);
        assertEquals(getPlaylistById(myPlaylistID).getTracks().getItems().length,nbTracksInMyPlaylist+ 2);
    }

    @When("Get Playlist Tracks")
    public void getPlaylistTracksById(){
        Paging<PlaylistTrack> myPlaylistTracks = getPlaylistTracksById(myPlaylistID);
        assertNotNull(myPlaylistTracks);
    }

    @When("Create Playlist")
    public void create_playlist(){
        int nbPlaylist = getMyCurrentUsersPlaylisst().getItems().length;
        CretaePlaylist(userID,myNewPlaylistName);
        assertEquals(getMyCurrentUsersPlaylisst().getItems().length,nbPlaylist+1);
    }

    @When("Create Playlist FailTest")
    public void create_playlist_failtest(){
        CretaePlaylistFailTest(myNewPlaylistName);
    }

    @When("Change Playlist Details")
    public void change_playlist_details(){
        ChangePlaylistDetails(myPlaylistID,newNameForChangePlaylistDetail,newDescriptionForChangePlaylistDetail);
        assertEquals(getPlaylistById(myPlaylistID).getName(),newNameForChangePlaylistDetail);
    }

    @When("Change Playlist Details FailTest")
    public void change_playlist_details_failtest(){
        ChangePlaylistDetailsFailTest(fakePlaylistID,newNameForChangePlaylistDetail,newDescriptionForChangePlaylistDetail);
    }
}
