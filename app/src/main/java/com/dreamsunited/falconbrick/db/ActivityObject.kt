package com.dreamsunited.falconbrick.db

import io.realm.RealmObject

open class ActivityObject(
    var activityName: String? = null,
    var activityStatus: String? = null,
    var currentUserName: String? = null,
    var id: String? = null,
    var stepName: String? = null,
    var progress: Int? = 0,
    var wf: String? = null
) : RealmObject()