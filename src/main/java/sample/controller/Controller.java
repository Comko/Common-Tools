package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.apache.commons.lang3.StringUtils;
import sample.utils.Base64Utils;
import sample.utils.JasyptUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    private TextArea jasyptResult;

    @FXML
    private TextField jasyptInput;

    @FXML
    private TextField jasyptSalt;

    @FXML
    protected TextArea history;

    @FXML
    private TextArea base64Result;

    @FXML
    private TextArea base64Original;

    @FXML
    private TextField regularInput;

    @FXML
    private TextField regularExpression;

    @FXML
    private Text regularResult;

    public void jasyptEncrypt() {

        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------Java Jasypt Encrypt--------------------------");
        String salt = jasyptSalt.getText();
        if (StringUtils.isEmpty(salt)) {
            historyList.add("Java Jasypt Salt is Blank");
            history.setText(returnHistoryValue(historyList));
            return;
        }
        String input = jasyptInput.getText();
        try {
            String outPut = JasyptUtils.encryptPwd(salt, input);
            historyList.add("Encrypt");
            historyList.add("Salt: " + salt);
            historyList.add("Input: " + input);
            historyList.add("Result: " + outPut);
            jasyptResult.setText(outPut);
        } catch (Exception e) {
            jasyptResult.setText("Encrypt error");
            historyList.add("Encrypt error");
        }
        history.setText(returnHistoryValue(historyList));
    }

    public void jasyptDecrypt() {

        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------Java Jasypt Decrypt--------------------------");
        String salt = jasyptSalt.getText();
        if (StringUtils.isEmpty(salt)) {
            historyList.add("Java Jasypt Salt is Blank");
            history.setText(returnHistoryValue(historyList));
            return;
        }
        String input = jasyptInput.getText();
        try {
            String outPut = JasyptUtils.decyptPwd(salt, input);
            historyList.add("Decrypt");
            historyList.add("Salt: " + salt);
            historyList.add("Input: " + input);
            historyList.add("Result: " + outPut);
            jasyptResult.setText(outPut);
        } catch (Exception e) {
            jasyptResult.setText("Decrypt error");
            historyList.add("Decrypt error");
        }
        history.setText(returnHistoryValue(historyList));
    }

    public void jasypt32BitSaltRandom() {
        int len = 32;
        char[] chars = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" +
                "1234567890!@#$%^&*()_+").toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < len; i++){
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        jasyptSalt.setText(sb.toString());
    }

    public void jasypt64BitSaltRandom() {
        int len = 64;
        char[] chars = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" +
                "1234567890!@#$%^&*()_+").toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < len; i++){
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        jasyptSalt.setText(sb.toString());
    }

    public void base64Encrypt(){
        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------Base64 Encode--------------------------");

        try {
            String value = Base64Utils.encryptBASE64(base64Original.getText());
            base64Result.setText(value);
            historyList.add("Encode");
            historyList.add("Original: " + base64Original.getText());
            historyList.add("Result: " + base64Result.getText());
        } catch (Exception e) {
            base64Result.setText("Base64 Encrypt Error");
        }
        history.setText(returnHistoryValue(historyList));
    }

    public void base64Decrypt(){
        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------Base64 Decode--------------------------");

        try {
            String value = new String(Base64Utils.decryptBASE64(base64Result.getText()));
            base64Original.setText(value);
            historyList.add("Decode");
            historyList.add("Original: " + base64Result.getText());
            historyList.add("Result: " + base64Original.getText());
        } catch (Exception e) {
            base64Original.setText("Base64 Decrypt Error");
        }
        history.setText(returnHistoryValue(historyList));
    }

    public void base64Change() {
        String o = base64Original.getText();
        String r = base64Result.getText();

        base64Original.setText(r);
        base64Result.setText(o);
    }

    public void regularCheck() {
        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------Regular Check--------------------------");

        String v = regularExpression.getText();
        if (StringUtils.isEmpty(v)) {
            regularResult.setText("Blank");
            regularResult.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
            regularResult.setFill(Color.RED);
            historyList.add("Regular expression blank");
        } else {
            try {
                String v1 = regularInput.getText();
                Pattern pattern = Pattern.compile(v);
                boolean flag = pattern.matcher(v1).matches();
                if (flag) {
                    regularResult.setText("Passed");
                    regularResult.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
                    regularResult.setFill(Color.GREEN);
                } else {
                    regularResult.setText("Fail");
                    regularResult.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
                    regularResult.setFill(Color.RED);
                }
                historyList.add("Regular Check");
                historyList.add("Regular expression: " + regularExpression.getText());
                historyList.add("Input: " + regularInput.getText());
                historyList.add("Check result: " + regularResult.getText());
            } catch (Exception e) {
                historyList.add("Regular Check Error");
            }
        }
        history.setText(returnHistoryValue(historyList));
    }

    public String returnHistoryValue (List<String> historyList) {
        String historyValue = "";
        int maxLen = 100;
        int deleteCount = historyList.size() - maxLen;
        int loopCount = 0;
        if (historyList.size() > maxLen) {
            Iterator<String> iterator = historyList.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
                loopCount ++;
                if (deleteCount == loopCount) {
                    break;
                }
            }
        }
        for (String str : historyList) {
            historyValue = historyValue.concat(str).concat("\n");
        }
        historyValue = historyValue.substring(0, historyValue.lastIndexOf("\n"));
        return historyValue;
    }

    public void cleanHistory() {
        history.clear();
    }

    public void about() {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About Common Tools");
        about.setHeaderText("Common Tools V1.1");
        about.setContentText("V1.1" + "\n" + "1:Change jasypt view;"
                + "\n" + "2:Add Base64;"
                + "\n" + "3:Add Regular check;");
        about.show();
    }
}
