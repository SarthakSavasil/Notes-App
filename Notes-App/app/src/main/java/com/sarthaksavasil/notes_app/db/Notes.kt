package com.sarthaksavasil.notes_app.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Notes (

    val title: String,
    val note : String
):Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int=0
}
