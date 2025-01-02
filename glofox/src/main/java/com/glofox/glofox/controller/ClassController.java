package com.glofox.glofox.controller;

import com.glofox.glofox.constants.Constants;
import com.glofox.glofox.model.ResponseModel;
import com.glofox.glofox.model.StudioClass;
import com.glofox.glofox.service.impl.ClassService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classes")
public class ClassController {
    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> createClass(@Valid @RequestBody StudioClass studioClass) {
        StudioClass createdClass = classService.createClass(studioClass);
        return ResponseEntity.ok(
                new ResponseModel(Constants.CLASS_CREATED_SUCCESSFULLY,
                        createdClass)
        );
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getAllClasses() {
        return ResponseEntity.ok(
                new ResponseModel(Constants.PRE_EXISTING_CLASSES,
                        classService.getAllClasses())
        );
    }
}
