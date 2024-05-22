// Autogenerated from Pigeon (v18.0.0), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.mapbox.maps.mapbox_maps.pigeons

import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StandardMessageCodec

private fun createConnectionError(channelName: String): FlutterError {
  return FlutterError("channel-error", "Unable to establish connection on channel: '$channelName'.", "")
}

enum class LoggingLevel(val raw: Int) {
  /** Verbose log data to understand how the code executes. */
  DEBUG(0),
  /** Logs related to normal application behavior. */
  INFO(1),
  /** To log a situation that might be a problem, or an unusual situation. */
  WARNING(2),
  /** A log message providing information when a significant error occurred */
  ERROR(3);

  companion object {
    fun ofRaw(raw: Int): LoggingLevel? {
      return values().firstOrNull { it.raw == raw }
    }
  }
}
/**
 * An interface for implementing log writing backends, e.g. for using platform specific log backends or logging to a notification service.
 *
 * Generated class from Pigeon that represents Flutter messages that can be called from Kotlin.
 */
@Suppress("UNCHECKED_CAST")
class LogWriterBackend(private val binaryMessenger: BinaryMessenger, private val messageChannelSuffix: String = "") {
  companion object {
    /** The codec used by LogWriterBackend. */
    val codec: MessageCodec<Any?> by lazy {
      StandardMessageCodec()
    }
  }
  /** Writes a log message with a given level. */
  fun writeLog(levelArg: LoggingLevel, messageArg: String, callback: (Result<Unit>) -> Unit) {
    val separatedMessageChannelSuffix = if (messageChannelSuffix.isNotEmpty()) ".$messageChannelSuffix" else ""
    val channelName = "dev.flutter.pigeon.mapbox_maps_flutter.LogWriterBackend.writeLog$separatedMessageChannelSuffix"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(levelArg.raw, messageArg)) {
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