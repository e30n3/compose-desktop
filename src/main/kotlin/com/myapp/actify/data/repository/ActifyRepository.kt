package ru.involta.actify.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.myapp.actify.data.api.ActifyMainApi
import com.myapp.actify.data.api.ActifyRegistrationApi
import dagger.Provides
import retrofit2.Retrofit
/*
import ru.involta.actify.data.db.DaoDatabase
*/
import ru.involta.actify.domain.entity.api.ResponseBody
import ru.involta.actify.domain.entity.api.request.*
import ru.involta.actify.domain.entity.api.response.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ActifyRepository @Inject constructor(
    private val mainApi: ActifyMainApi,
    private val registrationApi: ActifyRegistrationApi,
/*  private val database: DaoDatabase,*/
) {
//TODO !!!!!!!!!!!!!!!!!!! заменить
    private suspend fun getTerminalId(): Long = getTerminal()?.id ?: 55


    init {
        /*GlobalScope.launch {
          repeat(10000){
            async {
              accrue("+79158268337", 999999.0)
            }
          }
        }*/
    }


    suspend fun report(date: String): ReportResponse =
        withContext(Dispatchers.IO) {
            try {
                ReportResponse(
                    true,
                    mainApi.report(
                        ReportRequest(getTerminalId(), date)
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                val errorMsg =
                    try {
                        mainApi.reportAnotherBody(
                            ReportRequest(
                                getTerminalId(),
                                date
                            )
                        ).message
                    } catch (e: Exception) {
                        "Проверьте корректность введенных даных"
                    }
                ReportResponse(false, message = errorMsg)
            }
        }

    suspend fun debitCheck(phoneOrCard: String, amount: Double): DebitCheckResponse =
        withContext(Dispatchers.IO) {
            mainApi.debitCheck(DebitCheckRequest(phoneOrCard, getTerminalId(), AmountBody(amount)))
        }

    suspend fun debit(phoneOrCard: String, amount: Double, debit: Double, confirmCode: String): ResponseBody =
        withContext(Dispatchers.IO) {
            mainApi.debit(DebitRequest(phoneOrCard, getTerminalId(), AmountBody(amount), DebitBody(debit), confirmCode))
        }

    suspend fun prizes(phoneOrCard: String): PrizesResponse =
        withContext(Dispatchers.IO) {
            try {
                PrizesResponse(true, mainApi.prizes(BalanceRequest(phoneOrCard, getTerminalId())))
            } catch (e: Exception) {
                e.printStackTrace()
                val errorMsg =
                    try {
                        mainApi.prizesAnotherBody(BalanceRequest(phoneOrCard, getTerminalId())).message
                    } catch (e: Exception) {
                        "Проверьте корректность введенных даных"
                    }
                PrizesResponse(false, message = errorMsg)
            }
        }

    suspend fun claimPrize(
        phoneOrCard: String,
        actionId: Long,
        prizeId: Long,
        confirmCode: String
    ): ResponseBody =
        withContext(Dispatchers.IO) {
            mainApi.claimPrize(PrizeRequest(phoneOrCard, getTerminalId(), actionId, prizeId, confirmCode))
        }

    suspend fun registration(code: String, phone: String): ResponseBody =
        withContext(Dispatchers.IO) {
            registrationApi.registration(code, phone)
        }

    suspend fun getBalance(phoneOrCard: String): BalanceResponse =
        withContext(Dispatchers.IO) {
            mainApi.balance(BalanceRequest(phoneOrCard, getTerminalId()))
        }

    suspend fun accrue(phoneOrCard: String, amount: Double): BalanceResponse =
        withContext(Dispatchers.IO) {
            mainApi.accrue(AccrueRequest(phoneOrCard, getTerminalId(), AmountBody(amount)).also { println(it) })
        }

    suspend fun terminalRegistration(id: Long, key: String): TerminalRegistrationResponse =
        withContext(Dispatchers.IO) {
            val result = mainApi.terminalRegistration(TerminalRegistrationRequest(id, key))
            /* if (result.isSuccess) database.insertTerminalRegistrationData(result)*/
            result
        }


    suspend fun getTerminal(): TerminalRegistrationResponse? =
        withContext(Dispatchers.IO) {
            /* database.getTerminalRegistrationData()*/
            null
        }

    suspend fun logOut() {
        withContext(Dispatchers.IO) {
            /*database.clearTerminalTable()*/
        }
    }


    suspend fun sendSms(reg: Int, phone: String): ResponseBody =
        withContext(Dispatchers.IO) {
            registrationApi.sendSms(reg, phone)
        }
}