package com.dreamsunited.falconbrick.db

import io.realm.RealmList
import io.realm.RealmObject

open class UnitObject(
    var activities: RealmList<ActivityObject>? = null,
    var apt: Int? = 0,
    var blockId: String? = null,
    var blockName: String? = null,
    var floor: Int? = 0,
    var id: String? = null,
    var propertyId: String? = null,
    var title: String? = null,
    var unitType: String? = null
) : RealmObject()