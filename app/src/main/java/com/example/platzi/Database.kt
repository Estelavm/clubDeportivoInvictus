import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.platzi.model.User

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 2
        private const val TABLE_USERS = "User"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

        private const val TABLE_USER_DATA = "UserData"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_LAST_NAME = "last_name"
        private const val COLUMN_DOC_TYPE = "doc_type"
        private const val COLUMN_DOC_NUMBER = "doc_number"
        private const val COLUMN_USER_TYPE = "user_type"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createUsersTable = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID INTEGER PRIMARY KEY,
                $COLUMN_USERNAME TEXT,
                $COLUMN_PASSWORD TEXT
            )
        """
        db?.execSQL(createUsersTable)

        val adminValues = ContentValues().apply {
            put(COLUMN_USERNAME, "admin")
            put(COLUMN_PASSWORD, "123")
        }
        db?.insert(TABLE_USERS, null, adminValues)

        val createUserDataTable = """
            CREATE TABLE $TABLE_USER_DATA (
                $COLUMN_DOC_NUMBER TEXT PRIMARY KEY,
                $COLUMN_NAME TEXT,
                $COLUMN_LAST_NAME TEXT,
                $COLUMN_DOC_TYPE TEXT,
                $COLUMN_USER_TYPE TEXT
            )
        """
        db?.execSQL(createUserDataTable)
        Log.d("DatabaseHelper", "onCreate: Tables created successfully")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER_DATA")
        onCreate(db)
    }

    fun validateUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?", arrayOf(username, password))
        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }

    fun insertUserData(name: String, lastName: String, docType: String, docNumber: String, userType: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_LAST_NAME, lastName)
            put(COLUMN_DOC_TYPE, docType)
            put(COLUMN_DOC_NUMBER, docNumber)
            put(COLUMN_USER_TYPE, userType)
        }
        val id = db.insert(TABLE_USER_DATA, null, values)
        db.close()
        return id
    }

    fun getAllUsersList(): List<User> {
        val userList = mutableListOf<User>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_USER_DATA"
        val cursor = db.rawQuery(query, null)

        try {
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                    val lastName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME))
                    val docType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOC_TYPE))
                    val docNumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOC_NUMBER))
                    val userType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_TYPE))

                    val user = User(name, lastName, docType, docNumber, userType)
                    userList.add(user)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.e("DatabaseHelper", "Error retrieving data", e)
        } finally {
            cursor.close()
            db.close()
        }

        return userList
    }

    fun getAllUsersString(): String {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USER_DATA", null)
        val userList = StringBuilder()

        try {
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                    val lastName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME))
                    val docType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOC_TYPE))
                    val docNumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOC_NUMBER))
                    val userType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_TYPE))

                    userList.append("Name: $name $lastName\nDocument: $docType $docNumber\nType: $userType\n\n")
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.e("DatabaseHelper", "Error retrieving data", e)
        } finally {
            cursor.close()
            db.close()
        }

        return userList.toString()
    }

    fun getUserByDNI(docNumber: String): User? {
        val db = readableDatabase
        var user: User? = null
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USER_DATA WHERE $COLUMN_DOC_NUMBER = ?", arrayOf(docNumber))

        if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val lastName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME))
            val docType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOC_TYPE))
            val docNum = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOC_NUMBER))
            val userType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_TYPE))

            user = User(name, lastName, docType, docNum, userType)
        }

        cursor.close()
        db.close()
        return user
    }

    fun deleteUserByDNI(dni: String): Int {
        val db = writableDatabase
        val result = db.delete(TABLE_USER_DATA, "$COLUMN_DOC_NUMBER = ?", arrayOf(dni))
        db.close()
        return result
    }

    fun updateUserData(docNumber: String, name: String, lastName: String, docType: String, userType: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_LAST_NAME, lastName)
            put(COLUMN_DOC_TYPE, docType)
            put(COLUMN_USER_TYPE, userType)
        }
        val result = db.update(TABLE_USER_DATA, values, "$COLUMN_DOC_NUMBER = ?", arrayOf(docNumber))
        db.close()
        return result
    }
}
