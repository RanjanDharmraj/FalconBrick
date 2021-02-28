package com.dreamsunited.falconbrick.model

import com.google.gson.annotations.SerializedName

data class BlockData(
	@SerializedName("block_name") val blockName: String? = null,
	@SerializedName("units") val units: List<Units>? = null
)