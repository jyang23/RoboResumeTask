package com.byaj.roboresume;

import com.byaj.roboresume.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    ResumeRepository repository;

    @Autowired
    ExperienceRepository experiencerepository;

    @Autowired
    SkillRepository skillrepository;

    @Autowired
    EducationRepository educationrepository;

    @RequestMapping("/")
    public String myhomepage(Model model){
        model.addAttribute("resumes", repository.findAll());
        model.addAttribute("skills", repository.findAll());
        return "hometemplate";
    }

    @GetMapping("/addresume")
    public String addResumeForm(Model model){
        model.addAttribute("resume", new Resume());
        model.addAttribute("skill", new Skill());
        return "resumeform";
    }

    @RequestMapping("/detail")
    public String getResume(Model model)
    {
        model.addAttribute("resumes", new Resume());
        model.addAttribute("skills", new Skill());
        return "resumeform";
    }

    @RequestMapping("/addresume")
    public String myresume(@Valid Resume resume, BindingResult result){
        if(result.hasErrors())
        {
            return "resumeform";
        }
        return "confirm";
    }

    @PostMapping("/processresume")
    public String processForm(@Valid Resume resume, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "resumeform";
        }
        repository.save(resume);
        return "redirect:/";
    }
    @RequestMapping("detail/{id}")
    public String showResume(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("resume", repository.findById(id).get());
        model.addAttribute("skill", repository.findById(id).get());
        return "show";
    }
    @RequestMapping("/addskill/{id}")
    public String addSkill(@PathVariable("id") long id, Model model){
        Skill skill = new Skill();
        skill.setResumeid(id);
        model.addAttribute("skill", skill);
        return "skillform";
    }
    @PostMapping("/processskill")
    public String processSkillForm(@Valid Skill skill, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "skillform";
        }
        skillrepository.save(skill);
        return "redirect:/";
    }
    @RequestMapping("/addeducation/{id}")
    public String addEducation(@PathVariable("id") long id, Model model){
        Education education = new Education();
        education.setResumeid(id);
        model.addAttribute("education", education);
        return "confirm";
    }

    @PostMapping("/processeducation")
    public String processEducationForm(@Valid Education education, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "educationform";
        }
        educationrepository.save(education);
        return "redirect:/";
    }
    @RequestMapping("/addexperience/{id}")
    public String addExperience(@PathVariable("id") long id, Model model){
        Experience experience = new Experience();
        experience.setResumeid(id);
        model.addAttribute("experience", experience);
        return "confirm";
    }

    @PostMapping("/processexperience")
    public String processSkillForm(@Valid Experience experience, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "experienceform";
        }
        experiencerepository.save(experience);
        return "redirect:/";
    }

}
