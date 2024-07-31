package com.oscargil80.intoroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey(autoGenerate = true) val pId: Int,
    @ColumnInfo("persona_name") val name: String,
    @ColumnInfo("persona_age") val age: Int,
    @ColumnInfo("persona_city") val city: String,

    )
