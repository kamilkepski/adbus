package pl.kepski.busdemo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.kepski.busdemo.config.CustomUserDetails;
import pl.kepski.busdemo.entity.Ad;
import pl.kepski.busdemo.entity.User;
import pl.kepski.busdemo.repository.AdRepository;
import pl.kepski.busdemo.service.AdService;
import pl.kepski.busdemo.repository.UserRepository;

import java.util.List;

@Controller
public class AdController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private AdService adService;

    @GetMapping(path = "/add_ad")
    public String addAd(Model model) {
        model.addAttribute("ad", new Ad());
        return "add_ad";
    }

    @PostMapping("/process_adding_ad")
    public String processAddingAd(@Valid Ad ad, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        boolean thereAreErrors = bindingResult.hasErrors();
        if (thereAreErrors) {
            model.addAttribute("ad", ad);
            return "add_ad";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((CustomUserDetails)principal).getUsername();
        User user = userRepository.findByEmail(username);
        if (adRepository.countUserAds(user.getId()) < 5) {
            ad.setUser(user);
            adRepository.save(ad);
        }  else {
            redirectAttributes.addFlashAttribute("message", "Osiągnięto limit darmowych ogłoszeń! 5/5");
        }

        return "redirect:/my_ads";
    }

    @GetMapping("/list_ads")
    public String getAllPages(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/list_ads/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Ad> page = adService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Ad> listAds = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("listAds", listAds);

        return "ads";
    }

    @GetMapping("/offer/{id}")
    public String getOffer(Model model, @PathVariable("id") Long id) {

        if(adService.findById(id).isPresent())
        {
            adService.findById(id).ifPresent(o -> model.addAttribute("ad", o));
            return "offer_template";
        }

        return "redirect:/";
    }

    @GetMapping("/my_ads")
    public String getUserAds(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((CustomUserDetails)principal).getUsername();
        User user = userRepository.findByEmail(username);
        List<Ad> userAds = adRepository.findByUserId(user.getId());
        model.addAttribute("adsList", userAds);
        return "my_ads";
    }

    @PostMapping("/delete")
    public String deleteAd(@RequestParam Long id) {
        adRepository.deleteById(id);
        return "redirect:/my_ads";
    }
}
