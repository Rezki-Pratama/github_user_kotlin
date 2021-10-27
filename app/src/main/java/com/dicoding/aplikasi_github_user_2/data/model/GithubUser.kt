package com.dicoding.aplikasi_github_user_2.data.model
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GitUser(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("avatar_url")
    var avatarUrl: String?,
    @SerializedName("events_url")
    var eventsUrl: String?,
    @SerializedName("followers_url")
    var followersUrl: String?,
    @SerializedName("following_url")
    var followingUrl: String?,
    @SerializedName("gists_url")
    var gistsUrl: String?,
    @SerializedName("gravatar_id")
    var gravatarId: String?,
    @SerializedName("html_url")
    var htmlUrl: String?,
    @SerializedName("login")
    var login: String?,
    @SerializedName("node_id")
    var nodeId: String?,
    @SerializedName("organizations_url")
    var organizationsUrl: String?,
    @SerializedName("received_events_url")
    var receivedEventsUrl: String?,
    @SerializedName("repos_url")
    var reposUrl: String?,
    @SerializedName("score")
    var score: Double?,
    @SerializedName("site_admin")
    var siteAdmin: Boolean?,
    @SerializedName("starred_url")
    var starredUrl: String?,
    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("url")
    var url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(avatarUrl)
        parcel.writeString(eventsUrl)
        parcel.writeString(followersUrl)
        parcel.writeString(followingUrl)
        parcel.writeString(gistsUrl)
        parcel.writeString(gravatarId)
        parcel.writeString(htmlUrl)
        parcel.writeString(login)
        parcel.writeString(nodeId)
        parcel.writeString(organizationsUrl)
        parcel.writeString(receivedEventsUrl)
        parcel.writeString(reposUrl)
        parcel.writeValue(score)
        parcel.writeValue(siteAdmin)
        parcel.writeString(starredUrl)
        parcel.writeString(subscriptionsUrl)
        parcel.writeString(type)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GitUser> {
        override fun createFromParcel(parcel: Parcel): GitUser {
            return GitUser(parcel)
        }

        override fun newArray(size: Int): Array<GitUser?> {
            return arrayOfNulls(size)
        }
    }
}