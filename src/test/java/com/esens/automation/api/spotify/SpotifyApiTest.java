package com.esens.automation.api.spotify;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.*;
import java.io.IOException;
import io.restassured.specification.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.json.JSONObject;

class SpotifyApiTest extends SpotifyApiSpec{

    public void getAlbumById(String albumID){
        sendRequest("GET",GET_ALBUM_ENDPOINT(albumID),null,400);
    }

    public void getPlaylistById_FailTest(String PlaylistID){
        sendRequest("GET",GET_PLAYLIST_ENDPOINT(PlaylistID),null,400);
    }

    public void getPlaylistTracksById(String PlaylistID){
        sendRequest("GET",GET_PLAYLIST_TRACKS_ENDPOINT(PlaylistID),null,200);

    }

    public void getPlaylistById(String PlaylistID){
        sendRequest("GET",GET_PLAYLIST_ENDPOINT(PlaylistID),null,200);
    }

    public void getTrackById(String TrackID){
        sendRequest("GET",GET_TRACK_ENDPOINT(TrackID),null,200);
    }

    public void getArtistById(String arstistID){
        sendRequest("GET",GET_ARTIST_ENDPOINT(artistID),null,200);
    }

    public void getMyCurrentUsersPlaylisst(){
        sendRequest("GET",GET_LIST_OF_CURENT_USER_PLAYLIST_ENDPOINT(),null,200);
    }
/*
    public void ChangePlaylistDetailsFailTest(String PlaylistId, String newNamme, String newDescription){
        {ChangePlaylistsDetailsRequest changePlaylistsDetails =  spotifyApi.changePlaylistsDetails(PlaylistId)
                .name(newNamme)
                .description(newDescription)
                .build();
            try {
                changePlaylistsDetails.execute();
            } catch (IOException | SpotifyWebApiException e) {
                assertEquals(e.getMessage(),"Invalid playlist Id");
            }
        }
    }

*/

    public void ChangePlaylistDetails(String PlaylistId, String POST_PARAMETERS){
        sendRequest("PUT",CHANGE_TO_PLAYLIST_DETAILS_ENDPOINT(PlaylistId),POST_PARAMETERS,200);
    }
    public void AddTracksToPlaylist(String PlaylistID, String Uris){
        sendRequest("POST",ADD_TRACKS_TO_PLAYLIST_ENDPOINT(PlaylistID),Uris,201);
    }

    public void CreatePlaylist(String POST_PARAMETERS){
        sendRequest("POST",CREATE_PLAYLIST_ENDPOINT(),POST_PARAMETERS,201);
    }
}