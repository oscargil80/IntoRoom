package com.oscargil80.intoroom

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  savePerson(Person: Person)

    @Update
    suspend fun updatePerson(person:Person)


    @Delete
    suspend fun  deletePerson(person: Person)

    @Query("Delete from person_table where pId = :pId")
    suspend fun deletepersonById(pId:Int)

    @Query("SELECT * FROM person_table")
     fun getAllData(): Flow<List<Person>>


}