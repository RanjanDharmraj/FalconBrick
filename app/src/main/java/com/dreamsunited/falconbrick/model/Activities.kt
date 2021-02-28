package com.dreamsunited.falconbrick.model

import com.google.gson.annotations.SerializedName

data class Activities(
	@SerializedName("activity_name") val activityName: String? = null,
	@SerializedName("activity_status") val activityStatus: String? = null,
	@SerializedName("current_user_name") val currentUserName: String? = null,
	@SerializedName("id") val id: String? = null,
	@SerializedName("step_name") val stepName: String? = null,
	@SerializedName("progress") val progress: Int? = 0,
	@SerializedName("wf") val wf: String? = null
)