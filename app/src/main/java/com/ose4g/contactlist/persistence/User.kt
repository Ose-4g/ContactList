package com.ose4g.contactlist.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User(
    @ColumnInfo(name = "emailAddress") val emailAddress: String,
    @ColumnInfo(name = "password") val password: String
)
{
    @PrimaryKey
    var uid:Int = 0
}