package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// A anotação @Database requer vários argumentos para que o Room possa criar o BD.
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {
    // o BD precisa ser informado sobre o DAO
    abstract fun itemDao(): ItemDao

    // O objeto complementar permite acessar os métodos para criar ou consultar
    // o BD, usando o nome da classe como qualificador.
    companion object {
        // O valor de uma variável volátil nunca será armazenado em cache,
        // gravações e leituras serão feitas na memória principal.
        @Volatile
        // INSTANCE garante que haja somente uma instância do BD (padrão Singleton).
        private var INSTANCE: ItemRoomDatabase? = null
        fun getDatabase(context: Context): ItemRoomDatabase {
            // Bloco synchronized envolvendo o Banco de dados significa que
            // somente uma linha de execução vai poder entrar nesse bloco por vez,
            // garantindo que o banco de dados será inicializado apenas uma vez.
            return INSTANCE ?: synchronized(this) {
                // builder do banco de dados para retornar o banco de dados
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                )
                    // Um objeto de migração define como converter todas as linhas
                    // no esquema antigo em linhas no novo esquema, para que nenhum
                    // dado seja perdido.
                    .fallbackToDestructiveMigration()
                    // Cria a instância do banco de dados.
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}