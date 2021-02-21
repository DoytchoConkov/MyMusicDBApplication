package exam.web;

import exam.models.bindings.UserLoginBindingModel;
import exam.models.bindings.UserRegisterBindingModel;
import exam.models.security.CurrentUser;
import exam.models.service.UserServiceModel;
import exam.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserControler {
    private final CurrentUser currentUser;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserControler(CurrentUser currentUser, UserService userService, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        if (!currentUser.isAnonymous()) {
            return "redirect:/home";
        }
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!currentUser.isAnonymous()) {
            return "redirect:/home";
        }
        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";
        }
        if (this.userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class))) {
            return "redirect:/users/login";
        } else {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";
        }
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        if (!currentUser.isAnonymous()) {
            return "redirect:/home";
        }
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!currentUser.isAnonymous()) {
            return "redirect:/home";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }
        if (userService.haveUser(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword())) {
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
        redirectAttributes.addFlashAttribute("notExist", true);
        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logout() {
        if (currentUser.isAnonymous()) {
            return "redirect:/users/login";
        }
        currentUser.setAnonymous(true);
        return "redirect:/";
    }
}
