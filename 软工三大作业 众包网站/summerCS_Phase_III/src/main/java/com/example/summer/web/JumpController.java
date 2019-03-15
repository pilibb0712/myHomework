package com.example.summer.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JumpController {
    @RequestMapping("/")
    public String landingPage(){
        return "index";
    }

    @RequestMapping("/homepage")
    public String homePage(){
        return "home-page";
    }

    @RequestMapping("/homeproject")
    public String projectDetail(@RequestParam String proId, Model model){
        model.addAttribute("projectId", proId);
        return "home-project-details";
    }

    @RequestMapping("/asALauncher")
    public String launcher(){
        return "as-a-launcher";
    }

    @RequestMapping("/launcherproject")
    public String launcherProject(@RequestParam String proId, Model model){
        model.addAttribute("projectId", proId);
        return "launcher-project-details";
    }

    @RequestMapping("/asAWorker")
    public String worker(){
        return "as-a-worker";
    }

    @RequestMapping("/workerproject")
    public String workProject(@RequestParam String proId, Model model){
        model.addAttribute("projectId", proId);
        return "worker-project-details";
    }

    @RequestMapping("/launch")
    public String launchProject(){
        return "launch-project";
    }

    @RequestMapping("/createProject")
    public String createProject(){
        return "launcher-project-details";
    }

    @RequestMapping("/markPicture")
    public String markPicture(@RequestParam String proId, @RequestParam String markType, Model model){
        model.addAttribute("projectId", proId);
        model.addAttribute("markType", markType);
        return "mark-picture";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin-signin";
    }

    @RequestMapping("/admin-homepage")
    public String adminHome(){
        return "admin";
    }

    @RequestMapping("/test")
    public String test(){
        return "for-test";
    }

    @RequestMapping("/integratedpics")
    public String integrated(@RequestParam String proId, Model model){
        model.addAttribute("projectId", proId);
        return "integrated-pics";
    }

    @RequestMapping("/analysis")
    public String analysis(){
        return "analysis";
    }
}
