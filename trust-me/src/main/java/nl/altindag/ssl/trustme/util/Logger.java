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
package nl.altindag.ssl.trustme.util;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private static final String LINE_BREAK = "\n";

    private Logger() {}

    private static List<String> logMessages = new ArrayList<>();
    private static SimpleStringProperty logContainer = new SimpleStringProperty("");

    public static synchronized void log(String logMessage) {
        Platform.runLater(() -> {
            if (!logMessages.isEmpty() && logMessages.get(logMessages.size() - 1).contains(logMessage)) {
                return;
            }

            logMessages.add(logMessage);
            logContainer.setValue(logContainer.getValue() + logMessage + LINE_BREAK);
        });
    }

    public static SimpleStringProperty logContainerProperty() {
        return logContainer;
    }

}
