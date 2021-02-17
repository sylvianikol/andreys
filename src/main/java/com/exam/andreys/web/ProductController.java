package com.exam.andreys.web;

import com.exam.andreys.model.binding.ProductAddBindingModel;
import com.exam.andreys.model.service.ProductServiceModel;
import com.exam.andreys.model.view.ProductViewModel;
import com.exam.andreys.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        }

        if (!model.containsAttribute("productAddBindingModel")) {
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
            model.addAttribute("exists", false);
        }

        return "add-product";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.productAddBindingModel",
                    bindingResult);

            return "redirect:add";
        }

        boolean isAdded = this.productService.add(this.modelMapper
                .map(productAddBindingModel, ProductServiceModel.class));

        if (!isAdded) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("exists", true);

            return "redirect:add";
        }

        return "redirect:/";
    }

    @GetMapping("/details/{name}")
    public String details(@PathVariable String name, HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        }

        model.addAttribute("product", this.modelMapper
                .map(this.productService.getByName(name), ProductViewModel.class));

        return "details-product";
    }

    @PostMapping("/details/{name}")
    public String delete(@PathVariable String name, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        }

        this.productService.deleteByName(name);

        return "redirect:/";
    }
}
