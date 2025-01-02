package com.glofox.glofox.service.impl;

import com.glofox.glofox.model.StudioClass;
import com.glofox.glofox.service.interfaces.IClassService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService implements IClassService {
    private final List<StudioClass> classes;

    //Constructor
    public ClassService() {
        this.classes = new ArrayList<>();
    }

    public StudioClass createClass(StudioClass studioClass) {
        // Check if a class with the same name and dates already exists
        boolean classExists = classes.stream()
                .anyMatch(c -> c.getClassName().equals(studioClass.getClassName()) &&
                        c.getStartDate().equals(studioClass.getStartDate()) &&
                        c.getEndDate().equals(studioClass.getEndDate()));

        if (classExists) {
            throw new IllegalArgumentException("A class with these details already exists");
        }

        classes.add(studioClass);
        return studioClass;
    }

    public List<StudioClass> getAllClasses() {
        return new ArrayList<>(classes);
    }

    public Optional<StudioClass> findClassByNameAndDate(String className, LocalDate date) {
        return classes.stream()
                .filter(c -> c.getClassName().equals(className) &&
                        !c.getStartDate().isAfter(date) && !c.getEndDate().isBefore(date))
                .findFirst();
    }
}
