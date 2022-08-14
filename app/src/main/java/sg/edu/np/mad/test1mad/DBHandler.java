package sg.edu.np.mad.test1mad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserProfile.db";
    public static final String TABLE_USERS = "Users";
    public static final String COLUMN_USERNAME = "Name";
    public static final String COLUMN_USERDESCRIPTION = "Description";
    public static final String COLUMN_USERID = "ID";
    public static final String COLUMN_USERFOLLOWED = "Followed";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_USERDESCRIPTION + " TEXT, "
                + COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERFOLLOWED + " BOOLEAN)";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void insertUser(User user){
        ContentValues userValues = new ContentValues();

        userValues.put(COLUMN_USERNAME, user.getUserName());
        userValues.put(COLUMN_USERDESCRIPTION, user.getUserDescription());
        userValues.put(COLUMN_USERID, user.getUserId());
        userValues.put(COLUMN_USERFOLLOWED, user.getUserFollowed());

        SQLiteDatabase db = this.getWritableDatabase();

        long val = db.insert(TABLE_USERS, null, userValues);
        db.close();
    }

    public ArrayList<User> getUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);

        ArrayList<User> dbUserList = new ArrayList<>();

        while (cursor.moveToNext()){
            User dbUser = new User();
            dbUser.setUserName(cursor.getString(0));
            dbUser.setUserDescription(cursor.getString(1));
            dbUser.setUserId(cursor.getInt(2));
            dbUser.setUserFollowed(cursor.getWantsAllOnMoveCalls());

            dbUserList.add(dbUser);
        }
        db.close();
        return dbUserList;
    }

    public void updateUser(User userToUpdate){
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + "= \"" + userToUpdate.getUserName() + "\"";
        Cursor cursor = db.rawQuery(updateQuery, null);

        ContentValues userValues = new ContentValues();
        userValues.put(COLUMN_USERNAME, userToUpdate.getUserName());
        userValues.put(COLUMN_USERDESCRIPTION, userToUpdate.getUserDescription());
        userValues.put(COLUMN_USERID, userToUpdate.getUserId());
        userValues.put(COLUMN_USERFOLLOWED, userToUpdate.getUserFollowed());

        db.update(TABLE_USERS, userValues, COLUMN_USERID + " = ?", new String[]{String.valueOf(userToUpdate.getUserId())});
    }
}
