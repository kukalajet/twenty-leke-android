package com.jeton.twentyleke.feature.scan.view

import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.jeton.twentyleke.feature.scan.BarCodeAnalyser
import java.util.concurrent.Executors

@Composable
fun ScanCamera(
    onScannedValue: (String) -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var preview by remember { mutableStateOf<Preview?>(null) }

    AndroidView(
        factory = { viewContext ->
            PreviewView(viewContext).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            }
        },
        modifier = Modifier
            .fillMaxSize(),
        update = { previewView ->
            val cameraSelector =
                CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
            val cameraExecutor = Executors.newSingleThreadExecutor()
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

            cameraProviderFuture.addListener({
                preview = Preview.Builder().build()
                    .also { it.setSurfaceProvider(previewView.surfaceProvider) }
                val cameraProvider = cameraProviderFuture.get()
                val barCodeAnalyser = BarCodeAnalyser { barcodes ->
                    barcodes.forEach { barcode ->
                        barcode.rawValue?.let { value ->
                            onScannedValue(value)
                        }
                    }
                }
                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also { it.setAnalyzer(cameraExecutor, barCodeAnalyser) }

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalysis
                    )
                } catch (e: Exception) {
                    Log.d("TAG", "CameraPreview: ${e.localizedMessage}")
                }

            }, ContextCompat.getMainExecutor(context))
        })
}