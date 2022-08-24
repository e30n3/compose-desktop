package com.myapp.actify.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.involta.actify.data.repository.ActifyRepository
import ru.involta.actify.domain.entity.api.ResponseBody
import com.myapp.actify.domain.entity.api.response.TerminalRegistrationResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Interactor @Inject constructor(private val repository: ActifyRepository) {

  fun registration(code: String, phone: String): Flow<ResponseBody> = flow {
    emit(repository.registration(code, phone))
  }.flowOn(Dispatchers.IO)

  fun sendSms(reg: Int, phone: String): Flow<ResponseBody> = flow {
    emit(repository.sendSms(reg, phone))
  }.flowOn(Dispatchers.IO)

  fun terminalRegistration(id: Long, key: String): Flow<TerminalRegistrationResponse> = flow {
    emit(repository.terminalRegistration(id, key))
  }.flowOn(Dispatchers.IO)

  fun getTerminal(): Flow<TerminalRegistrationResponse?> = flow {
    emit(repository.getTerminal())
  }.flowOn(Dispatchers.IO)

  fun getBalance(phoneOrCard: String) = flow {
    emit(repository.getBalance(phoneOrCard))
  }.flowOn(Dispatchers.IO)

  fun accrue(phoneOrCard: String, amount: Double) = flow {
    emit(repository.accrue(phoneOrCard, amount))
  }.flowOn(Dispatchers.IO)

  fun prizes(phoneOrCard: String) = flow {
    emit(repository.prizes(phoneOrCard))
  }.flowOn(Dispatchers.IO)

  fun claimPrize(
    phoneOrCard: String,
    actionId: Long,
    prizeId: Long,
    confirmCode: String
  ) = flow {
    emit(repository.claimPrize(phoneOrCard, actionId, prizeId, confirmCode))
  }.flowOn(Dispatchers.IO)

  fun report(date: String) = flow {
    emit(repository.report(date))
  }.flowOn(Dispatchers.IO)

  fun logOut() = flow {
    emit(repository.logOut())
  }.flowOn(Dispatchers.IO)

  fun debitCheck(phoneOrCard: String, amount: Double) = flow {
    emit(repository.debitCheck(phoneOrCard, amount))
  }.flowOn(Dispatchers.IO)

  fun debit(phoneOrCard: String, amount: Double, debit: Double, confirmCode: String) = flow {
    emit(repository.debit(phoneOrCard, amount, debit, confirmCode))
  }.flowOn(Dispatchers.IO)

}