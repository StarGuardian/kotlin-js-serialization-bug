package data

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IntPayload(val value: Int)

@Serializable
sealed class OperationResult<out T>

typealias ActionResult = OperationResult<Unit>

@Serializable
@SerialName("operation_success")
data class OperationSuccess<T>(val result: T): OperationResult<T>()

@Serializable
@SerialName("operation_failure")
data class OperationFailure(val error: String): OperationResult<@Contextual Nothing>()

@Serializable
@SerialName("action_success")
object ActionSuccess: ActionResult()
