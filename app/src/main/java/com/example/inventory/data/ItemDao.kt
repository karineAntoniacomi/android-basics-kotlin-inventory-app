package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    // A estratégia OnConflictStrategy.IGNORE
    // ignorará um novo item se a chave
    // primária já estiver no banco de dados.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //  Operações BD podem demorar, suspend permite
    //  que sejam executadas em linha de execução
    //  separada e chamada e chamada em corrotina.
    suspend fun inserirItem(item: Item)

    @Update
    suspend fun atualizarItem(item: Item)

    @Delete
    suspend fun deletarItem(item: Item)

    // :id : dois pontos são usados na consulta
    // para referenciar argumentos na função.
    @Query("SELECT * from item WHERE id = :id")
    // o Flow ou o LiveData como tipo de retorno
    // garante que uma notificação seja enviada
    // quando os dados no banco de dados mudarem.
    fun recuperarItemPorID(id: Int): Flow<Item>
    // Room executa a consulta na linha de execução em
    // segundo plano, por isso não precisa ser suspend.

    // Consulta SQLite que retorna todas as colunas
    // da tabela item, em ordem crescente.
    @Query("SELECT * from item ORDER BY name ASC")
    // Retorna lista de entidades Item como um Flow.
    fun recuperarItens(): Flow<List<Item>>
}