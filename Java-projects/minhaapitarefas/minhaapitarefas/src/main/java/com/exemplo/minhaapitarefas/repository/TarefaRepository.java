package com.exemplo.minhaapitarefas.repository;

import com.exemplo.minhaapitarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interface é um componente de repositório
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // JpaRepository já nos dá métodos como save(), findById(), findAll(), deleteById()
    // Podemos adicionar métodos personalizados aqui, se necessário (ex: findByTitulo(String titulo))
}