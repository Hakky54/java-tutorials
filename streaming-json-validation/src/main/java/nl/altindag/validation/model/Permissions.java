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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "admin",
    "maintain",
    "push",
    "triage",
    "pull"
})
@Generated("jsonschema2pojo")
public class Permissions {

    @JsonProperty("admin")
    private Boolean admin;
    @JsonProperty("maintain")
    private Boolean maintain;
    @JsonProperty("push")
    private Boolean push;
    @JsonProperty("triage")
    private Boolean triage;
    @JsonProperty("pull")
    private Boolean pull;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Permissions() {
    }

    public Permissions(Boolean admin, Boolean maintain, Boolean push, Boolean triage, Boolean pull) {
        super();
        this.admin = admin;
        this.maintain = maintain;
        this.push = push;
        this.triage = triage;
        this.pull = pull;
    }

    @JsonProperty("admin")
    public Boolean getAdmin() {
        return admin;
    }

    @JsonProperty("admin")
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @JsonProperty("maintain")
    public Boolean getMaintain() {
        return maintain;
    }

    @JsonProperty("maintain")
    public void setMaintain(Boolean maintain) {
        this.maintain = maintain;
    }

    @JsonProperty("push")
    public Boolean getPush() {
        return push;
    }

    @JsonProperty("push")
    public void setPush(Boolean push) {
        this.push = push;
    }

    @JsonProperty("triage")
    public Boolean getTriage() {
        return triage;
    }

    @JsonProperty("triage")
    public void setTriage(Boolean triage) {
        this.triage = triage;
    }

    @JsonProperty("pull")
    public Boolean getPull() {
        return pull;
    }

    @JsonProperty("pull")
    public void setPull(Boolean pull) {
        this.pull = pull;
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
