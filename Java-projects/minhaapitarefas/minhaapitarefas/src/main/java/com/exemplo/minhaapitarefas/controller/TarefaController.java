package com.exemplo.minhaapitarefas.controller;

import com.exemplo.minhaapitarefas.model.Tarefa;
import com.exemplo.minhaapitarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/tarefas") // Define o caminho base para todos os endpoints neste controlador
public class TarefaController {

    @Autowired // Injeta uma instância de TarefaRepository
    private TarefaRepository tarefaRepository;

    // GET /api/tarefas - Retorna todas as tarefas
    @GetMapping
    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }

    // GET /api/tarefas/{id} - Retorna uma tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        return tarefa.map(ResponseEntity::ok) // Se encontrada, retorna 200 OK com a tarefa
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrada, retorna 404 Not Found
    }

    // POST /api/tarefas - Cria uma nova tarefa
    @PostMapping
    public ResponseEntity<Tarefa> createTarefa(@RequestBody Tarefa tarefa) {
        Tarefa savedTarefa = tarefaRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTarefa); // Retorna 201 Created
    }

    // PUT /api/tarefas/{id} - Atualiza uma tarefa existente
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaDetails) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);

        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setTitulo(tarefaDetails.getTitulo());
            tarefa.setDescricao(tarefaDetails.getDescricao());
            tarefa.setConcluida(tarefaDetails.isConcluida());
            Tarefa updatedTarefa = tarefaRepository.save(tarefa);
            return ResponseEntity.ok(updatedTarefa); // Retorna 200 OK com a tarefa atualizada
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }

    // DELETE /api/tarefas/{id} - Deleta uma tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content (sucesso sem conteúdo)
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }
}