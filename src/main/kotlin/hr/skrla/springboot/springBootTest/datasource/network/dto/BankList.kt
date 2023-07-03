package hr.skrla.springboot.springBootTest.datasource.network.dto

import hr.skrla.springboot.springBootTest.model.Bank

data class BankList(
    val results: Collection<Bank>
)
