package com.example.springbootcrud.controller;

import com.example.springbootcrud.entity.Course;
import com.example.springbootcrud.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class ViewController {

    private final CourseRepository courseRepository;

    @Autowired
    public ViewController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @GetMapping("/view")
    public String viewAllCourses(Model model){
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "view";
    }

    @PutMapping ("/edit/{id}")
    public String updateCourse(@PathVariable long id, @RequestBody Course course) {
        Optional<Course> courseData = courseRepository.findById(id);
        if (courseData.isPresent()) {
            Course _course = courseData.get();
            _course.setTitle(course.getTitle());
            _course.setDescription(course.getDescription());
            _course.setFull(course.isFull());
            courseRepository.save(_course);
        }
        return "redirect:/courses/view";
    }
    @GetMapping("/remove/{id}")
    public String removeCourse(@PathVariable long id) {
        courseRepository.deleteById(id);
        return "redirect:/courses/view";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        return "create";
    }

    @PostMapping("/courses/view")
    public String createCourse(@ModelAttribute Course course) {
        courseRepository.save(course);
        return "redirect:/courses/view";
        }

}


