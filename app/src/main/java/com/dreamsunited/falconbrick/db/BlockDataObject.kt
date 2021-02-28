package com.dreamsunited.falconbrick.db

import io.realm.RealmList
import io.realm.RealmObject

open class BlockDataObject(
	var blockName: String? = null,
	var units: RealmList<UnitObject>?= null
) : RealmObject()