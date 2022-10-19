package com.jeton.twentyleke.feature.scan

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.TimeUnit

@SuppressLint("UnsafeOptInUsageError")
class BarCodeAnalyser(
    private val onBarCodeDetected: (barcodes: List<Barcode>) -> Unit,
) : ImageAnalysis.Analyzer {

    private var lastAnalysedTimestamp = 0L

    override fun analyze(imageProxy: ImageProxy) {
        val currentTimestamp = System.currentTimeMillis()
        if (currentTimestamp - lastAnalysedTimestamp < TimeUnit.SECONDS.toMillis(1)) {
            imageProxy.close()
            return
        }

        imageProxy.image?.let { imageToAnalyze ->
            val options = BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
                .build()
            val barcodeScanner = BarcodeScanning.getClient(options)

            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            val imageToProcess = InputImage.fromMediaImage(imageToAnalyze, rotationDegrees)

            barcodeScanner.process(imageToProcess)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.isNotEmpty()) {
                        onBarCodeDetected(barcodes)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("TAG", "BarcodeAnalyzer: Something went wrong, $exception")
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }

            lastAnalysedTimestamp = currentTimestamp
        }
    }
}