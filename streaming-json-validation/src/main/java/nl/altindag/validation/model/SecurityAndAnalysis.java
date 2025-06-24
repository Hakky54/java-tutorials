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

package nl.altindag.validation.model;

import java.util.LinkedHashMap;
import java.util.Map;
import jakarta.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "advanced_security",
    "code_security",
    "dependabot_security_updates",
    "secret_scanning",
    "secret_scanning_push_protection",
    "secret_scanning_non_provider_patterns",
    "secret_scanning_ai_detection"
})
@Generated("jsonschema2pojo")
public class SecurityAndAnalysis {

    @JsonProperty("advanced_security")
    @Valid
    private AdvancedSecurity advancedSecurity;
    @JsonProperty("code_security")
    @Valid
    private CodeSecurity codeSecurity;
    /**
     * Enable or disable Dependabot security updates for the repository.
     * 
     */
    @JsonProperty("dependabot_security_updates")
    @JsonPropertyDescription("Enable or disable Dependabot security updates for the repository.")
    @Valid
    private DependabotSecurityUpdates dependabotSecurityUpdates;
    @JsonProperty("secret_scanning")
    @Valid
    private SecretScanning secretScanning;
    @JsonProperty("secret_scanning_push_protection")
    @Valid
    private SecretScanningPushProtection secretScanningPushProtection;
    @JsonProperty("secret_scanning_non_provider_patterns")
    @Valid
    private SecretScanningNonProviderPatterns secretScanningNonProviderPatterns;
    @JsonProperty("secret_scanning_ai_detection")
    @Valid
    private SecretScanningAiDetection secretScanningAiDetection;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public SecurityAndAnalysis() {
    }

    /**
     * 
     * @param dependabotSecurityUpdates
     *     Enable or disable Dependabot security updates for the repository.
     */
    public SecurityAndAnalysis(AdvancedSecurity advancedSecurity, CodeSecurity codeSecurity, DependabotSecurityUpdates dependabotSecurityUpdates, SecretScanning secretScanning, SecretScanningPushProtection secretScanningPushProtection, SecretScanningNonProviderPatterns secretScanningNonProviderPatterns, SecretScanningAiDetection secretScanningAiDetection) {
        super();
        this.advancedSecurity = advancedSecurity;
        this.codeSecurity = codeSecurity;
        this.dependabotSecurityUpdates = dependabotSecurityUpdates;
        this.secretScanning = secretScanning;
        this.secretScanningPushProtection = secretScanningPushProtection;
        this.secretScanningNonProviderPatterns = secretScanningNonProviderPatterns;
        this.secretScanningAiDetection = secretScanningAiDetection;
    }

    @JsonProperty("advanced_security")
    public AdvancedSecurity getAdvancedSecurity() {
        return advancedSecurity;
    }

    @JsonProperty("advanced_security")
    public void setAdvancedSecurity(AdvancedSecurity advancedSecurity) {
        this.advancedSecurity = advancedSecurity;
    }

    @JsonProperty("code_security")
    public CodeSecurity getCodeSecurity() {
        return codeSecurity;
    }

    @JsonProperty("code_security")
    public void setCodeSecurity(CodeSecurity codeSecurity) {
        this.codeSecurity = codeSecurity;
    }

    /**
     * Enable or disable Dependabot security updates for the repository.
     * 
     */
    @JsonProperty("dependabot_security_updates")
    public DependabotSecurityUpdates getDependabotSecurityUpdates() {
        return dependabotSecurityUpdates;
    }

    /**
     * Enable or disable Dependabot security updates for the repository.
     * 
     */
    @JsonProperty("dependabot_security_updates")
    public void setDependabotSecurityUpdates(DependabotSecurityUpdates dependabotSecurityUpdates) {
        this.dependabotSecurityUpdates = dependabotSecurityUpdates;
    }

    @JsonProperty("secret_scanning")
    public SecretScanning getSecretScanning() {
        return secretScanning;
    }

    @JsonProperty("secret_scanning")
    public void setSecretScanning(SecretScanning secretScanning) {
        this.secretScanning = secretScanning;
    }

    @JsonProperty("secret_scanning_push_protection")
    public SecretScanningPushProtection getSecretScanningPushProtection() {
        return secretScanningPushProtection;
    }

    @JsonProperty("secret_scanning_push_protection")
    public void setSecretScanningPushProtection(SecretScanningPushProtection secretScanningPushProtection) {
        this.secretScanningPushProtection = secretScanningPushProtection;
    }

    @JsonProperty("secret_scanning_non_provider_patterns")
    public SecretScanningNonProviderPatterns getSecretScanningNonProviderPatterns() {
        return secretScanningNonProviderPatterns;
    }

    @JsonProperty("secret_scanning_non_provider_patterns")
    public void setSecretScanningNonProviderPatterns(SecretScanningNonProviderPatterns secretScanningNonProviderPatterns) {
        this.secretScanningNonProviderPatterns = secretScanningNonProviderPatterns;
    }

    @JsonProperty("secret_scanning_ai_detection")
    public SecretScanningAiDetection getSecretScanningAiDetection() {
        return secretScanningAiDetection;
    }

    @JsonProperty("secret_scanning_ai_detection")
    public void setSecretScanningAiDetection(SecretScanningAiDetection secretScanningAiDetection) {
        this.secretScanningAiDetection = secretScanningAiDetection;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
