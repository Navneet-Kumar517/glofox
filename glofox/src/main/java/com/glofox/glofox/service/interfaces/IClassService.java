package com.glofox.glofox.service.interfaces;

import com.glofox.glofox.model.StudioClass;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IClassService {
    StudioClass createClass(StudioClass studioClass);
    List<StudioClass> getAllClasses();
    Optional<StudioClass> findClassByNameAndDate(String className, LocalDate date);
}
