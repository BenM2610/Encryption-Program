package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EncryptionDissController {
    
    @FXML
    private Label title;
    
    @FXML
    private TextField inputField;
    
    @FXML
    private TextField keyField;
    
    @FXML
    private TextField keyOutput;
    
    @FXML
    private TextField resultField;
    
    private Scanner scanner;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;
    private char[] letters;
    
    public EncryptionDissController() {
    }
    
    @FXML
    private void initialize() {
        scanner = new Scanner(System.in);
        new Random();
        list = new ArrayList<Character>();
        shuffledList = new ArrayList<Character>();
        character = ' ';
        
        newKey();
        title.setText("Encryption Program");
    }
    
    private void newKey(){
        character = ' ';
        list.clear();
        shuffledList.clear();
        
        for(int i=32;i<127;i++) {
            list.add(Character.valueOf(character));
            character++;
        }
        
        shuffledList = new ArrayList<Character>(list);
        Collections.shuffle(shuffledList);
        keyOutput.setText(shuffledList.toString());
    }
    
    @FXML
    private void getKey(){
        newKey();
        shuffledList.clear();
        shuffledList.addAll(list);
        Collections.shuffle(shuffledList);
        keyOutput.setText(""); // Clear the text field
        keyOutput.setText(shuffledList.toString());
    }


    
    @FXML
    private void encrypt(){
        String message = inputField.getText();
        
        letters = message.toCharArray();
        
        for(int i =0;i<letters.length;i++) {
            
            for(int j =0;j<list.size();j++) {
                if(letters[i]==list.get(j)) {
                    letters[i]=shuffledList.get(j);
                    break;
                }
            }
        }
        resultField.setText(new String(letters));
    }
    
    @FXML
    private void decrypt(){
        String encryptedMessage = inputField.getText();
        String userKey = keyField.getText();
        
        if (userKey.length() == list.size()) {
            shuffledList.clear();
            for (char c : userKey.toCharArray()) {
                shuffledList.add(c);
            }
        }
        
        char[] encryptedLetters = encryptedMessage.toCharArray();
        char[] decryptedLetters = new char[encryptedLetters.length];
        
        for(int i =0;i<encryptedLetters.length;i++) {
            for(int j =0;j<shuffledList.size();j++) {
                if(encryptedLetters[i]==shuffledList.get(j)) {
                    decryptedLetters[i]=list.get(j);
                    break;
                }
            }
        }
        
        // Clear the resultField before setting the decrypted message
        resultField.clear();
        resultField.setText(new String(decryptedLetters));
    }


    @FXML
    private void quit(){
        System.out.println("Thank you, have a nice day bro!");
        System.exit(0);
    }
}
