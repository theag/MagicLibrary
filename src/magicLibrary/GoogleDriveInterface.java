/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicLibrary;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author nbp184
 */
public class GoogleDriveInterface {
    
    private static final String redirect_uri = "urn:ietf:wg:oauth:2.0:oob";
    private static final String[] scope_list = {"https://www.googleapis.com/auth/drive.appdata"};
    
    private final Drive service;
    
    public GoogleDriveInterface() throws IOException {
        service = getService();
    }
    
    private Drive getService() throws IOException {
        NetHttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        GoogleClientSecrets secrets = GoogleClientSecrets.load(jsonFactory, new FileReader("credentials.json"));
        ArrayList<String> scopes = new ArrayList<>();
        for(int i = 0; i < scope_list.length; i++) {
            scopes.add(scope_list[i]);
        }
        GoogleAuthorizationCodeFlow.Builder builder = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, secrets, scopes);
        builder.setDataStoreFactory(new FileDataStoreFactory(new java.io.File("credentials")));
        GoogleAuthorizationCodeFlow gAuth = builder.build();
        Credential credential = gAuth.loadCredential("admin");
        if(credential == null) {
            GoogleAuthorizationCodeRequestUrl url = gAuth.newAuthorizationUrl();
            url.setRedirectUri(redirect_uri);
            String code;
            if(Desktop.isDesktopSupported()) {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "You'll need to provide authorization on your Google account to contine. You will be redirected to a website and then provided a code to enter.", "Google Authorization", JOptionPane.WARNING_MESSAGE);
                Desktop.getDesktop().browse(url.toURI());
                code = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter code", "Google Authorization", JOptionPane.QUESTION_MESSAGE);
            } else {
                code = null;
            }
            GoogleAuthorizationCodeTokenRequest request = gAuth.newTokenRequest(code);
            request.setRedirectUri(redirect_uri);
            GoogleTokenResponse response = request.execute();
            credential = gAuth.createAndStoreCredential(response, "admin");
        }
        
        return new Drive.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("Drive API Java CLI")
                .build();
    }
    
    public void uploadFiles(java.io.File cardFile, java.io.File deckFile) throws IOException {
        FileList files = service.files().list()
                .setSpaces("appDataFolder")
                .setFields("files(id, name)")
                .execute();
        String cardId = null;
        String deckId = null;
        for(File f : files.getFiles()) {
            if(f.getName().compareTo(cardFile.getName()) == 0) {
                cardId = f.getId();
            } else if(f.getName().compareTo(deckFile.getName()) == 0) {
                deckId = f.getId();
            }
        }
        if(cardId == null) {
            //make new file
            File f = new File()
                    .setName(cardFile.getName())
                    .setParents(Collections.singletonList("appDataFolder"));
            FileContent fc = new FileContent(null, cardFile);
            service.files().create(f, fc)
                    .execute();
        } else {
            FileContent fc = new FileContent(null, cardFile);
            service.files().update(cardId, null, fc)
                    .execute();
        }
        if(deckId == null) {
            //make new file
            File f = new File()
                    .setName(deckFile.getName())
                    .setParents(Collections.singletonList("appDataFolder"));
            FileContent fc = new FileContent(null, deckFile);
            service.files().create(f, fc)
                    .execute();
        } else {
            FileContent fc = new FileContent(null, deckFile);
            service.files().update(deckId, null, fc)
                    .execute();
        }
    }
    
    public void downloadFiles(java.io.File cardFile, java.io.File deckFile) throws IOException {
        FileList files = service.files().list()
                .setSpaces("appDataFolder")
                .setFields("files(id, name)")
                .execute();
        String cardId = null;
        String deckId = null;
        for(File f : files.getFiles()) {
            if(f.getName().compareTo(cardFile.getName()) == 0) {
                cardId = f.getId();
            } else if(f.getName().compareTo(deckFile.getName()) == 0) {
                deckId = f.getId();
            }
        }
        if(cardId != null) {
            FileOutputStream fOut = new FileOutputStream(cardFile);
            service.files().get(cardId)
                    .executeMediaAndDownloadTo(fOut);
        }
        if(deckId != null) {
            FileOutputStream fOut = new FileOutputStream(deckFile);
            service.files().get(deckId)
                    .executeMediaAndDownloadTo(fOut);
        }
    }
    
}
