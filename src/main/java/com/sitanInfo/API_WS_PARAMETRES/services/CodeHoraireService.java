package com.sitanInfo.API_WS_PARAMETRES.services;

import com.sitanInfo.API_WS_PARAMETRES.model.CodeHoraire;
import com.sitanInfo.API_WS_PARAMETRES.repository.CodeHoraireRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class CodeHoraireService {

    @Autowired
    private CodeHoraireRepository codeHoraireRepository;

    public ResponseEntity<CodeHoraire> create(CodeHoraire codeHoraire){
         codeHoraireRepository.save(codeHoraire);
         return ResponseEntity.ok(codeHoraire);
    }

    public List<CodeHoraire> lire() {
        return codeHoraireRepository.findAll();
    }

    public Optional<CodeHoraire> findByid(Integer id) {
        return codeHoraireRepository.findById(id);
    }

    public void delete(Integer id) {
        codeHoraireRepository.deleteById(id);
    }

    public CodeHoraire updateCodeHoraire(CodeHoraire codeHoraire){
        return codeHoraireRepository.save(codeHoraire);
    }
}
