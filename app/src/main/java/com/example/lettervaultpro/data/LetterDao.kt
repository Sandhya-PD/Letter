package com.example.lettervaultpro.data


import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LetterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(msg:Letter):Long

    @Query("SELECT * FROM letter ORDER BY id DESC LIMIT 1")
     fun getLetter(): Flow<Letter>

    @Update
    suspend fun update(item: Letter)

    @Query("SELECT * FROM letter")
    fun getAllLetters():PagingSource<Int,Letter>

    @Query("SELECT * FROM letter WHERE id =:id")
    fun getLetterByID(id:Int):Letter

    @Query("SELECT id FROM letter WHERE id =:id ")
    fun checkIdPresent(id:Int): Boolean

//    @Query("SELECT * FROM letter")
//    fun pagingSource():PagingSource<Int,Letter>

    @Query("DELETE FROM letter WHERE id=:id")
    suspend fun delete(id: Int)
}