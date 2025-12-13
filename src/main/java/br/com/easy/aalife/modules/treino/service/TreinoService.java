package br.com.easy.aalife.modules.treino.service;

import br.com.easy.aalife.modules.treino.dto.TreinoRequest;
import br.com.easy.aalife.modules.treino.model.Treino;
import br.com.easy.aalife.modules.treino.repository.TreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final TreinoRepository repository;

    public void salvar(TreinoRequest request, MultipartFile imagemExercicio) {
        var exercicio = Treino.of(request, imagemExercicio.getName());
        repository.save(exercicio);
    }
}
