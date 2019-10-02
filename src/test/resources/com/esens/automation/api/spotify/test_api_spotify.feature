Feature: Tests API Spotify
  Description: Une série de tests de l'API Spotify

  Scenario: Playlist
   Given Get a Playlist
   And Get a Playlist FailTest
   And Get Playlist Tracks
   And Create Playlist
   And Create Playlist FailTest
   And Add Track To Playlist
   And Change Playlist Details
   And Change Playlist Details FailTest
   And Get Track
   And Get Artist
   And Get Album



