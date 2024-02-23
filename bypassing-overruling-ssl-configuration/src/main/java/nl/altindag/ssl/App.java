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
package nl.altindag.ssl;

import nl.altindag.ssl.util.ProviderUtils;

import java.security.Provider;
import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        SSLFactory sslFactory = SSLFactory.builder()
                .withLoggingTrustMaterial()
                .withUnsafeTrustMaterial()
                .build();

        Provider provider = ProviderUtils.create(sslFactory);
        Security.insertProviderAt(provider, 1);

        String url = "jdbc:mysql://localhost:33060/mysql?verifyServerCertificate=true&useSSL=true&requireSSL=true";
        try (Connection connection = DriverManager.getConnection(url, "root", "secret")) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        } finally {
            Security.removeProvider("Fenix");
        }
    }

}
