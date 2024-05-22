// Autogenerated from Pigeon (v18.0.0), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.mapbox.maps.mapbox_maps.pigeons

import android.util.Log
import com.mapbox.geojson.Polygon
import com.mapbox.maps.mapbox_maps.mapping.turf.*
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StandardMessageCodec
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

private fun wrapResult(result: Any?): List<Any?> {
  return listOf(result)
}

private fun wrapError(exception: Throwable): List<Any?> {
  if (exception is FlutterError) {
    return listOf(
      exception.code,
      exception.message,
      exception.details
    )
  } else {
    return listOf(
      exception.javaClass.simpleName,
      exception.toString(),
      "Cause: " + exception.cause + ", Stacktrace: " + Log.getStackTraceString(exception)
    )
  }
}

private fun createConnectionError(channelName: String): FlutterError {
  return FlutterError("channel-error", "Unable to establish connection on channel: '$channelName'.", "")
}

/** Controls the frame of reference for `fill-translate`. */
enum class FillTranslateAnchor(val raw: Int) {
  /** The fill is translated relative to the map. */
  MAP(0),
  /** The fill is translated relative to the viewport. */
  VIEWPORT(1);

  companion object {
    fun ofRaw(raw: Int): FillTranslateAnchor? {
      return values().firstOrNull { it.raw == raw }
    }
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class PolygonAnnotation(
  /** The id for annotation */
  val id: String,
  /** The geometry that determines the location/shape of this annotation */
  val geometry: Polygon,
  /** Sorts features in ascending order based on this value. Features with a higher sort key will appear above features with a lower sort key. */
  val fillSortKey: Double? = null,
  /** The color of the filled part of this layer. This color can be specified as `rgba` with an alpha component and the color's opacity will not affect the opacity of the 1px stroke, if it is used. */
  val fillColor: Long? = null,
  /** The opacity of the entire fill layer. In contrast to the `fill-color`, this value will also affect the 1px stroke around the fill, if the stroke is used. */
  val fillOpacity: Double? = null,
  /** The outline color of the fill. Matches the value of `fill-color` if unspecified. */
  val fillOutlineColor: Long? = null,
  /** Name of image in sprite to use for drawing image fills. For seamless patterns, image width and height must be a factor of two (2, 4, 8, ..., 512). Note that zoom-dependent expressions will be evaluated only at integer zoom levels. */
  val fillPattern: String? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): PolygonAnnotation {
      val id = list[0] as String
      val geometry = PolygonDecoder.fromList(list[1] as List<Any?>)
      val fillSortKey = list[2] as Double?
      val fillColor = list[3].let { if (it is Int) it.toLong() else it as Long? }
      val fillOpacity = list[4] as Double?
      val fillOutlineColor = list[5].let { if (it is Int) it.toLong() else it as Long? }
      val fillPattern = list[6] as String?
      return PolygonAnnotation(id, geometry, fillSortKey, fillColor, fillOpacity, fillOutlineColor, fillPattern)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      id,
      geometry.toList(),
      fillSortKey,
      fillColor,
      fillOpacity,
      fillOutlineColor,
      fillPattern,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class PolygonAnnotationOptions(
  /** The geometry that determines the location/shape of this annotation */
  val geometry: Polygon,
  /** Sorts features in ascending order based on this value. Features with a higher sort key will appear above features with a lower sort key. */
  val fillSortKey: Double? = null,
  /** The color of the filled part of this layer. This color can be specified as `rgba` with an alpha component and the color's opacity will not affect the opacity of the 1px stroke, if it is used. */
  val fillColor: Long? = null,
  /** The opacity of the entire fill layer. In contrast to the `fill-color`, this value will also affect the 1px stroke around the fill, if the stroke is used. */
  val fillOpacity: Double? = null,
  /** The outline color of the fill. Matches the value of `fill-color` if unspecified. */
  val fillOutlineColor: Long? = null,
  /** Name of image in sprite to use for drawing image fills. For seamless patterns, image width and height must be a factor of two (2, 4, 8, ..., 512). Note that zoom-dependent expressions will be evaluated only at integer zoom levels. */
  val fillPattern: String? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): PolygonAnnotationOptions {
      val geometry = PolygonDecoder.fromList(list[0] as List<Any?>)
      val fillSortKey = list[1] as Double?
      val fillColor = list[2].let { if (it is Int) it.toLong() else it as Long? }
      val fillOpacity = list[3] as Double?
      val fillOutlineColor = list[4].let { if (it is Int) it.toLong() else it as Long? }
      val fillPattern = list[5] as String?
      return PolygonAnnotationOptions(geometry, fillSortKey, fillColor, fillOpacity, fillOutlineColor, fillPattern)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      geometry.toList(),
      fillSortKey,
      fillColor,
      fillOpacity,
      fillOutlineColor,
      fillPattern,
    )
  }
}
@Suppress("UNCHECKED_CAST")
private object OnPolygonAnnotationClickListenerCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          PolygonDecoder.fromList(it)
        }
      }
      129.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          PolygonAnnotation.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?) {
    when (value) {
      is Polygon -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      is PolygonAnnotation -> {
        stream.write(129)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/** Generated class from Pigeon that represents Flutter messages that can be called from Kotlin. */
@Suppress("UNCHECKED_CAST")
class OnPolygonAnnotationClickListener(private val binaryMessenger: BinaryMessenger, private val messageChannelSuffix: String = "") {
  companion object {
    /** The codec used by OnPolygonAnnotationClickListener. */
    val codec: MessageCodec<Any?> by lazy {
      OnPolygonAnnotationClickListenerCodec
    }
  }
  fun onPolygonAnnotationClick(annotationArg: PolygonAnnotation, callback: (Result<Unit>) -> Unit) {
    val separatedMessageChannelSuffix = if (messageChannelSuffix.isNotEmpty()) ".$messageChannelSuffix" else ""
    val channelName = "dev.flutter.pigeon.mapbox_maps_flutter.OnPolygonAnnotationClickListener.onPolygonAnnotationClick$separatedMessageChannelSuffix"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(annotationArg)) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          callback(Result.success(Unit))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      }
    }
  }
}
@Suppress("UNCHECKED_CAST")
private object _PolygonAnnotationMessengerCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          PolygonDecoder.fromList(it)
        }
      }
      129.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          PolygonAnnotation.fromList(it)
        }
      }
      130.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          PolygonAnnotation.fromList(it)
        }
      }
      131.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          PolygonAnnotationOptions.fromList(it)
        }
      }
      132.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          PolygonAnnotationOptions.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?) {
    when (value) {
      is Polygon -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      is PolygonAnnotation -> {
        stream.write(129)
        writeValue(stream, value.toList())
      }
      is PolygonAnnotation -> {
        stream.write(130)
        writeValue(stream, value.toList())
      }
      is PolygonAnnotationOptions -> {
        stream.write(131)
        writeValue(stream, value.toList())
      }
      is PolygonAnnotationOptions -> {
        stream.write(132)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/** Generated interface from Pigeon that represents a handler of messages from Flutter. */
interface _PolygonAnnotationMessenger {
  fun create(managerId: String, annotationOption: PolygonAnnotationOptions, callback: (Result<PolygonAnnotation>) -> Unit)
  fun createMulti(managerId: String, annotationOptions: List<PolygonAnnotationOptions>, callback: (Result<List<PolygonAnnotation>>) -> Unit)
  fun update(managerId: String, annotation: PolygonAnnotation, callback: (Result<Unit>) -> Unit)
  fun delete(managerId: String, annotation: PolygonAnnotation, callback: (Result<Unit>) -> Unit)
  fun deleteAll(managerId: String, callback: (Result<Unit>) -> Unit)
  fun setFillAntialias(managerId: String, fillAntialias: Boolean, callback: (Result<Unit>) -> Unit)
  fun getFillAntialias(managerId: String, callback: (Result<Boolean?>) -> Unit)
  fun setFillEmissiveStrength(managerId: String, fillEmissiveStrength: Double, callback: (Result<Unit>) -> Unit)
  fun getFillEmissiveStrength(managerId: String, callback: (Result<Double?>) -> Unit)
  fun setFillTranslate(managerId: String, fillTranslate: List<Double?>, callback: (Result<Unit>) -> Unit)
  fun getFillTranslate(managerId: String, callback: (Result<List<Double?>?>) -> Unit)
  fun setFillTranslateAnchor(managerId: String, fillTranslateAnchor: FillTranslateAnchor, callback: (Result<Unit>) -> Unit)
  fun getFillTranslateAnchor(managerId: String, callback: (Result<FillTranslateAnchor?>) -> Unit)

  companion object {
    /** The codec used by _PolygonAnnotationMessenger. */
    val codec: MessageCodec<Any?> by lazy {
      _PolygonAnnotationMessengerCodec
    }
    /** Sets up an instance of `_PolygonAnnotationMessenger` to handle messages through the `binaryMessenger`. */
    @Suppress("UNCHECKED_CAST")
    fun setUp(binaryMessenger: BinaryMessenger, api: _PolygonAnnotationMessenger?, messageChannelSuffix: String = "") {
      val separatedMessageChannelSuffix = if (messageChannelSuffix.isNotEmpty()) ".$messageChannelSuffix" else ""
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.create$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            val annotationOptionArg = args[1] as PolygonAnnotationOptions
            api.create(managerIdArg, annotationOptionArg) { result: Result<PolygonAnnotation> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                val data = result.getOrNull()
                reply.reply(wrapResult(data))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.createMulti$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            val annotationOptionsArg = args[1] as List<PolygonAnnotationOptions>
            api.createMulti(managerIdArg, annotationOptionsArg) { result: Result<List<PolygonAnnotation>> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                val data = result.getOrNull()
                reply.reply(wrapResult(data))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.update$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            val annotationArg = args[1] as PolygonAnnotation
            api.update(managerIdArg, annotationArg) { result: Result<Unit> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                reply.reply(wrapResult(null))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.delete$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            val annotationArg = args[1] as PolygonAnnotation
            api.delete(managerIdArg, annotationArg) { result: Result<Unit> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                reply.reply(wrapResult(null))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.deleteAll$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            api.deleteAll(managerIdArg) { result: Result<Unit> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                reply.reply(wrapResult(null))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.setFillAntialias$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            val fillAntialiasArg = args[1] as Boolean
            api.setFillAntialias(managerIdArg, fillAntialiasArg) { result: Result<Unit> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                reply.reply(wrapResult(null))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.getFillAntialias$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            api.getFillAntialias(managerIdArg) { result: Result<Boolean?> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                val data = result.getOrNull()
                reply.reply(wrapResult(data))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.setFillEmissiveStrength$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            val fillEmissiveStrengthArg = args[1] as Double
            api.setFillEmissiveStrength(managerIdArg, fillEmissiveStrengthArg) { result: Result<Unit> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                reply.reply(wrapResult(null))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.getFillEmissiveStrength$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            api.getFillEmissiveStrength(managerIdArg) { result: Result<Double?> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                val data = result.getOrNull()
                reply.reply(wrapResult(data))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.setFillTranslate$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            val fillTranslateArg = args[1] as List<Double?>
            api.setFillTranslate(managerIdArg, fillTranslateArg) { result: Result<Unit> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                reply.reply(wrapResult(null))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.getFillTranslate$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            api.getFillTranslate(managerIdArg) { result: Result<List<Double?>?> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                val data = result.getOrNull()
                reply.reply(wrapResult(data))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.setFillTranslateAnchor$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            val fillTranslateAnchorArg = FillTranslateAnchor.ofRaw(args[1] as Int)!!
            api.setFillTranslateAnchor(managerIdArg, fillTranslateAnchorArg) { result: Result<Unit> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                reply.reply(wrapResult(null))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.mapbox_maps_flutter._PolygonAnnotationMessenger.getFillTranslateAnchor$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val managerIdArg = args[0] as String
            api.getFillTranslateAnchor(managerIdArg) { result: Result<FillTranslateAnchor?> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                val data = result.getOrNull()
                reply.reply(wrapResult(data?.raw))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
    }
  }
}