package com.dreamsunited.falconbrick.utils

import com.dreamsunited.falconbrick.db.BlockDataObject
import com.dreamsunited.falconbrick.model.Activities
import com.dreamsunited.falconbrick.model.BlockData
import com.dreamsunited.falconbrick.model.Units


fun dbToModelObject(listData: List<BlockDataObject>): List<BlockData> {
    return listData.map { _block ->
        BlockData(
            _block.blockName,
            _block.units?.map { _unit ->
                Units(
                    _unit.activities?.map { _activity ->
                        Activities(
                            _activity?.activityName,
                            _activity?.activityStatus,
                            _activity?.currentUserName,
                            _activity?.id,
                            _activity?.stepName,
                            _activity?.progress,
                            _activity?.wf
                        )
                    },
                    _unit?.apt,
                    _unit?.blockId,
                    _unit?.blockName,
                    _unit?.floor,
                    _unit?.id,
                    _unit?.propertyId,
                    _unit?.title,
                    _unit?.unitType
                )
            }
        )
    }
}