package br.com.euriconeto.todolist.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {

    List<TaskModel> findByIdUser(UUID idUser);
    TaskModel findByIdAndUser(UUID id, UUID idUser);
}
