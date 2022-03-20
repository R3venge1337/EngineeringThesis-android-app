 package com.example.engineeringthesis.database.Room

/*

 @Database(entities = [Account::class,
                        Audio::class,
                         AudioFileTable::class,
                         Category::class,
                         CategoryTeacher::class,
                         Child::class,
                         Game::class,
                         Gameplay::class,
                         GameplayResult::class,
                         Image::class,
                         ImageFileTable::class,
                         Language::class,
                         Role::class,
                         StatisticResult::class,
                         StatisticType::class,
                         Teacher::class,
                         Token::class,
                         Word::class],
                        version = 1,exportSchema = false)
 abstract class RoomDatabaseObject : RoomDatabase()
{
    companion object
    {
        @Volatile
        private var INSTANCE :RoomDatabaseObject? = null

        fun getDatabaseInstance(context: Context) :RoomDatabaseObject
        {
            val tmpinstance = INSTANCE
            if(tmpinstance != null)
            {
                return tmpinstance
            }
            synchronized(this)
            {
                val  instance = Room.databaseBuilder(context,RoomDatabaseObject::class.java,"appDatabase")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
  */