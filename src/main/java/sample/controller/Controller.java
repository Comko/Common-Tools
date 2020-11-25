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
import sample.utils.AESUtils;
import sample.utils.Base64Utils;
import sample.utils.JasyptUtils;
import sample.utils.JsonViewUtils;
import sample.utils.TimeUtils;
import sample.utils.UrlUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
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

    @FXML
    private TextField aesKey;

    @FXML
    private TextArea aesInput;

    @FXML
    private TextArea aesResult;

    @FXML
    private TextArea jsonView;

    @FXML
    private TextField timestampInput;

    @FXML
    private TextField timestampResult;

    @FXML
    private TextField timeInput;

    @FXML
    private TextField timeResult;

    @FXML
    private TextArea urlInput;

    @FXML
    private TextArea urlResult;

    private String lastJsonValue;

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

    public void aesEncrypt() {
        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------AES Encrypt--------------------------");
        String key = aesKey.getText();
        if (StringUtils.isEmpty(key)) {
            historyList.add("AES key is Blank");
            history.setText(returnHistoryValue(historyList));
            return;
        }
        String input = aesInput.getText();
        try {
            String outPut = AESUtils.encrypt(input, key);
            historyList.add("Encrypt");
            historyList.add("Key: " + key);
            historyList.add("Input: " + input);
            historyList.add("Result: " + outPut);
            aesResult.setText(outPut);
        } catch (Exception e) {
            aesResult.setText("Encrypt error");
            historyList.add("Encrypt error");
        }
        history.setText(returnHistoryValue(historyList));
    }

    public void aesDecrypt() {
        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------AES Decrypt--------------------------");
        String key = aesKey.getText();
        if (StringUtils.isEmpty(key)) {
            historyList.add("AES key is Blank");
            history.setText(returnHistoryValue(historyList));
            return;
        }
        String input = aesInput.getText();
        try {
            String outPut = AESUtils.decrypt(input, key);
            historyList.add("Decrypt");
            historyList.add("Key: " + key);
            historyList.add("Input: " + input);
            historyList.add("Result: " + outPut);
            aesResult.setText(outPut);
        } catch (Exception e) {
            aesResult.setText("Decrypt error");
            historyList.add("Decrypt error");
        }
        history.setText(returnHistoryValue(historyList));
    }

    public void jsonView() {
        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------Json View--------------------------");
        String value = jsonView.getText();
        try {
            historyList.add("Json View");
            if (StringUtils.isNotEmpty(value) && !value.equals(lastJsonValue)) {
                String result = JsonViewUtils.formatJson(value);
                historyList.add("Result: " + result);
                jsonView.setText(result);
                lastJsonValue = result;
            } else {
                historyList.add("Result: " + value);
                jsonView.setText(value);
            }
        } catch (Exception e) {
            jsonView.setText(value);
            historyList.add("Json view error");
        }
        history.setText(returnHistoryValue(historyList));
    }

    public void timestampTransfer() {
        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------Time--------------------------");
        try {
            String input = timestampInput.getText();
            String result = TimeUtils.stampToTime(input);
            timestampResult.setText(result);
            historyList.add("Stamp to Date");
            historyList.add("Stamp: " + input);
            historyList.add("Date: " + result);
        } catch (Exception e) {
            historyList.add("stamp to date error");
        }
        history.setText(returnHistoryValue(historyList));
    }

    public void timesTransfer() {
        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------Time--------------------------");
        try {
            String input = timeInput.getText();
            String result = TimeUtils.dateToStamp(input);
            timeResult.setText(result);
            historyList.add("Date to Stamp");
            historyList.add("Date: " + input);
            historyList.add("Stamp: " + result);
        } catch (Exception e) {
            historyList.add("date to stamp error");
        }
        history.setText(returnHistoryValue(historyList));
    }

    public void urlEncode() {
        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------URL encode/decode--------------------------");
        try {
            String input = urlInput.getText();
            String result = UrlUtils.getURLEncoderString(input);
            urlResult.setText(result);
            historyList.add("URL encode");
            historyList.add("Input: " + input);
            historyList.add("Result: " + result);
        } catch (Exception e) {
            historyList.add("URL encode error");
        }
        history.setText(returnHistoryValue(historyList));
    }

    public void urlDecode() {
        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------URL encode/decode--------------------------");
        try {
            String input = urlInput.getText();
            String result = UrlUtils.URLDecoderString(input);
            urlResult.setText(result);
            historyList.add("URL decode");
            historyList.add("Input: " + input);
            historyList.add("Result: " + result);
        } catch (Exception e) {
            historyList.add("URL decode error");
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
                + "\n" + "3:Add Regular check;"

        + "\n" + "V1.2" + "\n" + "1:Add AES;"
                + "\n" + "2:Add Json viewer;"
                + "\n" + "3:Add Time;");
        about.show();
    }
}
