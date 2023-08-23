package app.api.todo.tarefa;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/tarefa")
public class TarefaController {

    private TarefaService tarefaService;

    @Operation(summary = "Lista todas as tarefas")
    @GetMapping("/listarTodas")
    public List<Tarefa> listarTodas() {
        return tarefaService.listarTodas();
    }

    @Operation(summary = "Busca uma tarefa específica")
    @GetMapping("/buscar/{id}")
    public Optional<Tarefa> buscar(@PathVariable Long id) {
        return tarefaService.buscar(id);
    }

    @Operation(summary = "Lista todas as tarefas pendentes")
    @GetMapping("/pendentes")
    public List<Tarefa> listarPendentes() {
        return tarefaService.listaPendentes();
    }

    @Operation(summary = "Cria uma nova tarefa")
    @PostMapping("/criar")
    public ResponseEntity<Map<String, String>> criar(@RequestBody Tarefa tarefa) {
        return tarefaService.criar(tarefa);
    }

    @Operation(summary = "Edita uma tarefa existente")
    @PostMapping("/editar")
    public ResponseEntity<Map<String, String>> editar(@RequestBody Tarefa tarefa) {
        return tarefaService.editar(tarefa);
    }

    @Operation(summary = "Deleta uma tarefa")
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Map<String, String>> excluir(@PathVariable Long id) {
        return tarefaService.excluir(id);
    }

    @Operation(summary = "Marca/Desmarca uma tarefa")
    @PostMapping("/marcar")
    public ResponseEntity<Map<String, String>> marcar (@RequestBody Tarefa tarefa) {
        return tarefaService.marcar(tarefa.getId(), tarefa.isConcluida());
    }

}
