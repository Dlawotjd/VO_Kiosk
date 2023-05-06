package com.vo.vo_kiosk.View

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.databinding.FragmentFacedetectBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FaceDetectFragment : Fragment() {

    private var _binding : FragmentFacedetectBinding? = null
    private val binding get() = _binding!!
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var imageCapture: ImageCapture

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            startCamera()
        } else {
            Toast.makeText(requireContext(), "카메라 권한이 없으면 앱을 사용할 수 없습니다", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFacedetectBinding.inflate(inflater, container, false)

        cameraExecutor = Executors.newSingleThreadExecutor()

        checkCameraPermission()

        findNavController().navigate(R.id.action_facedetectFragment_to_choiceMenuFragment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        takePicture()

    }

//  카메라 권한 요청
    private fun requestCameraPermission() {
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

//  카메라 시작
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
//                    it.setSurfaceProvider(binding.previewView.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (exc: Exception) {
                Log.d("cameraProvider_Error", "$exc")
                takePicture()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

//  카메라 권한 요청
    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission()
        } else {
            startCamera()
        }
    }

    private fun takePicture() {

        Handler(Looper.getMainLooper()).postDelayed({

            val outputOptions = ImageCapture.OutputFileOptions.Builder(createTempFile())
                .build()
//            binding.faceText.text = "얼굴 인식 중 입니다."

            imageCapture.takePicture(outputOptions, cameraExecutor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        val savedUri = outputFileResults.savedUri ?: return
                        detectFaces(savedUri.toString())

                    }

                    override fun onError(exception: ImageCaptureException) {
                        takePicture()
                    }
                })
        }, 1500) // 사용자 인식 얼굴 위치에 조절을 위한 1.5초 후에 실행
    }

//  임시 파일 경로
    private fun createTempFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }

//  얼굴 이미지 인식
    private fun detectFaces(imagePath: String) {
        val image = InputImage.fromFilePath(requireContext(), Uri.parse(imagePath))

        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_NONE)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE)
            .build()

        val detector = FaceDetection.getClient(options)

        detector.process(image)
            .addOnSuccessListener { faces ->
                if (faces.isNotEmpty()) {
//                    얼굴 인식을 성공 후 api 호출 후 결과에 따른 화면 이동 -> 결과에 따른 bundle에 데이터 전송
                    binding.faceText.text = "얼굴 인식에 성공했습니다."

                    findNavController().navigate(R.id.action_facedetectFragment_to_choiceMenuFragment)

                } else {
                    binding.faceText.text = "얼굴 인식에 실패하였습니다."
                    takePicture()
                }
            }

            .addOnFailureListener { e ->
                // 처리 중 오류가 발생했을 때의 동작을 구현합니다.
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(
                        requireContext(),
                        "얼굴 인식 실패: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}