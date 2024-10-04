package com.zam.zamMarket.service;

import com.google.cloud.storage.Blob;
import com.zam.zamMarket.payload.dtos.BlobDto;
import com.zam.zamMarket.payload.response.MessageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FireBaseStorageService {

    public BlobDto uploadFile(MultipartFile file) throws Exception;
    byte[] downloadFile(String fileName) throws IOException;
    public MessageResponse deleteFile(String fileName) throws IOException;

}
