package com.esens.automation.api.spotify;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.playlists.*;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.*;
import static org.junit.Assert.*;
import java.io.IOException;

class SpotifyApiSpec extends TestsDatas{
    SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientID)
            .setClientSecret(clientSecret)
            .setAccessToken(getAccessToken())
            .build();

    private String getAccessToken() {
        ChromeDriver chromeDriver = getChromeDriver();
        chromeDriver.get("https://accounts.spotify.com/authorize?client_id=" + clientID +"&response_type=token&redirect_uri=http://www.example.com/postman/redirect&state=123&scope=playlist-modify&show_dialog=false");
        chromeDriver.findElement(By.id("login-username")).sendKeys(loginUserName);
        chromeDriver.findElement(By.id("login-password")).sendKeys(loginPassword);
        chromeDriver.findElement(By.id("login-button")).click();
        try {Thread.sleep(3000);} catch(InterruptedException e){System.out.println("Tiemout error !");}
        String myACtualURL = chromeDriver.executeScript("return window.location.href").toString();
        String myAccessToken = myACtualURL.split("access_token=")[1].split("&token_type")[0];
        chromeDriver.close();
        return myAccessToken;
    }

    private String getChromeDriverFilePath() {
        String pathPrefixe = "src/webDriver/";
        if (System.getProperty("os.name").startsWith("Windows"))
            return pathPrefixe + "chromedriver.exe";
        else
            return pathPrefixe + "chromedriver";
    }

    private ChromeOptions getChromeOptions() {
        System.setProperty("webdriver.chrome.driver",getChromeDriverFilePath());
        ChromeOptions chromeOptions= new ChromeOptions();
        chromeOptions.setHeadless(false);
        chromeOptions.setCapability("chrome.switches","--incognito");
        chromeOptions.addArguments("--kiosk");
        return chromeOptions;
    }

    private ChromeDriver getChromeDriver() {
        return new ChromeDriver(this.getChromeOptions());
    }

    public Album getAlbumById(String albumID){
        {GetAlbumRequest myAlbum = spotifyApi.getAlbum(albumID).build();
            try {
                return myAlbum.execute();
            } catch (IOException | SpotifyWebApiException e) {
                System.out.println("Error: " + e.getMessage());
            }
            return null;
        }
    }

    public Playlist getPlaylistById(String PlaylistID){
        {GetPlaylistRequest myPlaylist = spotifyApi.getPlaylist(PlaylistID).build();
            try {
                return myPlaylist.execute();
            } catch (IOException | SpotifyWebApiException e) {
                System.out.println("Error: " + e.getMessage());
            }
            return null;
        }
    }
    public Playlist getPlaylistById_FailTest(String PlaylistID){
        {GetPlaylistRequest myPlaylist = spotifyApi.getPlaylist(PlaylistID).build();
            try {
                return myPlaylist.execute();
            } catch (IOException | SpotifyWebApiException e) {
                assertEquals("Invalid playlist Id",e.getMessage());
            }
            return null;
        }
    }

    public Paging<PlaylistTrack> getPlaylistTracksById(String PlaylistID){
        {
            GetPlaylistsTracksRequest myPlaylist = spotifyApi.getPlaylistsTracks(PlaylistID).build();
            try {
                return myPlaylist.execute();
            } catch (IOException | SpotifyWebApiException e) {
                System.out.println("Error: " + e.getMessage());
            }
            return null;
        }
    }

    Artist getArtistById(String ArtistID){
        {GetArtistRequest myArtist = spotifyApi.getArtist(ArtistID).build();
            try {
                return myArtist.execute();
            } catch (IOException | SpotifyWebApiException e) {
                System.out.println("Error: " + e.getMessage());
            }
            return null;
        }
    }

    public Track getTrackById(String TrackID){
        {
            GetTrackRequest myTrack = spotifyApi.getTrack(TrackID).build();
            try {
                return myTrack.execute();
            } catch (IOException | SpotifyWebApiException e) {
                System.out.println("Error: " + e.getMessage());
            }
            return null;
        }
    }

    public Paging<PlaylistSimplified> getMyCurrentUsersPlaylisst(){
        {
            GetListOfCurrentUsersPlaylistsRequest myCurrentUsersPlaylist = spotifyApi.getListOfCurrentUsersPlaylists().build();
            try {
                return myCurrentUsersPlaylist.execute();
            } catch (IOException | SpotifyWebApiException e) {
                System.out.println("Error: " + e.getMessage());
            }
            return null;
        }
    }

    public void CretaePlaylist(String UserID , String PlaylistName){
        {CreatePlaylistRequest myCreatePlaylist =  spotifyApi.createPlaylist(UserID,myPlaylistName).build();
            try {
                myCreatePlaylist.execute();
            } catch (IOException | SpotifyWebApiException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    public void CretaePlaylistFailTest(String PlaylistName){
        {CreatePlaylistRequest myCreatePlaylist =  spotifyApi.createPlaylist(fakeUserID,PlaylistName).build();
            try {
                myCreatePlaylist.execute();
            } catch (IOException | SpotifyWebApiException e) {
                assertEquals(e.getMessage(),"You cannot create a playlist for another user.");
            }
        }
    }

    public void ChangePlaylistDetails(String PlaylistId, String newNamme, String newDescription){
        {ChangePlaylistsDetailsRequest changePlaylistsDetails =  spotifyApi.changePlaylistsDetails(PlaylistId)
                .name(newNamme)
                .description(newDescription)
                .build();
            try {
                changePlaylistsDetails.execute();
            } catch (IOException | SpotifyWebApiException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

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

    public void AddTracksToPlaylist(String UserID , String PlaylistID, String [] Uris){
        {AddTracksToPlaylistRequest addTracksToMyPlaylist =  spotifyApi.addTracksToPlaylist(UserID,PlaylistID,Uris).build();
            try {
                addTracksToMyPlaylist.execute();
            } catch (IOException | SpotifyWebApiException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}

