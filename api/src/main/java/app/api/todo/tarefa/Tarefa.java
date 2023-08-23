package app.api.todo.tarefa;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descricao;
    private boolean concluida;
    private String prioridade;
}
