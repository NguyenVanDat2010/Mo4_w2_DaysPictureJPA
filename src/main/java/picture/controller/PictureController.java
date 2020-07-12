package picture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import picture.model.Picture;
import picture.service.impl.IPictureService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pictures")
public class PictureController {
    @Autowired
    private IPictureService pictureService;

    @GetMapping
    public ModelAndView showPicture(){
        ModelAndView modelAndView = new ModelAndView("daysPicture");
        modelAndView.addObject("comment",timeConvert());
        modelAndView.addObject("picture",new Picture());
        return modelAndView;
    }

    @PostMapping("/save")
    public String savePicture(Picture picture, RedirectAttributes redirectAttributes){
        pictureService.save(picture);
        redirectAttributes.addFlashAttribute("success","Add comment successfully!");
        return "redirect:/pictures";
    }

    @GetMapping("/like/{id}")
    public String likePicture(@PathVariable Long id){
//        ModelAndView modelAndView = new ModelAndView("redirect:/pictures");

        pictureService.updateLike(id);
        return "redirect:/pictures";
    }

    private List<Picture> timeConvert() {
        List<Picture> pictureList = pictureService.findAll();
        List<Picture> commentLike = new ArrayList<>();
        LocalDateTime myDateObj = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Picture picture:pictureList){
            if (picture.getCreated_at().toLocalDateTime().format(formatter).equals(myDateObj.format(formatter))){
                commentLike.add(picture);
            }
        }
        return commentLike;
    }
}
