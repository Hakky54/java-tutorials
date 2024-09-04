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
package nl.altindag.ssl.trustme;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.function.Function;

@SpringBootApplication
public class App extends Application {

    private static final String TITLE = "Trust Me";
    private ConfigurableApplicationContext applicationContext;
    private final Function<String, FXMLLoader> fxmlLoaderFunction = fxml -> new FXMLLoader(this.getClass().getResource(fxml));

    private Parent root;

    @Override
    public void init() throws IOException {
        applicationContext = new SpringApplicationBuilder(App.class)
                .headless(false)
                .run(getParameters().getRaw().toArray(String[]::new));

        FXMLLoader fxmlLoader = fxmlLoaderFunction.apply("/mainscreen.fxml");
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        root = fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(root);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(400);
        stage.setResizable(false);

        stage.show();
    }

    @Override
    public void stop() {
        Platform.exit();
        applicationContext.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}