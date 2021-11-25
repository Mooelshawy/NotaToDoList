# NotaToDoList
Android Room using Kotlin

#Room is a part of the Android Architecture components which provides an abstraction layer over SQlite
 which allows for a more robust database access while still providing the full power of SQlite.

 ###  Room Basics  ###
 The Room library consists of 3 major components
            /// Entity Dao  DataBase ///
 1-Entity
 The Entity represents a table with in the database and has to be annotated with # @Entity #
 Each Entity consist of a minimum of one field has to define a ### primary key ###

 2-DAO (Database Access Object):
 In Room you use data access objects to access and manage your data.
 The DAO is the main component of Room and includes methods that offer access to your apps
 database it has to be annotated with ### @Dao ###. DAOs are used instead of query builders
 and let you separated different components of your database e.g. current data and statistics,
 which allows you to easily test your database.

 3-Database:
  Serves as the database holder an is the main access point to your relational database.
  It has to be annotated with ### @Database ### and extends the # RoomDatabase #.
  It also contains and returns the Dao (Database Access Object).
---------------------------------------------------------------------------------------------------------------------------------------
