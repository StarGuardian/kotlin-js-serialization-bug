package data

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.*
import kotlin.js.JsName
import kotlin.test.DefaultAsserter
import kotlin.test.Test

class OperationResultSerializationTest {

    val jsonFormat = Json {
        serializersModule = SerializersModule {
            polymorphic(Any::class) {
                subclass(IntPayload::class)
            }
        }
    }

    @Test
    @JsName("operationSuccessSerializationTest")
    fun `operation success should be serialized and deserialized`() {
        val success = OperationSuccess(IntPayload(42))

        val successJson = serialize(success)
        val deserializedSuccess = deserialize<IntPayload>(successJson)

        DefaultAsserter.assertEquals(null, success, deserializedSuccess)
    }

    private inline fun <reified T> serialize(value: OperationResult<T>): String {
        return jsonFormat.encodeToString(value)
    }

    private inline fun <reified T> deserialize(json: String): OperationResult<T> {
        return jsonFormat.decodeFromString(json)
    }
}