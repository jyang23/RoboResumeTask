package com.byaj.roboresume.models;

import com.byaj.roboresume.models.Resume;
import org.springframework.data.repository.CrudRepository;

public interface ResumeRepository extends CrudRepository<Resume, Long> {
}
