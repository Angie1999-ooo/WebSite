package com.shop.cosm.controller;
import com.microsoft.azure.cognitiveservices.vision.faceapi.FaceAPI;
import com.microsoft.azure.cognitiveservices.vision.faceapi.models.AzureRegions;
import com.shop.cosm.domain.Image;
import com.shop.cosm.domain.User;
import com.shop.cosm.library.*;
import com.shop.cosm.recognize.RecognizeRepo;
import com.shop.cosm.repos.ImageRepo;
import com.shop.cosm.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.util.Map;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

@Controller
public class MainController {
    final String key = "d3c9172caca7432bb5ddb72dab57ae38";
    final AzureRegions myRegion = AzureRegions.EASTUS;
    final String PERSON_GROUP_ID = "new_key3337774";
    private RecognizerwithAzure recognizerwithAzure;
    RecognizerwithOpencv recognizerwithOpencv = new RecognizerwithOpencv(new RecognizeRepo());

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ImageRepo imageRepo;


    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/apps")
    public String apps() {


        return "apps";
    }


    @GetMapping("/about")
    public String about() {

        return "about";
    }

    @GetMapping("/lll")
    public String webcam() {

        return "webcam";
    }

    @PostMapping("/lll")
    public String webcamm(@RequestParam("file") MultipartFile file1,Map<String, Object> model) {

        Image image= imageRepo.findByFilename(file1.getName()).get(0);
        return "webcam";
    }

    @GetMapping("/l")
    public String login_by_photo() {
        return "login_by_photo";
    }

    @PostMapping("/l")
    public String login_by_photo_(@RequestParam("file1") MultipartFile file1, Map<String, Object> model) throws IOException {
        FaceAPI client = FaceAPIManager.authenticate(myRegion, key);
        RecognizeAPIBuilderFactory factory = new RecognizeAPIBuilderFactory(new RecognizeRepo(), client, PERSON_GROUP_ID);
        ISettingRecognize recognizer = factory.GetBuilder(RecognizerwithAzure.class);
        recognizer.train();
        String names = recognizer.recognize(file1.getBytes(), 90);
        User user = userRepo.findByUsername(names);
        model.put("name",user);
        return "redirect:/";
    }


    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile file
    ) throws IOException {
        RecognizeRepo recognizeRepo = new RecognizeRepo();
        RecognizerwithOpencv recognizerwithOpencv1 = new RecognizerwithOpencv(new RecognizeRepo());
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String filename =  recognizeRepo.add(file.getBytes(),user.getUsername() ,extension);
        recognizerwithOpencv1.train();
        return "main";
    }


    @PostMapping("delete")
    public String delete(@RequestParam("filename") String fileid) {
        RecognizeRepo lib = new RecognizeRepo();
        lib.delete(fileid);
        return "main";
    }

}











