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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
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
import jakarta.validation.constraints.NotNull;


/**
 * Minimal Repository
 * <p>
 * Minimal Repository
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "node_id",
    "name",
    "full_name",
    "owner",
    "private",
    "html_url",
    "description",
    "fork",
    "url",
    "archive_url",
    "assignees_url",
    "blobs_url",
    "branches_url",
    "collaborators_url",
    "comments_url",
    "commits_url",
    "compare_url",
    "contents_url",
    "contributors_url",
    "deployments_url",
    "downloads_url",
    "events_url",
    "forks_url",
    "git_commits_url",
    "git_refs_url",
    "git_tags_url",
    "git_url",
    "issue_comment_url",
    "issue_events_url",
    "issues_url",
    "keys_url",
    "labels_url",
    "languages_url",
    "merges_url",
    "milestones_url",
    "notifications_url",
    "pulls_url",
    "releases_url",
    "ssh_url",
    "stargazers_url",
    "statuses_url",
    "subscribers_url",
    "subscription_url",
    "tags_url",
    "teams_url",
    "trees_url",
    "clone_url",
    "mirror_url",
    "hooks_url",
    "svn_url",
    "homepage",
    "language",
    "forks_count",
    "stargazers_count",
    "watchers_count",
    "size",
    "default_branch",
    "open_issues_count",
    "is_template",
    "topics",
    "has_issues",
    "has_projects",
    "has_wiki",
    "has_pages",
    "has_downloads",
    "has_discussions",
    "archived",
    "disabled",
    "visibility",
    "pushed_at",
    "created_at",
    "updated_at",
    "permissions",
    "role_name",
    "temp_clone_token",
    "delete_branch_on_merge",
    "subscribers_count",
    "network_count",
    "code_of_conduct",
    "license",
    "forks",
    "open_issues",
    "watchers",
    "allow_forking",
    "web_commit_signoff_required",
    "security_and_analysis",
    "custom_properties"
})
@Generated("jsonschema2pojo")
public class GitHubRepository {

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
    @JsonProperty("name")
    @NotNull
    private String name;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("full_name")
    @NotNull
    private String fullName;
    /**
     * Simple User
     * <p>
     * A GitHub user.
     * (Required)
     * 
     */
    @JsonProperty("owner")
    @JsonPropertyDescription("A GitHub user.")
    @Valid
    @NotNull
    private Owner owner;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("private")
    @NotNull
    private Boolean _private;
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
    @JsonProperty("description")
    @NotNull
    private String description;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("fork")
    @NotNull
    private Boolean fork;
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
    @JsonProperty("archive_url")
    @NotNull
    private String archiveUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("assignees_url")
    @NotNull
    private String assigneesUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("blobs_url")
    @NotNull
    private String blobsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("branches_url")
    @NotNull
    private String branchesUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("collaborators_url")
    @NotNull
    private String collaboratorsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("comments_url")
    @NotNull
    private String commentsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("commits_url")
    @NotNull
    private String commitsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("compare_url")
    @NotNull
    private String compareUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("contents_url")
    @NotNull
    private String contentsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("contributors_url")
    @NotNull
    private URI contributorsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("deployments_url")
    @NotNull
    private URI deploymentsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("downloads_url")
    @NotNull
    private URI downloadsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("events_url")
    @NotNull
    private URI eventsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("forks_url")
    @NotNull
    private URI forksUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("git_commits_url")
    @NotNull
    private String gitCommitsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("git_refs_url")
    @NotNull
    private String gitRefsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("git_tags_url")
    @NotNull
    private String gitTagsUrl;
    @JsonProperty("git_url")
    private String gitUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("issue_comment_url")
    @NotNull
    private String issueCommentUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("issue_events_url")
    @NotNull
    private String issueEventsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("issues_url")
    @NotNull
    private String issuesUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("keys_url")
    @NotNull
    private String keysUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("labels_url")
    @NotNull
    private String labelsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("languages_url")
    @NotNull
    private URI languagesUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("merges_url")
    @NotNull
    private URI mergesUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("milestones_url")
    @NotNull
    private String milestonesUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("notifications_url")
    @NotNull
    private String notificationsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("pulls_url")
    @NotNull
    private String pullsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("releases_url")
    @NotNull
    private String releasesUrl;
    @JsonProperty("ssh_url")
    private String sshUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("stargazers_url")
    @NotNull
    private URI stargazersUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("statuses_url")
    @NotNull
    private String statusesUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("subscribers_url")
    @NotNull
    private URI subscribersUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("subscription_url")
    @NotNull
    private URI subscriptionUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("tags_url")
    @NotNull
    private URI tagsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("teams_url")
    @NotNull
    private URI teamsUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("trees_url")
    @NotNull
    private String treesUrl;
    @JsonProperty("clone_url")
    private String cloneUrl;
    @JsonProperty("mirror_url")
    private String mirrorUrl;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("hooks_url")
    @NotNull
    private URI hooksUrl;
    @JsonProperty("svn_url")
    private String svnUrl;
    @JsonProperty("homepage")
    private String homepage;
    @JsonProperty("language")
    private String language;
    @JsonProperty("forks_count")
    private Long forksCount;
    @JsonProperty("stargazers_count")
    private Long stargazersCount;
    @JsonProperty("watchers_count")
    private Long watchersCount;
    /**
     * The size of the repository, in kilobytes. Size is calculated hourly. When a repository is initially created, the size is 0.
     * 
     */
    @JsonProperty("size")
    @JsonPropertyDescription("The size of the repository, in kilobytes. Size is calculated hourly. When a repository is initially created, the size is 0.")
    private Long size;
    @JsonProperty("default_branch")
    private String defaultBranch;
    @JsonProperty("open_issues_count")
    private Long openIssuesCount;
    @JsonProperty("is_template")
    private Boolean isTemplate;
    @JsonProperty("topics")
    @Valid
    private List<String> topics;
    @JsonProperty("has_issues")
    private Boolean hasIssues;
    @JsonProperty("has_projects")
    private Boolean hasProjects;
    @JsonProperty("has_wiki")
    private Boolean hasWiki;
    @JsonProperty("has_pages")
    private Boolean hasPages;
    @JsonProperty("has_downloads")
    private Boolean hasDownloads;
    @JsonProperty("has_discussions")
    private Boolean hasDiscussions;
    @JsonProperty("archived")
    private Boolean archived;
    @JsonProperty("disabled")
    private Boolean disabled;
    @JsonProperty("visibility")
    private String visibility;
    @JsonProperty("pushed_at")
    private Date pushedAt;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("permissions")
    @Valid
    private Permissions permissions;
    @JsonProperty("role_name")
    private String roleName;
    @JsonProperty("temp_clone_token")
    private String tempCloneToken;
    @JsonProperty("delete_branch_on_merge")
    private Boolean deleteBranchOnMerge;
    @JsonProperty("subscribers_count")
    private Long subscribersCount;
    @JsonProperty("network_count")
    private Long networkCount;
    /**
     * Code Of Conduct
     * <p>
     * Code Of Conduct
     * 
     */
    @JsonProperty("code_of_conduct")
    @JsonPropertyDescription("Code Of Conduct")
    @Valid
    private CodeOfConduct codeOfConduct;
    @JsonProperty("license")
    private License license;
    @JsonProperty("forks")
    private Long forks;
    @JsonProperty("open_issues")
    private Long openIssues;
    @JsonProperty("watchers")
    private Long watchers;
    @JsonProperty("allow_forking")
    private Boolean allowForking;
    @JsonProperty("web_commit_signoff_required")
    private Boolean webCommitSignoffRequired;
    @JsonProperty("security_and_analysis")
    private SecurityAndAnalysis securityAndAnalysis;
    /**
     * The custom properties that were defined for the repository. The keys are the custom property names, and the values are the corresponding custom property values.
     * 
     */
    @JsonProperty("custom_properties")
    @JsonPropertyDescription("The custom properties that were defined for the repository. The keys are the custom property names, and the values are the corresponding custom property values.")
    @Valid
    private CustomProperties customProperties;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public GitHubRepository() {
    }

    /**
     * 
     * @param owner
     *     Simple User. A GitHub user.
     * @param codeOfConduct
     *     Code Of Conduct. Code Of Conduct.
     * @param customProperties
     *     The custom properties that were defined for the repository. The keys are the custom property names, and the values are the corresponding custom property values.
     * @param size
     *     The size of the repository, in kilobytes. Size is calculated hourly. When a repository is initially created, the size is 0.
     */
    public GitHubRepository(Long id, String nodeId, String name, String fullName, Owner owner, Boolean _private, URI htmlUrl, String description, Boolean fork, URI url, String archiveUrl, String assigneesUrl, String blobsUrl, String branchesUrl, String collaboratorsUrl, String commentsUrl, String commitsUrl, String compareUrl, String contentsUrl, URI contributorsUrl, URI deploymentsUrl, URI downloadsUrl, URI eventsUrl, URI forksUrl, String gitCommitsUrl, String gitRefsUrl, String gitTagsUrl, String gitUrl, String issueCommentUrl, String issueEventsUrl, String issuesUrl, String keysUrl, String labelsUrl, URI languagesUrl, URI mergesUrl, String milestonesUrl, String notificationsUrl, String pullsUrl, String releasesUrl, String sshUrl, URI stargazersUrl, String statusesUrl, URI subscribersUrl, URI subscriptionUrl, URI tagsUrl, URI teamsUrl, String treesUrl, String cloneUrl, String mirrorUrl, URI hooksUrl, String svnUrl, String homepage, String language, Long forksCount, Long stargazersCount, Long watchersCount, Long size, String defaultBranch, Long openIssuesCount, Boolean isTemplate, List<String> topics, Boolean hasIssues, Boolean hasProjects, Boolean hasWiki, Boolean hasPages, Boolean hasDownloads, Boolean hasDiscussions, Boolean archived, Boolean disabled, String visibility, Date pushedAt, Date createdAt, Date updatedAt, Permissions permissions, String roleName, String tempCloneToken, Boolean deleteBranchOnMerge, Long subscribersCount, Long networkCount, CodeOfConduct codeOfConduct, License license, Long forks, Long openIssues, Long watchers, Boolean allowForking, Boolean webCommitSignoffRequired, SecurityAndAnalysis securityAndAnalysis, CustomProperties customProperties) {
        super();
        this.id = id;
        this.nodeId = nodeId;
        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
        this._private = _private;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.fork = fork;
        this.url = url;
        this.archiveUrl = archiveUrl;
        this.assigneesUrl = assigneesUrl;
        this.blobsUrl = blobsUrl;
        this.branchesUrl = branchesUrl;
        this.collaboratorsUrl = collaboratorsUrl;
        this.commentsUrl = commentsUrl;
        this.commitsUrl = commitsUrl;
        this.compareUrl = compareUrl;
        this.contentsUrl = contentsUrl;
        this.contributorsUrl = contributorsUrl;
        this.deploymentsUrl = deploymentsUrl;
        this.downloadsUrl = downloadsUrl;
        this.eventsUrl = eventsUrl;
        this.forksUrl = forksUrl;
        this.gitCommitsUrl = gitCommitsUrl;
        this.gitRefsUrl = gitRefsUrl;
        this.gitTagsUrl = gitTagsUrl;
        this.gitUrl = gitUrl;
        this.issueCommentUrl = issueCommentUrl;
        this.issueEventsUrl = issueEventsUrl;
        this.issuesUrl = issuesUrl;
        this.keysUrl = keysUrl;
        this.labelsUrl = labelsUrl;
        this.languagesUrl = languagesUrl;
        this.mergesUrl = mergesUrl;
        this.milestonesUrl = milestonesUrl;
        this.notificationsUrl = notificationsUrl;
        this.pullsUrl = pullsUrl;
        this.releasesUrl = releasesUrl;
        this.sshUrl = sshUrl;
        this.stargazersUrl = stargazersUrl;
        this.statusesUrl = statusesUrl;
        this.subscribersUrl = subscribersUrl;
        this.subscriptionUrl = subscriptionUrl;
        this.tagsUrl = tagsUrl;
        this.teamsUrl = teamsUrl;
        this.treesUrl = treesUrl;
        this.cloneUrl = cloneUrl;
        this.mirrorUrl = mirrorUrl;
        this.hooksUrl = hooksUrl;
        this.svnUrl = svnUrl;
        this.homepage = homepage;
        this.language = language;
        this.forksCount = forksCount;
        this.stargazersCount = stargazersCount;
        this.watchersCount = watchersCount;
        this.size = size;
        this.defaultBranch = defaultBranch;
        this.openIssuesCount = openIssuesCount;
        this.isTemplate = isTemplate;
        this.topics = topics;
        this.hasIssues = hasIssues;
        this.hasProjects = hasProjects;
        this.hasWiki = hasWiki;
        this.hasPages = hasPages;
        this.hasDownloads = hasDownloads;
        this.hasDiscussions = hasDiscussions;
        this.archived = archived;
        this.disabled = disabled;
        this.visibility = visibility;
        this.pushedAt = pushedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.permissions = permissions;
        this.roleName = roleName;
        this.tempCloneToken = tempCloneToken;
        this.deleteBranchOnMerge = deleteBranchOnMerge;
        this.subscribersCount = subscribersCount;
        this.networkCount = networkCount;
        this.codeOfConduct = codeOfConduct;
        this.license = license;
        this.forks = forks;
        this.openIssues = openIssues;
        this.watchers = watchers;
        this.allowForking = allowForking;
        this.webCommitSignoffRequired = webCommitSignoffRequired;
        this.securityAndAnalysis = securityAndAnalysis;
        this.customProperties = customProperties;
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
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("full_name")
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("full_name")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Simple User
     * <p>
     * A GitHub user.
     * (Required)
     * 
     */
    @JsonProperty("owner")
    public Owner getOwner() {
        return owner;
    }

    /**
     * Simple User
     * <p>
     * A GitHub user.
     * (Required)
     * 
     */
    @JsonProperty("owner")
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("private")
    public Boolean getPrivate() {
        return _private;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("private")
    public void setPrivate(Boolean _private) {
        this._private = _private;
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
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("fork")
    public Boolean getFork() {
        return fork;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("fork")
    public void setFork(Boolean fork) {
        this.fork = fork;
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
    @JsonProperty("archive_url")
    public String getArchiveUrl() {
        return archiveUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("archive_url")
    public void setArchiveUrl(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("assignees_url")
    public String getAssigneesUrl() {
        return assigneesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("assignees_url")
    public void setAssigneesUrl(String assigneesUrl) {
        this.assigneesUrl = assigneesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("blobs_url")
    public String getBlobsUrl() {
        return blobsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("blobs_url")
    public void setBlobsUrl(String blobsUrl) {
        this.blobsUrl = blobsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("branches_url")
    public String getBranchesUrl() {
        return branchesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("branches_url")
    public void setBranchesUrl(String branchesUrl) {
        this.branchesUrl = branchesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("collaborators_url")
    public String getCollaboratorsUrl() {
        return collaboratorsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("collaborators_url")
    public void setCollaboratorsUrl(String collaboratorsUrl) {
        this.collaboratorsUrl = collaboratorsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("comments_url")
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("comments_url")
    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("commits_url")
    public String getCommitsUrl() {
        return commitsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("commits_url")
    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("compare_url")
    public String getCompareUrl() {
        return compareUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("compare_url")
    public void setCompareUrl(String compareUrl) {
        this.compareUrl = compareUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("contents_url")
    public String getContentsUrl() {
        return contentsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("contents_url")
    public void setContentsUrl(String contentsUrl) {
        this.contentsUrl = contentsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("contributors_url")
    public URI getContributorsUrl() {
        return contributorsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("contributors_url")
    public void setContributorsUrl(URI contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("deployments_url")
    public URI getDeploymentsUrl() {
        return deploymentsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("deployments_url")
    public void setDeploymentsUrl(URI deploymentsUrl) {
        this.deploymentsUrl = deploymentsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("downloads_url")
    public URI getDownloadsUrl() {
        return downloadsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("downloads_url")
    public void setDownloadsUrl(URI downloadsUrl) {
        this.downloadsUrl = downloadsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("events_url")
    public URI getEventsUrl() {
        return eventsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("events_url")
    public void setEventsUrl(URI eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("forks_url")
    public URI getForksUrl() {
        return forksUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("forks_url")
    public void setForksUrl(URI forksUrl) {
        this.forksUrl = forksUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("git_commits_url")
    public String getGitCommitsUrl() {
        return gitCommitsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("git_commits_url")
    public void setGitCommitsUrl(String gitCommitsUrl) {
        this.gitCommitsUrl = gitCommitsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("git_refs_url")
    public String getGitRefsUrl() {
        return gitRefsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("git_refs_url")
    public void setGitRefsUrl(String gitRefsUrl) {
        this.gitRefsUrl = gitRefsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("git_tags_url")
    public String getGitTagsUrl() {
        return gitTagsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("git_tags_url")
    public void setGitTagsUrl(String gitTagsUrl) {
        this.gitTagsUrl = gitTagsUrl;
    }

    @JsonProperty("git_url")
    public String getGitUrl() {
        return gitUrl;
    }

    @JsonProperty("git_url")
    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("issue_comment_url")
    public String getIssueCommentUrl() {
        return issueCommentUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("issue_comment_url")
    public void setIssueCommentUrl(String issueCommentUrl) {
        this.issueCommentUrl = issueCommentUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("issue_events_url")
    public String getIssueEventsUrl() {
        return issueEventsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("issue_events_url")
    public void setIssueEventsUrl(String issueEventsUrl) {
        this.issueEventsUrl = issueEventsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("issues_url")
    public String getIssuesUrl() {
        return issuesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("issues_url")
    public void setIssuesUrl(String issuesUrl) {
        this.issuesUrl = issuesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("keys_url")
    public String getKeysUrl() {
        return keysUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("keys_url")
    public void setKeysUrl(String keysUrl) {
        this.keysUrl = keysUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("labels_url")
    public String getLabelsUrl() {
        return labelsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("labels_url")
    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("languages_url")
    public URI getLanguagesUrl() {
        return languagesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("languages_url")
    public void setLanguagesUrl(URI languagesUrl) {
        this.languagesUrl = languagesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("merges_url")
    public URI getMergesUrl() {
        return mergesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("merges_url")
    public void setMergesUrl(URI mergesUrl) {
        this.mergesUrl = mergesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("milestones_url")
    public String getMilestonesUrl() {
        return milestonesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("milestones_url")
    public void setMilestonesUrl(String milestonesUrl) {
        this.milestonesUrl = milestonesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("notifications_url")
    public String getNotificationsUrl() {
        return notificationsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("notifications_url")
    public void setNotificationsUrl(String notificationsUrl) {
        this.notificationsUrl = notificationsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("pulls_url")
    public String getPullsUrl() {
        return pullsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("pulls_url")
    public void setPullsUrl(String pullsUrl) {
        this.pullsUrl = pullsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("releases_url")
    public String getReleasesUrl() {
        return releasesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("releases_url")
    public void setReleasesUrl(String releasesUrl) {
        this.releasesUrl = releasesUrl;
    }

    @JsonProperty("ssh_url")
    public String getSshUrl() {
        return sshUrl;
    }

    @JsonProperty("ssh_url")
    public void setSshUrl(String sshUrl) {
        this.sshUrl = sshUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("stargazers_url")
    public URI getStargazersUrl() {
        return stargazersUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("stargazers_url")
    public void setStargazersUrl(URI stargazersUrl) {
        this.stargazersUrl = stargazersUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("statuses_url")
    public String getStatusesUrl() {
        return statusesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("statuses_url")
    public void setStatusesUrl(String statusesUrl) {
        this.statusesUrl = statusesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("subscribers_url")
    public URI getSubscribersUrl() {
        return subscribersUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("subscribers_url")
    public void setSubscribersUrl(URI subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("subscription_url")
    public URI getSubscriptionUrl() {
        return subscriptionUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("subscription_url")
    public void setSubscriptionUrl(URI subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("tags_url")
    public URI getTagsUrl() {
        return tagsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("tags_url")
    public void setTagsUrl(URI tagsUrl) {
        this.tagsUrl = tagsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("teams_url")
    public URI getTeamsUrl() {
        return teamsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("teams_url")
    public void setTeamsUrl(URI teamsUrl) {
        this.teamsUrl = teamsUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("trees_url")
    public String getTreesUrl() {
        return treesUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("trees_url")
    public void setTreesUrl(String treesUrl) {
        this.treesUrl = treesUrl;
    }

    @JsonProperty("clone_url")
    public String getCloneUrl() {
        return cloneUrl;
    }

    @JsonProperty("clone_url")
    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    @JsonProperty("mirror_url")
    public String getMirrorUrl() {
        return mirrorUrl;
    }

    @JsonProperty("mirror_url")
    public void setMirrorUrl(String mirrorUrl) {
        this.mirrorUrl = mirrorUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("hooks_url")
    public URI getHooksUrl() {
        return hooksUrl;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("hooks_url")
    public void setHooksUrl(URI hooksUrl) {
        this.hooksUrl = hooksUrl;
    }

    @JsonProperty("svn_url")
    public String getSvnUrl() {
        return svnUrl;
    }

    @JsonProperty("svn_url")
    public void setSvnUrl(String svnUrl) {
        this.svnUrl = svnUrl;
    }

    @JsonProperty("homepage")
    public String getHomepage() {
        return homepage;
    }

    @JsonProperty("homepage")
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("forks_count")
    public Long getForksCount() {
        return forksCount;
    }

    @JsonProperty("forks_count")
    public void setForksCount(Long forksCount) {
        this.forksCount = forksCount;
    }

    @JsonProperty("stargazers_count")
    public Long getStargazersCount() {
        return stargazersCount;
    }

    @JsonProperty("stargazers_count")
    public void setStargazersCount(Long stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    @JsonProperty("watchers_count")
    public Long getWatchersCount() {
        return watchersCount;
    }

    @JsonProperty("watchers_count")
    public void setWatchersCount(Long watchersCount) {
        this.watchersCount = watchersCount;
    }

    /**
     * The size of the repository, in kilobytes. Size is calculated hourly. When a repository is initially created, the size is 0.
     * 
     */
    @JsonProperty("size")
    public Long getSize() {
        return size;
    }

    /**
     * The size of the repository, in kilobytes. Size is calculated hourly. When a repository is initially created, the size is 0.
     * 
     */
    @JsonProperty("size")
    public void setSize(Long size) {
        this.size = size;
    }

    @JsonProperty("default_branch")
    public String getDefaultBranch() {
        return defaultBranch;
    }

    @JsonProperty("default_branch")
    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    @JsonProperty("open_issues_count")
    public Long getOpenIssuesCount() {
        return openIssuesCount;
    }

    @JsonProperty("open_issues_count")
    public void setOpenIssuesCount(Long openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    @JsonProperty("is_template")
    public Boolean getIsTemplate() {
        return isTemplate;
    }

    @JsonProperty("is_template")
    public void setIsTemplate(Boolean isTemplate) {
        this.isTemplate = isTemplate;
    }

    @JsonProperty("topics")
    public List<String> getTopics() {
        return topics;
    }

    @JsonProperty("topics")
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    @JsonProperty("has_issues")
    public Boolean getHasIssues() {
        return hasIssues;
    }

    @JsonProperty("has_issues")
    public void setHasIssues(Boolean hasIssues) {
        this.hasIssues = hasIssues;
    }

    @JsonProperty("has_projects")
    public Boolean getHasProjects() {
        return hasProjects;
    }

    @JsonProperty("has_projects")
    public void setHasProjects(Boolean hasProjects) {
        this.hasProjects = hasProjects;
    }

    @JsonProperty("has_wiki")
    public Boolean getHasWiki() {
        return hasWiki;
    }

    @JsonProperty("has_wiki")
    public void setHasWiki(Boolean hasWiki) {
        this.hasWiki = hasWiki;
    }

    @JsonProperty("has_pages")
    public Boolean getHasPages() {
        return hasPages;
    }

    @JsonProperty("has_pages")
    public void setHasPages(Boolean hasPages) {
        this.hasPages = hasPages;
    }

    @JsonProperty("has_downloads")
    public Boolean getHasDownloads() {
        return hasDownloads;
    }

    @JsonProperty("has_downloads")
    public void setHasDownloads(Boolean hasDownloads) {
        this.hasDownloads = hasDownloads;
    }

    @JsonProperty("has_discussions")
    public Boolean getHasDiscussions() {
        return hasDiscussions;
    }

    @JsonProperty("has_discussions")
    public void setHasDiscussions(Boolean hasDiscussions) {
        this.hasDiscussions = hasDiscussions;
    }

    @JsonProperty("archived")
    public Boolean getArchived() {
        return archived;
    }

    @JsonProperty("archived")
    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    @JsonProperty("disabled")
    public Boolean getDisabled() {
        return disabled;
    }

    @JsonProperty("disabled")
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @JsonProperty("visibility")
    public String getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @JsonProperty("pushed_at")
    public Date getPushedAt() {
        return pushedAt;
    }

    @JsonProperty("pushed_at")
    public void setPushedAt(Date pushedAt) {
        this.pushedAt = pushedAt;
    }

    @JsonProperty("created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("permissions")
    public Permissions getPermissions() {
        return permissions;
    }

    @JsonProperty("permissions")
    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    @JsonProperty("role_name")
    public String getRoleName() {
        return roleName;
    }

    @JsonProperty("role_name")
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @JsonProperty("temp_clone_token")
    public String getTempCloneToken() {
        return tempCloneToken;
    }

    @JsonProperty("temp_clone_token")
    public void setTempCloneToken(String tempCloneToken) {
        this.tempCloneToken = tempCloneToken;
    }

    @JsonProperty("delete_branch_on_merge")
    public Boolean getDeleteBranchOnMerge() {
        return deleteBranchOnMerge;
    }

    @JsonProperty("delete_branch_on_merge")
    public void setDeleteBranchOnMerge(Boolean deleteBranchOnMerge) {
        this.deleteBranchOnMerge = deleteBranchOnMerge;
    }

    @JsonProperty("subscribers_count")
    public Long getSubscribersCount() {
        return subscribersCount;
    }

    @JsonProperty("subscribers_count")
    public void setSubscribersCount(Long subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    @JsonProperty("network_count")
    public Long getNetworkCount() {
        return networkCount;
    }

    @JsonProperty("network_count")
    public void setNetworkCount(Long networkCount) {
        this.networkCount = networkCount;
    }

    /**
     * Code Of Conduct
     * <p>
     * Code Of Conduct
     * 
     */
    @JsonProperty("code_of_conduct")
    public CodeOfConduct getCodeOfConduct() {
        return codeOfConduct;
    }

    /**
     * Code Of Conduct
     * <p>
     * Code Of Conduct
     * 
     */
    @JsonProperty("code_of_conduct")
    public void setCodeOfConduct(CodeOfConduct codeOfConduct) {
        this.codeOfConduct = codeOfConduct;
    }

    @JsonProperty("license")
    public License getLicense() {
        return license;
    }

    @JsonProperty("license")
    public void setLicense(License license) {
        this.license = license;
    }

    @JsonProperty("forks")
    public Long getForks() {
        return forks;
    }

    @JsonProperty("forks")
    public void setForks(Long forks) {
        this.forks = forks;
    }

    @JsonProperty("open_issues")
    public Long getOpenIssues() {
        return openIssues;
    }

    @JsonProperty("open_issues")
    public void setOpenIssues(Long openIssues) {
        this.openIssues = openIssues;
    }

    @JsonProperty("watchers")
    public Long getWatchers() {
        return watchers;
    }

    @JsonProperty("watchers")
    public void setWatchers(Long watchers) {
        this.watchers = watchers;
    }

    @JsonProperty("allow_forking")
    public Boolean getAllowForking() {
        return allowForking;
    }

    @JsonProperty("allow_forking")
    public void setAllowForking(Boolean allowForking) {
        this.allowForking = allowForking;
    }

    @JsonProperty("web_commit_signoff_required")
    public Boolean getWebCommitSignoffRequired() {
        return webCommitSignoffRequired;
    }

    @JsonProperty("web_commit_signoff_required")
    public void setWebCommitSignoffRequired(Boolean webCommitSignoffRequired) {
        this.webCommitSignoffRequired = webCommitSignoffRequired;
    }

    @JsonProperty("security_and_analysis")
    public SecurityAndAnalysis getSecurityAndAnalysis() {
        return securityAndAnalysis;
    }

    @JsonProperty("security_and_analysis")
    public void setSecurityAndAnalysis(SecurityAndAnalysis securityAndAnalysis) {
        this.securityAndAnalysis = securityAndAnalysis;
    }

    /**
     * The custom properties that were defined for the repository. The keys are the custom property names, and the values are the corresponding custom property values.
     * 
     */
    @JsonProperty("custom_properties")
    public CustomProperties getCustomProperties() {
        return customProperties;
    }

    /**
     * The custom properties that were defined for the repository. The keys are the custom property names, and the values are the corresponding custom property values.
     * 
     */
    @JsonProperty("custom_properties")
    public void setCustomProperties(CustomProperties customProperties) {
        this.customProperties = customProperties;
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
