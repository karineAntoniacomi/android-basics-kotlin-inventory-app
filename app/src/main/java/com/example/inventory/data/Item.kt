package com.example.inventory.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Quando a anotação @Entity não tem argumentos, o nome da tabela é
// igual ao da classe. tableName permite atribuir outro nome a tabela.
@Entity(tableName = "item")
// classe de dados que representa entidade do banco de dados do app
// As classes de dados não podem ser abstract, open, sealed ou inner
data class Item(
    // Anotação que define o id como chave primária
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Anotação ColumnInfo permite personalizar a
    // coluna associada ao campo específico.
    @ColumnInfo(name = "name")
    val nomeItem: String,
    @ColumnInfo(name = "price")
    val precoItem: Double,
    @ColumnInfo(name = "quantity")
    val quantidadeEmEstoque: Int
)
