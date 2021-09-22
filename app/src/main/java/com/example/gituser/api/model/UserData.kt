package com.example.gituser.api.model

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("avatar_url")
    val avatar_url: String = "",
    @SerializedName("bio")
    val bio: Any = Any(),
    @SerializedName("blog")
    val blog: String = "",
    @SerializedName("collaborators")
    val collaborators: Int = 0,
    @SerializedName("company")
    val company: Any = Any(),
    @SerializedName("created_at")
    val created_at: String = "",
    @SerializedName("disk_usage")
    val diskUsage: Int = 0,
    @SerializedName("email")
    val email: String = "",
    @SerializedName("events_url")
    val eventsUrl: String = "",
    @SerializedName("followers")
    val followers: Int = 0,
    @SerializedName("followers_url")
    val followersUrl: String = "",
    @SerializedName("following")
    val following: Int = 0,
    @SerializedName("following_url")
    val following_url: String = "",
    @SerializedName("gists_url")
    val gistsUrl: String = "",
    @SerializedName("gravatar_id")
    val gravatarId: String = "",
    @SerializedName("hireable")
    val hireable: Any = Any(),
    @SerializedName("html_url")
    val htmlUrl: String = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("location")
    val location: Any = Any(),
    @SerializedName("login")
    val login: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("node_id")
    val nodeId: String = "",
    @SerializedName("organizations_url")
    val organizations_url: String = "",
    @SerializedName("owned_private_repos")
    val owned_private_repos: Int = 0,
    @SerializedName("plan")
    val plan: Plan = Plan(),
    @SerializedName("private_gists")
    val private_gists: Int = 0,
    @SerializedName("public_gists")
    val public_gists: Int = 0,
    @SerializedName("public_repos")
    val public_repos: Int = 0,
    @SerializedName("received_events_url")
    val received_events_url: String = "",
    @SerializedName("repos_url")
    val repos_url: String = "",
    @SerializedName("site_admin")
    val site_admin: Boolean = false,
    @SerializedName("starred_url")
    val starred_url: String = "",
    @SerializedName("subscriptions_url")
    val subscriptions_url: String = "",
    @SerializedName("total_private_repos")
    val total_private_repos: Int = 0,
    @SerializedName("twitter_username")
    val twitter_username: Any = Any(),
    @SerializedName("two_factor_authentication")
    val two_factor_authentication: Boolean = false,
    @SerializedName("type")
    val type: String = "",
    @SerializedName("updated_at")
    val updated_at: String = "",
    @SerializedName("url")
    val url: String = ""
)

class Plan(
    @SerializedName("collaborators")
    val collaborators: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("private_repos")
    val private_repos: Int = 0,
    @SerializedName("space")
    val space: Int = 0
)