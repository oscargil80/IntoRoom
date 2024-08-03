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


    @Query("SELECT * FROM person_table WHERE persona_name LIKE :query || '%' ")
    fun getSearchFromStartData(query:String): Flow<List<Person>>

    @Query("SELECT * FROM person_table WHERE persona_name LIKE '%' || :query")
    fun getSearchFromEndData(query:String): Flow<List<Person>>

    @Query("SELECT * FROM person_table WHERE persona_name LIKE '%' || :query  || '%'  ")
    fun getSearchedData(query:String): Flow<List<Person>>

    @Query("SELECT * FROM person_table WHERE persona_name NOT LIKE '%' || :query  || '%'  ")
    fun getSearchedExceptQueryData(query:String): Flow<List<Person>>



}