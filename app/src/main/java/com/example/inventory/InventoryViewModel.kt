package com.example.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {

    private fun inserirItem(item: Item) {
        // viewModelScope.launch inicia uma corrotina no ViewModelScope, e é uma
        // uma propriedade de extensão para a classe ViewModel que cancela
        // automaticamente as corrotinas filhas quando a ViewModel é destruída.
        viewModelScope.launch {
            itemDao.inserirItem(item)
        }
    }

    private fun obterNovaEntradaDeItem(nomeItem: String, precoItem: String, quantidadeItem: String): Item {
        return Item(
            nomeItem = nomeItem,
            precoItem = precoItem.toDouble(),
            quantidadeEmEstoque = quantidadeItem.toInt()
        )
    }

    fun adicionarNovoItem(nomeItem: String, precoItem: String, quantidadeItem: String) {
        val novoItem = obterNovaEntradaDeItem(nomeItem, precoItem, quantidadeItem)
        inserirItem(novoItem)
    }

}

// Classe para criar a instância de ViewModel.
class InventoryViewModelFactory (private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}