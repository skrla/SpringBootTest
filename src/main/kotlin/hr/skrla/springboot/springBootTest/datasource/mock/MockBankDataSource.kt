package hr.skrla.springboot.springBootTest.datasource.mock

import hr.skrla.springboot.springBootTest.datasource.BankDataSource
import hr.skrla.springboot.springBootTest.model.Bank

import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException


@Repository("mock")
class MockBankDataSource : BankDataSource {

    val banks = mutableListOf(
        Bank("1234", 3.14, 17),
        Bank("1010", 17.0, 0),
        Bank("5678", 0.0, 100)
    )

    override fun retrieveBanks(): Collection<Bank> {
        return banks
    }

    override fun retrieveBank(accountNumber: String): Bank = banks.firstOrNull { it.accountNumber == accountNumber } ?:
    throw NoSuchElementException("Could not find a bank with account number $accountNumber")

    override fun createBank(bank: Bank): Bank {

        if (banks.any { it.accountNumber == bank.accountNumber }) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exits")
        }
        banks.add(bank)

        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber } ?:
        throw NoSuchElementException("Could not find a bank with account number ${bank.accountNumber}")

        banks.remove(currentBank)
        banks.add(bank)

        return bank
    }

    override fun deleteBank(accountNumber: String) {
        val currentBank = banks.firstOrNull { it.accountNumber == accountNumber } ?:
        throw NoSuchElementException("Could not find a bank with account number $accountNumber")

        banks.remove(currentBank)
    }

}