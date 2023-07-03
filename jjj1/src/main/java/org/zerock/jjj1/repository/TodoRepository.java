package org.zerock.jjj1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.jjj1.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
