package com.esens.automation.api.spotify;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SpotifyApiSpec extends TestsDatas {
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
        String pathPrefixe = "src/test/resources/bin/";
        if (System.getProperty("os.name").startsWith("Windows"))
            return pathPrefixe + "chromedriver.exe";
        else
            return pathPrefixe + "chromedriver";
    }

    private ChromeOptions getChromeOptions() {
        System.setProperty("webdriver.chrome.driver",getChromeDriverFilePath());
        ChromeOptions chromeOptions= new ChromeOptions();
        chromeOptions.setHeadless(true);
        chromeOptions.setCapability("chrome.switches","--incognito");
        chromeOptions.addArguments("--kiosk");
        return chromeOptions;
    }

    private ChromeDriver getChromeDriver() {
        return new ChromeDriver(this.getChromeOptions());
    }

    private final String accessToken = getAccessToken();

    public void sendRequest(String Type, String urlEndpoint, String PostOrPutParameters,int expectedReturnCode) {
        System.out.println(Type + " REQUEST :" + PREFIXE_URL + urlEndpoint );
        RestAssured.baseURI = PREFIXE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("User-Agent", USER_AGENT);
        request.header("Authorization", "Bearer "+ accessToken);
        if (Type == "GET"){
            Response response = request.get(urlEndpoint);
            int statusCode = response.getStatusCode();
            Assert.assertEquals(statusCode, 200);
            if (statusCode == 200) System.out.println(ANSI_GREEN + "GET Request return code 200" + ANSI_RESET);
        } else {


            JSONObject requestParams = new JSONObject();
            String[] couple = PostOrPutParameters.split("&");
            for( String keyAndValue : couple) {
                requestParams.put(keyAndValue.split("=")[0],keyAndValue.split("=")[1]);
            }

            request.body(requestParams.toString());
            if (Type == "POST"){
                try {
                    Response response = request.post( urlEndpoint + "?" + PostOrPutParameters);
                    int statusCode = response.getStatusCode();
                    Assert.assertEquals(statusCode, expectedReturnCode);
                    if (statusCode == expectedReturnCode) System.out.println(ANSI_GREEN + "POST Request return code " + expectedReturnCode + ANSI_RESET);
                } catch(Exception e){
                    e.getMessage();
                }
            }

            if (Type == "PUT"){
                try {
                    Response response = request.put( urlEndpoint + "?" + PostOrPutParameters);
                    int statusCode = response.getStatusCode();
                    Assert.assertEquals(statusCode, expectedReturnCode);
                    if (statusCode == expectedReturnCode) System.out.println(ANSI_GREEN + "POST Request return code " + expectedReturnCode + ANSI_RESET);
                } catch(Exception e){
                    e.getMessage();
                }
            }
        }

    }

}
