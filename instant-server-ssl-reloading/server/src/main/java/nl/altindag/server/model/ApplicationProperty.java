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
package nl.altindag.server.model;

import java.util.Arrays;

public class ApplicationProperty {

    private int serverPort;
    private boolean sslClientAuth;
    private String keystorePath;
    private char[] keystorePassword;
    private String truststorePath;
    private char[] truststorePassword;

    public ApplicationProperty withServerPort(int serverPort) {
        this.serverPort = serverPort;
        return this;
    }

    public ApplicationProperty withSslClientAuth(boolean sslClientAuth) {
        this.sslClientAuth = sslClientAuth;
        return this;
    }

    public ApplicationProperty withKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
        return this;
    }

    public ApplicationProperty withKeystorePassword(char[] keystorePassword) {
        this.keystorePassword = keystorePassword;
        return this;
    }

    public ApplicationProperty withTruststorePath(String truststorePath) {
        this.truststorePath = truststorePath;
        return this;
    }

    public ApplicationProperty withTruststorePassword(char[] truststorePassword) {
        this.truststorePassword = truststorePassword;
        return this;
    }

    public int getServerPort() {
        return serverPort;
    }

    public boolean isSslClientAuth() {
        return sslClientAuth;
    }

    public String getKeystorePath() {
        return keystorePath;
    }

    public char[] getKeystorePassword() {
        return keystorePassword;
    }

    public String getTruststorePath() {
        return truststorePath;
    }

    public char[] getTruststorePassword() {
        return truststorePassword;
    }

    @Override
    public String toString() {
        return "ApplicationProperty{" +
                "serverPort='" + serverPort + '\'' +
                ", sslClientAuth=" + sslClientAuth +
                ", keystorePath='" + keystorePath + '\'' +
                ", keystorePassword=" + Arrays.toString(keystorePassword) +
                ", truststorePath='" + truststorePath + '\'' +
                ", truststorePassword=" + Arrays.toString(truststorePassword) +
                '}';
    }

}
