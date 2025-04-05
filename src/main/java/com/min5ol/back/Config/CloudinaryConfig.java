package com.min5ol.back.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dxavift7v",
            "api_key", "412354414592993",
            "api_secret", "qwoq7QqILiAOotBPf287GqbDGlY"
        ));
    }
}
