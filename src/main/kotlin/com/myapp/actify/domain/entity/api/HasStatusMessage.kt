package ru.involta.actify.domain.entity.api

interface HasStatusMessage {
  val unsafeMessage: String?
  val message: String
  val isSuccess: Boolean
}