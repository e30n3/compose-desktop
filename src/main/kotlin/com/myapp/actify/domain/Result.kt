package ru.involta.actify.domain

data class Result<out T>(val status: Status, val data: T?, val exception: Throwable?) {

  enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    EMPTY
  }

  companion object {
    fun <T> success(data: T): Result<T> {
      return Result(Status.SUCCESS, data, null)
    }

    fun <T> error(exception: Throwable): Result<T> {
      return Result(Status.ERROR, null, exception)
    }

    fun <T> loading(): Result<T> {
      return Result(Status.LOADING, null, null)
    }

    fun <T> empty(): Result<T> {
      return Result(Status.EMPTY, null, null)
    }
  }
}
