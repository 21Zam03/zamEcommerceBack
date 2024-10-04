package com.zam.zamMarket.service.impl;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.firebase.cloud.StorageClient;
import com.zam.zamMarket.entity.ImageEntity;
import com.zam.zamMarket.payload.dtos.BlobDto;
import com.zam.zamMarket.payload.response.MessageResponse;
import com.zam.zamMarket.repository.ImageRepository;
import com.zam.zamMarket.service.FireBaseStorageService;
import com.zam.zamMarket.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class FireBaseStorageServiceImpl implements FireBaseStorageService {

    @Override
    public BlobDto uploadFile(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();

        String fileName = FileUtil.getFileName(file);

        String filePath = "Products/" + fileName;

        BlobInfo blobInfo = BlobInfo.newBuilder(StorageClient.getInstance().bucket().getName(), filePath)
                .setContentType(file.getContentType())
                .build();

        Blob blob = StorageClient.getInstance().bucket().create(blobInfo.getName(), inputStream, blobInfo.getContentType());
        //blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

        String encodedFilePath = URLEncoder.encode(filePath, StandardCharsets.UTF_8);
        String url = "https://firebasestorage.googleapis.com/v0/b/" + StorageClient.getInstance().bucket().getName() + "/o/" +
                encodedFilePath + "?alt=media";

        BlobDto blobDto = new BlobDto(blob.getMediaLink(), url, blob.getSize());

        log.info("The file was uploaded successfully");
        return blobDto;
    }

    @Override
    public byte[] downloadFile(String fileName) throws IOException {
        Blob blob = StorageClient.getInstance().bucket().get(fileName);
        if (blob == null) {
            throw new FileNotFoundException("File not found: " + fileName);
        }
        return blob.getContent();
    }

    @Override
    public MessageResponse deleteFile(String fileName) throws IOException {
        String filePath = "Products/"+fileName;
        Blob blob = StorageClient.getInstance().bucket().get(filePath);
        if (blob == null) {
            throw new IOException("File not found: " + fileName);
        }
        boolean deleted = blob.delete();
        if (!deleted) {
            throw new IOException("Failed to delete file: " + fileName);
        }
        return new MessageResponse("The file was deleted successfully");
    }

}
