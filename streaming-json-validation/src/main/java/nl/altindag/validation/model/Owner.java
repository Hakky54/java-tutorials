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

import java.net.URI;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


/**
 * Simple User
 * <p>
 * A GitHub user.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "email",
    "login",
    "id",
    "node_id",
    "avatar_url",
    "gravatar_id",
    "url",
    "html_url",
    "followers_url",
    "following_url",
    "gists_url",
    "starred_url",
    "subscriptions_url",
    "organizations_url",
    "repos_url",
    "events_url",
    "received_events_url",
    "type",
    "site_admin",
    "starred_at",
    "user_view_type"
})
@Generated("jsonschema2pojo")
public class Owner {

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("login")
    @NotNull
    private String login;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    @NotNull
    private Long id;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("node_id")
    @NotNull
    private String nodeId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("avatar_url")
    @NotNull
    private URI avatarUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("gravatar_id")
    @NotNull
    private String gravatarId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("url")
    @NotNull
    private URI url;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("html_url")
    @NotNull
    private URI htmlUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("followers_url")
    @NotNull
    private URI followersUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("following_url")
    @NotNull
    private String followingUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("gists_url")
    @NotNull
    private String gistsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("starred_url")
    @NotNull
    private String starredUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("subscriptions_url")
    @NotNull
    private URI subscriptionsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("organizations_url")
    @NotNull
    private URI organizationsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("repos_url")
    @NotNull
    private URI reposUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("events_url")
    @NotNull
    private String eventsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("received_events_url")
    @NotNull
    private URI receivedEventsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    @NotNull
    private String type;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("site_admin")
    @NotNull
    private Boolean siteAdmin;
    @JsonProperty("starred_at")
    private String starredAt;
    @JsonProperty("user_view_type")
    private String userViewType;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Owner() {
    }

    public Owner(String name, String email, String login, Long id, String nodeId, URI avatarUrl, String gravatarId, URI url, URI htmlUrl, URI followersUrl, String followingUrl, String gistsUrl, String starredUrl, URI subscriptionsUrl, URI organizationsUrl, URI reposUrl, String eventsUrl, URI receivedEventsUrl, String type, Boolean siteAdmin, String starredAt, String userViewType) {
        super();
        this.name = name;
        this.email = email;
        this.login = login;
        this.id = id;
        this.nodeId = nodeId;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.followersUrl = followersUrl;
        this.followingUrl = followingUrl;
        this.gistsUrl = gistsUrl;
        this.starredUrl = starredUrl;
        this.subscriptionsUrl = subscriptionsUrl;
        this.organizationsUrl = organizationsUrl;
        this.reposUrl = reposUrl;
        this.eventsUrl = eventsUrl;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
        this.siteAdmin = siteAdmin;
        this.starredAt = starredAt;
        this.userViewType = userViewType;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("login")
    @Pattern(regexp="^[A-z]+$")
    @Size(min=1,max=10)
    public String getLogin() {
        return login;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("node_id")
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("node_id")
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("avatar_url")
    public URI getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("avatar_url")
    public void setAvatarUrl(URI avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("gravatar_id")
    public String getGravatarId() {
        return gravatarId;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("gravatar_id")
    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("url")
    public URI getUrl() {
        return url;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("url")
    public void setUrl(URI url) {
        this.url = url;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("html_url")
    public URI getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("html_url")
    public void setHtmlUrl(URI htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("followers_url")
    public URI getFollowersUrl() {
        return followersUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("followers_url")
    public void setFollowersUrl(URI followersUrl) {
        this.followersUrl = followersUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("following_url")
    public String getFollowingUrl() {
        return followingUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("following_url")
    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("gists_url")
    public String getGistsUrl() {
        return gistsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("gists_url")
    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("starred_url")
    public String getStarredUrl() {
        return starredUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("starred_url")
    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("subscriptions_url")
    public URI getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("subscriptions_url")
    public void setSubscriptionsUrl(URI subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("organizations_url")
    public URI getOrganizationsUrl() {
        return organizationsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("organizations_url")
    public void setOrganizationsUrl(URI organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("repos_url")
    public URI getReposUrl() {
        return reposUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("repos_url")
    public void setReposUrl(URI reposUrl) {
        this.reposUrl = reposUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("events_url")
    public String getEventsUrl() {
        return eventsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("events_url")
    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("received_events_url")
    public URI getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("received_events_url")
    public void setReceivedEventsUrl(URI receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("site_admin")
    public Boolean getSiteAdmin() {
        return siteAdmin;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("site_admin")
    public void setSiteAdmin(Boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    @JsonProperty("starred_at")
    public String getStarredAt() {
        return starredAt;
    }

    @JsonProperty("starred_at")
    public void setStarredAt(String starredAt) {
        this.starredAt = starredAt;
    }

    @JsonProperty("user_view_type")
    public String getUserViewType() {
        return userViewType;
    }

    @JsonProperty("user_view_type")
    public void setUserViewType(String userViewType) {
        this.userViewType = userViewType;
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
