/*
 * Copyright 2022 Thunderberry.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.altindag.ssl.trustme.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import nl.altindag.ssl.trustme.exception.PingException;
import nl.altindag.ssl.trustme.service.PingService;
import nl.altindag.ssl.trustme.util.Logger;
import org.springframework.stereotype.Controller;

import javax.net.ssl.SSLHandshakeException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.CENTER;

@Controller
public class SearchController implements Initializable {

    @FXML
    private TextField urlField;
    @FXML
    private TextArea loggerArea;

    private final PingService pingService;

    public SearchController(PingService pingService) {
        this.pingService = pingService;
    }

    @FXML
    public void initialize() {
        urlField.setAlignment(CENTER);

        urlField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                urlField.setText(oldValue);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loggerArea.textProperty().bind(Logger.logContainerProperty());
        Logger.logContainerProperty().addListener((observable, newValue, oldValue) -> {
            loggerArea.selectPositionCaret(loggerArea.getLength());
            loggerArea.deselect();
        });
    }

    @FXML
    public void onEnter(ActionEvent event) {
        String url = urlField.getText().toLowerCase();
        try {
            pingService.ping(url);
            Logger.log(String.format("Certificate of [%s] is already trusted", url));
        } catch (PingException pingException) {
            if (pingException.getCause() instanceof SSLHandshakeException) {
                Logger.log(String.format("Certificate of [%s] is not trusted", url));
            }
        }
    }

}
