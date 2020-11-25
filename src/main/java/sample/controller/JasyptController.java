package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import sample.utils.JasyptUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author zhangpenghao
 * @ClassName : JasyptController
 * @description: java jasypt
 * @date 2020-11-18 15:20
 */
public class JasyptController extends HistoryController{

    @FXML
    private TextArea jasyptResult;

    @FXML
    private TextField jasyptInput;

    @FXML
    private TextField jasyptSalt;

    public void jasyptEncrypt() {

        List<String> historyList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(history.getText())) {
            historyList = new ArrayList<String>(Arrays.asList(history.getText().split("\n")));
        }
        historyList.add("------------------------Java Jasypt--------------------------");
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
        historyList.add("------------------------Java Jasypt--------------------------");
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
}
