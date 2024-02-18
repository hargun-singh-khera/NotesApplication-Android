package com.example.notesapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME="NotesAppDB"
        private const val DATABASE_VERSION=1
        private const val TABLE_NAME="NotesTable"
        const val ID_COL="notesId"
        const val NOTES_TITLE="notesTitle"
        const val NOTES_DESC="notesDesc"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTES_TITLE + " TEXT, " + NOTES_DESC + " TEXT)")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addNote(title: String, desc: String): Long {
        val values = ContentValues()
        values.put(NOTES_TITLE, title)
        values.put(NOTES_DESC, desc)
        val db = this.writableDatabase
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getNotes(): ArrayList<NotesData> {
        val notesList = arrayListOf<NotesData>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

        val idIndex = cursor.getColumnIndex(ID_COL)
        val titleIndex = cursor.getColumnIndex(NOTES_TITLE)
        val descIndex = cursor.getColumnIndex(NOTES_DESC)

        while (cursor.moveToNext()) {
            if (cursor != null) {
                val id = cursor.getLong(idIndex)
                val title = cursor.getString(titleIndex)
                val desc = cursor.getString(descIndex)
                val notes = NotesData(id, title, desc)
                notesList.add(notes)
            }
        }
        cursor.close()
        return notesList
    }

    fun updateNotes(id: Long, newTitle: String, newDesc: String): Boolean {
        val values = ContentValues()
        values.put(NOTES_TITLE, newTitle)
        values.put(NOTES_DESC, newDesc)
        val db = this.writableDatabase
        val updatedRows = db.update(TABLE_NAME, values, ID_COL + "=?", arrayOf(id.toString()))
        db.close()
        return updatedRows > 0
    }

    fun deleteNotesData(id: Long) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$ID_COL=?", arrayOf(id.toString()))
        db.close()
    }

}