package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.Iterator;
import java.util.List;

/**
 * @author zhangpenghao
 * @ClassName : HistoryController
 * @description: history
 * @date 2020-11-18 15:22
 */
public class HistoryController {

    @FXML
    protected TextArea history;

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
}
