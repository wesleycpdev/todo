package app.api.todo.tarefa;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TarefaService {

    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> buscar(Long id) {
        return tarefaRepository.findById(id);
    }

    public List<Tarefa> listaPendentes() {
        return tarefaRepository.findAll().stream().filter(i -> !i.isConcluida()).collect(Collectors.toList());
    }

    public ResponseEntity<Map<String, String>> criar(Tarefa tarefa) {
        Map<String, String> response = new HashMap<>();

        tarefaRepository.save(tarefa);

        response.put("status", "success");
        response.put("message", "Tarefa criada com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Map<String, String>> editar(Tarefa tarefa) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(tarefa.getId());
        Map<String, String> response = new HashMap<>();

        if (tarefaExistente.isPresent()) {
            tarefaExistente.get().setTitulo(tarefa.getTitulo());
            tarefaExistente.get().setDescricao(tarefa.getDescricao());
            tarefaExistente.get().setPrioridade(tarefa.getPrioridade());
            tarefaRepository.save(tarefaExistente.get());

            response.put("status", "success");
            response.put("message", "Tarefa atualizada com sucesso");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("status", "not found");
            response.put("message", "Tarefa não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> excluir(Long id) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);
        Map<String, String> response = new HashMap<>();

        if (tarefaExistente.isPresent()) {
            tarefaRepository.deleteById(id);

            response.put("status", "success");
            response.put("message", "Tarefa excluída com sucesso");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("status", "not found");
            response.put("message", "Tarefa não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> marcar(Long id, boolean opcao) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);
        Map<String, String> response = new HashMap<>();

        if(tarefaExistente.isPresent()) {
            tarefaExistente.get().setConcluida(opcao);
            tarefaRepository.save(tarefaExistente.get());

            response.put("status", "success");
            response.put("message", "Tarefa atualizada com sucesso");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("status", "not found");
            response.put("message", "Tarefa não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
