package com.min5ol.back.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dxavift7v",
                "api_key", "412354414592993",
                "api_secret", "qwoq7QqILiAOotBPf287GqbDGlY"
        ));
    }

    @SuppressWarnings("unchecked")  // 강제 캐스팅에 대해 경고 숨기기
    public String uploadImage(String imagePath) throws IOException {
        // 업로드 결과를 Map<String, Object>로 강제 캐스팅
        Map<String, Object> uploadResult = (Map<String, Object>) cloudinary.uploader().upload(imagePath, ObjectUtils.emptyMap());
        
        // "secure_url"을 반환하여 업로드된 이미지 URL을 얻기
        return (String) uploadResult.get("secure_url");  // 업로드된 이미지의 secure_url 반환
    }
}
