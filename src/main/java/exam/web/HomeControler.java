package exam.web;

import exam.models.security.CurrentUser;
import exam.services.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControler {
    private final CurrentUser currentUser;
    private final AlbumService albumService;

    public HomeControler(CurrentUser currentUser, AlbumService albumService) {
        this.currentUser = currentUser;
        this.albumService = albumService;
    }
    @GetMapping("/")
    public String index(Model model) {
        if (!currentUser.isAnonymous()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("home")
    public String home(Model model) {
        if (currentUser.isAnonymous()) {
            return "redirect:/users/login";
        }
      model.addAttribute("albums", this.albumService.viewAll());
      model.addAttribute("totalCopies", this.albumService.getAllCopies());
        return "home";
    }
}
