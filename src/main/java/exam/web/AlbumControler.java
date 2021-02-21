package exam.web;

import exam.models.bindings.AlbumBindingModel;
import exam.models.security.CurrentUser;
import exam.models.service.AlbumServiceModel;
import exam.services.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/album")
public class AlbumControler {
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;
    private final AlbumService albumService;

    public AlbumControler(CurrentUser currentUser, ModelMapper modelMapper, AlbumService albumService) {
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
        this.albumService = albumService;
    }
    @GetMapping("/add")
    public String addTask(Model model) {
        if (currentUser.isAnonymous()) {
            return "redirect:/users/login";
        }
        if (!model.containsAttribute("albumBindingModel")) {
            model.addAttribute("albumBindingModel", new AlbumBindingModel());
        }
        return "add-album";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("albumBindingModel") AlbumBindingModel albumBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (currentUser.isAnonymous()) {
            return "redirect:/users/login";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("albumBindingModel", albumBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumBindingModel", bindingResult);
            return "redirect:add";
        }
        this.albumService.addAlbum(modelMapper.map(albumBindingModel, AlbumServiceModel.class));
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String addTask(@PathVariable String id, Model model) {
        if (currentUser.isAnonymous()) {
            return "redirect:/users/login";
        }
this.albumService.deleteAlbum(id);
        return "redirect:/";
    }
}
