package com.example.templatesample.service;

import com.example.templatesample.model.Professor;
import com.example.templatesample.repository.ProfessorRepository;
import com.example.templatesample.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public ResponseEntity<String> createProfessor(Professor professor) {
        profileRepository.save(professor);
        return new ResponseEntity<>("Successfully create professor " + professorRepository.save(professor).getProfileID(), HttpStatus.OK);
    }

    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public Optional<Professor> getProfessorById(String id) {
        return professorRepository.findById(id);
    }

    @Override
    public ResponseEntity<Professor> updateProfessor(String id, Professor professor) {
        Optional<Professor> professorData = professorRepository.findById(id);
        if(professorData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Professor _professor = professorData.get();
        _professor.updateProfessor(professor);
        professorRepository.save(_professor);
        profileRepository.save(_professor);
        return new ResponseEntity<>(_professor, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteProfessor(String id) {
        try {
            professorRepository.deleteById(id);
            profileRepository.deleteById(id);

            return new ResponseEntity<>("Successfully delete a professor " + id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot find a professor]",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
