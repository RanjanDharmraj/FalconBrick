package com.dreamsunited.falconbrick.model

import com.google.gson.annotations.SerializedName

data class Units(
    @SerializedName("activities") val activities: List<Activities>? = null,
    @SerializedName("apt") val apt: Int? = 0,
    @SerializedName("block_id") val blockId: String? = null,
    @SerializedName("block_name") val blockName: String? = null,
    @SerializedName("floor") val floor: Int? = 0,
    @SerializedName("id") val id: String? = null,
    @SerializedName("property_id") val propertyId: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("unit_type") val unitType: String? = null
)