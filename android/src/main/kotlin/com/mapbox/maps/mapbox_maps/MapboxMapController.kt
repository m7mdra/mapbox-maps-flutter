package com.mapbox.maps.mapbox_maps

import android.content.Context
import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.setViewTreeLifecycleOwner
import com.mapbox.common.DownloadOptions
import com.mapbox.common.HttpHeaders
import com.mapbox.common.HttpRequest
import com.mapbox.common.HttpResponse
import com.mapbox.common.HttpServiceFactory
import com.mapbox.common.HttpServiceInterceptorInterface
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.mapbox_maps.annotation.AnnotationController
import com.mapbox.maps.pigeons.FLTMapInterfaces
import com.mapbox.maps.pigeons.FLTSettings
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView
import kotlin.collections.set

class MapboxMapController(
  context: Context,
  mapInitOptions: MapInitOptions,
  lifecycleProvider: MapboxMapsPlugin.LifecycleProvider,
  eventTypes: List<String>,
  messenger: BinaryMessenger,
  channelSuffix: Int,
  pluginVersion: String
) : PlatformView,
  DefaultLifecycleObserver,
  MethodChannel.MethodCallHandler {

  private var mapView: MapView? = null
  private var mapboxMap: MapboxMap? = null
  private val methodChannel: MethodChannel
  private val styleController: StyleController
  private val cameraController: CameraController
  private val projectionController: MapProjectionController
  private val mapInterfaceController: MapInterfaceController
  private val animationController: AnimationController
  private val annotationController: AnnotationController
  private val locationComponentController: LocationComponentController
  private val gestureController: GestureController
  private val logoController: LogoController
  private val attributionController: AttributionController
  private val scaleBarController: ScaleBarController
  private val compassController: CompassController

  private val proxyBinaryMessenger = ProxyBinaryMessenger(messenger, "/map_$channelSuffix")

  private val lifecycleHelper: LifecycleHelper

  init {
    val mapView = MapView(context, mapInitOptions)
    val mapboxMap = mapView.getMapboxMap()
    this.mapView = mapView
    this.mapboxMap = mapboxMap
    styleController = StyleController(mapboxMap)
    cameraController = CameraController(mapboxMap)
    projectionController = MapProjectionController(mapboxMap)
    mapInterfaceController = MapInterfaceController(mapboxMap)
    animationController = AnimationController(mapboxMap)
    annotationController = AnnotationController(mapView, mapboxMap)
    locationComponentController = LocationComponentController(mapView)
    gestureController = GestureController(mapView)
    logoController = LogoController(mapView)
    attributionController = AttributionController(mapView)
    scaleBarController = ScaleBarController(mapView)
    compassController = CompassController(mapView)

    changeUserAgent(pluginVersion)

    lifecycleHelper = LifecycleHelper(lifecycleProvider.getLifecycle()!!)
    mapView.setViewTreeLifecycleOwner(lifecycleHelper)

    FLTMapInterfaces.StyleManager.setup(proxyBinaryMessenger, styleController)
    FLTMapInterfaces._CameraManager.setup(proxyBinaryMessenger, cameraController)
    FLTMapInterfaces.Projection.setup(proxyBinaryMessenger, projectionController)
    FLTMapInterfaces._MapInterface.setup(proxyBinaryMessenger, mapInterfaceController)
    FLTMapInterfaces._AnimationManager.setup(proxyBinaryMessenger, animationController)
    annotationController.setup(proxyBinaryMessenger)
    FLTSettings.LocationComponentSettingsInterface.setup(proxyBinaryMessenger, locationComponentController)
    FLTSettings.LogoSettingsInterface.setup(proxyBinaryMessenger, logoController)
    FLTSettings.GesturesSettingsInterface.setup(proxyBinaryMessenger, gestureController)
    FLTSettings.AttributionSettingsInterface.setup(proxyBinaryMessenger, attributionController)
    FLTSettings.ScaleBarSettingsInterface.setup(proxyBinaryMessenger, scaleBarController)
    FLTSettings.CompassSettingsInterface.setup(proxyBinaryMessenger, compassController)
    methodChannel = MethodChannel(proxyBinaryMessenger, "plugins.flutter.io")
    methodChannel.setMethodCallHandler(this)
    mapboxMap.subscribe(
      { event ->
        methodChannel.invokeMethod(getEventMethodName(event.type), event.data.toJson())
      },
      eventTypes
    )
  }

  override fun getView(): View? {
    return mapView
  }

  override fun dispose() {
    if (mapView == null) {
      return
    }
    lifecycleHelper.dispose()
    mapView = null
    mapboxMap = null
    methodChannel.setMethodCallHandler(null)
    FLTMapInterfaces.StyleManager.setup(proxyBinaryMessenger, null)
    FLTMapInterfaces._CameraManager.setup(proxyBinaryMessenger, null)
    FLTMapInterfaces.Projection.setup(proxyBinaryMessenger, null)
    FLTMapInterfaces._MapInterface.setup(proxyBinaryMessenger, null)
    FLTMapInterfaces._AnimationManager.setup(proxyBinaryMessenger, null)
    annotationController.dispose(proxyBinaryMessenger)
    FLTSettings.LocationComponentSettingsInterface.setup(proxyBinaryMessenger, null)
    FLTSettings.LogoSettingsInterface.setup(proxyBinaryMessenger, null)
    FLTSettings.GesturesSettingsInterface.setup(proxyBinaryMessenger, null)
    FLTSettings.CompassSettingsInterface.setup(proxyBinaryMessenger, null)
    FLTSettings.ScaleBarSettingsInterface.setup(proxyBinaryMessenger, null)
    FLTSettings.AttributionSettingsInterface.setup(proxyBinaryMessenger, null)
  }

  override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
    when (call.method) {
      "map#subscribe" -> {
        val eventType = call.argument<String>("event")!!
        println(eventType)
        mapboxMap?.subscribe(
          { event ->
            methodChannel.invokeMethod(getEventMethodName(eventType), event.data.toJson())
          },
          listOf(eventType)
        )
        result.success(null)
      }

      "annotation#create_manager" -> {
        annotationController.handleCreateManager(call, result)
      }

      "annotation#remove_manager" -> {
        annotationController.handleRemoveManager(call, result)
      }

      "gesture#add_listeners" -> {
        gestureController.addListeners(proxyBinaryMessenger)
        result.success(null)
      }

      "gesture#remove_listeners" -> {
        gestureController.removeListeners()
        result.success(null)
      }

      else -> {
        result.notImplemented()
      }
    }
  }

  private fun changeUserAgent(version: String) {
    HttpServiceFactory.getInstance().setInterceptor(
      object : HttpServiceInterceptorInterface {
        override fun onRequest(request: HttpRequest): HttpRequest {
          request.headers[HttpHeaders.USER_AGENT] =
            "${request.headers[HttpHeaders.USER_AGENT]} Flutter Plugin/$version"
          return request
        }

        override fun onDownload(download: DownloadOptions): DownloadOptions {
          return download
        }

        override fun onResponse(response: HttpResponse): HttpResponse {
          return response
        }
      }
    )
  }

  private fun getEventMethodName(eventType: String) = "event#$eventType"

  /*
    Mirrors lifecycle of the parent, with one addition - switches to DESTROYED state
    when `dispose` is called.
    `parentLifecycle` is the lifecycle of the Flutter activity, which may live much longer then
    the MapView attached.
  */
  private class LifecycleHelper(override val lifecycle: Lifecycle) : LifecycleOwner, DefaultLifecycleObserver {

    val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    init {
      lifecycle.addObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
      lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun onStart(owner: LifecycleOwner) {
      lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    override fun onResume(owner: LifecycleOwner) {
      lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    override fun onPause(owner: LifecycleOwner) {
      lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    override fun onStop(owner: LifecycleOwner) {
      lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun onDestroy(owner: LifecycleOwner) {
      lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    fun dispose() {
      lifecycle.removeObserver(this)
      // fires MapView.onStop
      lifecycleRegistry.currentState = Lifecycle.State.CREATED
      // fires MapView.onDestroy
      lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }
  }

}