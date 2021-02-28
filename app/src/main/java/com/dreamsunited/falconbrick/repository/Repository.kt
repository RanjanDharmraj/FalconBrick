package com.dreamsunited.falconbrick.repository

import com.dreamsunited.falconbrick.R
import com.dreamsunited.falconbrick.db.ActivityObject
import com.dreamsunited.falconbrick.db.BlockDataObject
import com.dreamsunited.falconbrick.db.UnitObject
import com.dreamsunited.falconbrick.model.BlockData
import com.dreamsunited.falconbrick.utils.AssetManagerHelper
import com.dreamsunited.falconbrick.utils.dbToModelObject
import io.reactivex.Completable
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmList

interface Repository {
    fun getAllData(): Observable<List<BlockData>>
    fun getDataOnSearch(value: String) : Observable<List<BlockData>>
    fun saveDataIntoDB(): Completable
}

class RepositoryImpl(
    private val assetManagerHelper: AssetManagerHelper,
    private val realm: Realm
) : Repository {

    override fun getAllData(): Observable<List<BlockData>> {
        return Observable.create { emitter ->
            realm.executeTransactionAsync(
                {
                    val data = it.where(BlockDataObject::class.java).findAll()
                    emitter.onNext(dbToModelObject(data))
                },
                { t: Throwable ->
                    emitter.onError(t)
                }
            )
        }
    }

    override fun getDataOnSearch(value: String): Observable<List<BlockData>> {
        return Observable.create { emitter ->
            realm.executeTransactionAsync(
                {
                    val data = it.where(BlockDataObject::class.java).equalTo("blockName", value).findAll()
                    emitter.onNext(dbToModelObject(data))
                },
                { t: Throwable ->
                    emitter.onError(t)
                }
            )
        }
    }

    override fun saveDataIntoDB(): Completable {
        return Completable.create { completable ->
            realm.executeTransactionAsync(
                { _realm ->
                    val blockData = mutableListOf<BlockDataObject>()
                    assetManagerHelper.constructUsingGson(
                        R.raw.data,
                        Array<BlockData>::class.java
                    ).forEach { _block ->
                        val data = _realm.createObject(BlockDataObject::class.java)
                        data.blockName = _block.blockName
                        data.units = RealmList()
                        _block.units?.forEach { _unit ->
                            val unit = _realm.createObject(UnitObject::class.java)
                            unit.activities = RealmList()
                            _unit.activities?.forEach { _activity ->
                                val activity =
                                    _realm.createObject(ActivityObject::class.java)
                                activity.activityName = _activity.activityName
                                activity.activityStatus = _activity.activityStatus
                                activity.currentUserName = _activity.currentUserName
                                activity.id = _activity.id
                                activity.stepName = _activity.stepName
                                activity.progress = _activity.progress
                                activity.wf = _activity.wf
                                unit.activities?.add(
                                    activity
                                )
                            }
                            unit.apt = _unit.apt
                            unit.blockId = _unit.blockId
                            unit.blockName = _unit.blockName
                            unit.floor = _unit.floor
                            unit.id = _unit.id
                            unit.propertyId = _unit.propertyId
                            unit.title = _unit.title
                            unit.unitType = _unit.unitType
                            data.units?.add(
                                unit
                            )
                        }
                        blockData.add(data)
                    }
                    _realm.insertOrUpdate(blockData)
                }, {
                    completable.onComplete()
                }, { t: Throwable ->
                    completable.onError(t)
                }
            )
        }
    }
}