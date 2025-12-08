package br.com.easy.aalife.modules.exercicio.service;

import br.com.easy.aalife.modules.exercicio.dto.ExercicioRequest;
import br.com.easy.aalife.modules.exercicio.model.Exercicio;
import br.com.easy.aalife.modules.exercicio.repository.ExercicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository repository;

    public void salvar(ExercicioRequest request, MultipartFile imagemExercicio) {
        var exercicio = Exercicio.of(request, imagemExercicio.getName());
        repository.save(exercicio);
    }
}
