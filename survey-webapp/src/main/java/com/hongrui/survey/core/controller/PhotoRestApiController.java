package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.RandomUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import com.wlw.pylon.web.rest.annotation.RestApiController;

import com.hongrui.survey.core.service.PhotoService;
import com.hongrui.survey.core.model.PhotoModel;
import com.hongrui.survey.core.vo.PhotoVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/survey")
public class PhotoRestApiController {

    private final Logger logger = LoggerFactory.getLogger(PhotoRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private PhotoService photoService;

    @Value("${photo.base.url}")
    private String baseDirectory;

    @GetMapping(value = "/photo/{id}")
    public void getPhotoById(@PathVariable Long id,
                             HttpServletResponse response) {

        PhotoModel photoModel = photoService.findByPrimaryKey(id);
        response.setContentType(photoModel.getContentType());
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = response.getOutputStream();
            inputStream = FileUtils.openInputStream(new File(baseDirectory + "/" + photoModel.getPath()));
            IOUtils.copy(inputStream, outputStream);

        } catch (IOException e) {
            logger.error("expected error ", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }

    }


    @PostMapping(value = "/{taskId}/{photoType}/photo")
    public ResponseEnvelope<Integer> uploadPhoto(@RequestParam MultipartFile file,
                                                 @PathVariable Long taskId,
                                                 @PathVariable Long photoType) {
        PhotoModel photoModel = new PhotoModel();
        photoModel.setCreateTime(new Date());
        photoModel.setName(file.getName());
        photoModel.setPhotoType(photoType);
        photoModel.setTaskId(taskId);
        photoModel.setContentType(file.getContentType());
        String filename = RandomUtil.createRandom(true, 12);
        String path = baseDirectory + "/" + filename;
        photoModel.setPath(filename);
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            outputStream = FileUtils.openOutputStream(new File(path));
            IOUtils.copy(inputStream, outputStream);

        } catch (IOException e) {
            logger.error("expected error ", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }

        Integer result = photoService.createSelective(photoModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @DeleteMapping(value = "/photo/{id}")
    public ResponseEnvelope<String> deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }


}
