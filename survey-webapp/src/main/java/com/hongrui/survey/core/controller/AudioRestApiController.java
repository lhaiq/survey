package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.AudioType;
import com.hongrui.survey.core.RandomUtil;
import com.hongrui.survey.core.model.AudioModel;
import com.hongrui.survey.core.model.PhotoModel;
import com.hongrui.survey.core.service.AudioService;
import com.hongrui.survey.core.service.PhotoService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

@RestController
@RequestMapping("/survey")
public class AudioRestApiController {

    private final Logger logger = LoggerFactory.getLogger(AudioRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private AudioService audioService;

    @Value("${audio.base.url}")
    private String baseDirectory;

    @GetMapping(value = "/audio/{id}")
    public void getAudioById(@PathVariable Long id,
                             HttpServletResponse response) {

        AudioModel audioModel = audioService.findByPrimaryKey(id);
        response.setContentType(audioModel.getContentType());
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = FileUtils.openInputStream(new File(audioModel.getPath()));
            IOUtils.copy(inputStream, outputStream);

        } catch (IOException e) {
            logger.error("expected error ", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }

    }


    @PostMapping(value = "/{taskId}/audio/{filename}/{index}")
    public ResponseEnvelope<Integer> uploadAudio(@RequestParam MultipartFile file,
                                                 @PathVariable Long taskId,
                                                 @PathVariable String filename,
                                                 @PathVariable Integer index,
                                                 @RequestParam Integer lastIndex) {
        AudioModel audioModel = new AudioModel();
        audioModel.setCreatTime(new Date());
        audioModel.setName(filename);
        audioModel.setContentType(file.getContentType());
        audioModel.setTaskId(taskId);
        audioModel.setIndex(index);
        audioModel.setType(AudioType.TEMPORARY.getType());
        String path = baseDirectory + "/" + RandomUtil.createRandom(true, 12);
        audioModel.setPath(path);
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

        Integer result = audioService.createSelective(audioModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @DeleteMapping(value = "/audio/{id}")
    public ResponseEnvelope<Integer> deleteAudio(@PathVariable Long id) {
        Integer result = audioService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


}
