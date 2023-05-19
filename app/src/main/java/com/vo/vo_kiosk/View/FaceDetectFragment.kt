package com.vo.vo_kiosk.View

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
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
import com.vo.vo_kiosk.DTO.FaceAge
import com.vo.vo_kiosk.DTO.FolderPath
import com.vo.vo_kiosk.NetWork.FaceDetectAPI
import com.vo.vo_kiosk.NetWork.ResponseDTO
import com.vo.vo_kiosk.NetWork.RetrofitClient
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.databinding.FragmentFacedetectBinding
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FaceDetectFragment : Fragment(), ImageCapture.OnImageSavedCallback {

    private var _binding : FragmentFacedetectBinding? = null
    private val binding get() = _binding!!
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var imageCapture: ImageCapture

    val call by lazy { RetrofitClient.getInstance() }
    val call2 by lazy { FaceDetectAPI.getInstance() }

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

        binding.faceText.text = "전명 카메라을 봐주세요!!"

        binding.faceButton.setOnClickListener {
            findNavController().navigate(R.id.action_facedetectFragment_to_Main_Fragment)
//            takePicture()
        }

        return binding.root
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
                    it.setSurfaceProvider(binding.previewView.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (exc: Exception) {
                Log.d("cameraProvider_Error", "$exc")
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

            imageCapture.takePicture(outputOptions, cameraExecutor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        val savedUri = outputFileResults.savedUri ?: return
                        val imagePath = savedUri.toString().substring(7)

                        detectFaces(savedUri.toString(), imagePath)
                        Log.d("이미지 경로", savedUri.toString())

                    }

                    override fun onError(exception: ImageCaptureException) {
                        Handler(Looper.getMainLooper()).post {
                            binding.faceText.text = "정면을 보고 다시 눌러주세요"
                        }
                    }
                })
        }, 500) // 사용자 인식 얼굴 위치에 조절을 위한 1.5초 후에 실행
    }
//  임시 파일 경로
    private fun createTempFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("${timeStamp}_", ".jpg", storageDir)
    }
//  얼굴 이미지 인식
    private fun detectFaces(imagePath: String, imagePath2: String) {
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
                    sendImageToServer(imagePath2)

                } else {
                    binding.faceText.text = "얼굴을 인식 시켜 주세요"
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

    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
        val savedUri = outputFileResults.savedUri ?: return
        val imagePath = savedUri.toString().substring(7)  // "file://" 제거
        Log.d("onImageSave", imagePath)
    }
    override fun onError(exception: ImageCaptureException) {
        takePicture()
    }
    private fun rotateImage(bitmap: Bitmap, rotationDegrees: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(rotationDegrees.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

//  이미지 사이즈 조절
    private fun resizeImage(imageFile: File, maxWidth: Int, maxHeight: Int): File {
        val options = BitmapFactory.Options().apply {
            // 이미지를 메모리에 로드하지 않고 크기만 읽어옴
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(imageFile.absolutePath, this)
            val imageWidth = outWidth
            val imageHeight = outHeight

            var scaleFactor = 1
            // 이미지 크기가 제한된 최대 크기를 초과하는 경우 축소 비율을 계산하여 크기 조정
            if (imageWidth > maxWidth || imageHeight > maxHeight) {
                val widthRatio = imageWidth.toFloat() / maxWidth.toFloat()
                val heightRatio = imageHeight.toFloat() / maxHeight.toFloat()
                scaleFactor = Math.ceil(Math.max(widthRatio, heightRatio).toDouble()).toInt()
            }

            inJustDecodeBounds = false
            inSampleSize = scaleFactor
        }

        val originalBitmap = BitmapFactory.decodeFile(imageFile.absolutePath, options)
        val rotationDegrees = getRotationDegrees(imageFile) // 이미지의 회전 각도 가져오기
        val rotatedBitmap = rotateImage(originalBitmap, rotationDegrees) // 이미지 회전
        originalBitmap.recycle() // 원본 비트맵 메모리 해제

        // 이미지를 축소하여 새로운 파일로 저장
        val resizedImageFile = File.createTempFile("resized_", ".jpg", requireContext().cacheDir)
        FileOutputStream(resizedImageFile).use { outputStream ->
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
            outputStream.flush()
        }

        return resizedImageFile
    }

//  이미지 회전 시키는 함수
    private fun getRotationDegrees(imageFile: File): Int {
        val exifInterface = ExifInterface(imageFile.absolutePath)
        val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90
            ExifInterface.ORIENTATION_ROTATE_180 -> 180
            ExifInterface.ORIENTATION_ROTATE_270 -> 270
            else -> 0
        }
    }

//  이미지 전송 API 함수
    private fun sendImageToServer(imagePath: String) {
        val imageFile = File(imagePath)
        val resizedImageFile = resizeImage(imageFile, 800, 800)

        // 파일을 MultipartBody.Part로 변환
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), resizedImageFile)
        val body = MultipartBody.Part.createFormData("image", resizedImageFile.name, requestFile)
        Log.d("sendImageData", resizedImageFile.toString())

        // 설명(description)을 MultipartBody.Part로 변환
        val description = RequestBody.create(MultipartBody.FORM, "This is an image")

        // API 호출
        call!!.uploadImage(body, description).enqueue(object : Callback<ResponseDTO> {
            override fun onResponse(call: Call<ResponseDTO>, response: Response<ResponseDTO>) {
                if (response.isSuccessful) {
                    Log.d("Upload", "Upload success!")
                    face_Age()
                } else {
                    Log.d("Upload", "Upload failed!")
                }
            }

            override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                Log.d("Upload", "Upload error: ${t.message}")
            }
        })
    }

    fun face_Age() {

        val folderPath = FolderPath("./images")

        call2!!.faceCheck(folderPath).enqueue(object : Callback<FaceAge>{
            override fun onResponse(call: Call<FaceAge>, response: Response<FaceAge>) {

                if (response.isSuccessful){
                    val faceAgeBundle = Bundle()
                    faceAgeBundle.putString("faceAge", response.body()!!.preictions)
                    findNavController().navigate(R.id.action_facedetectFragment_to_Main_Fragment, faceAgeBundle)
                }
            }

            override fun onFailure(call: Call<FaceAge>, t: Throwable) {
                Log.d("face Age Fail", "연령 검사 실패 다시 이미지 촬영을 해야됨")
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}