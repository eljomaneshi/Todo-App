package com.eljo.rest.webservices.restfulwebservices.todo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TodoServiceTest {

    private TodoService todoService;

    @BeforeEach
    public void setUp() {
        todoService = new TodoService();
        // Reset static variables
        TodoService.todos.clear();
        TodoService.todosCount = 0;
    }

    @Test
    public void testAddTodo_GoodPath() {
        Todo todo = todoService.addTodo("user1", "Test description", LocalDate.now(), TodoEnum.TODO, "Test notes");
        assertNotNull(todo);
        assertEquals(1, todo.getId());
        assertEquals("user1", todo.getUsername());
    }

    @Test
    public void testFindByUsername_GoodPath() {
        todoService.addTodo("user1", "Test description", LocalDate.now(), TodoEnum.TODO, "Test notes");
        List<Todo> todos = todoService.findByUsername("user1");
        assertFalse(todos.isEmpty());
        assertEquals(1, todos.size());
        assertEquals("user1", todos.get(0).getUsername());
    }

    @Test
    public void testDeleteById_GoodPath() {
        Todo todo = todoService.addTodo("user1", "Test description", LocalDate.now(), TodoEnum.TODO, "Test notes");
        todoService.deleteById(todo.getId());
        List<Todo> todos = todoService.findByUsername("user1");
        assertTrue(todos.isEmpty());
    }

    @Test
    public void testFindById_GoodPath() {
        Todo todo = todoService.addTodo("user1", "Test description", LocalDate.now(), TodoEnum.TODO, "Test notes");
        Todo foundTodo = todoService.findById(todo.getId());
        assertNotNull(foundTodo);
        assertEquals(todo.getId(), foundTodo.getId());
    }

    @Test
    public void testUpdateTodo_GoodPath() {
        Todo todo = todoService.addTodo("user1", "Test description", LocalDate.now(), TodoEnum.TODO, "Test notes");
        todo.setDescription("Updated description");
        todoService.updateTodo(todo);
        Todo updatedTodo = todoService.findById(todo.getId());
        assertEquals("Updated description", updatedTodo.getDescription());
    }

    @Test
    public void testFindById_BadPath() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            todoService.findById(999);
        });
        assertNotNull(exception);
    }

    @Test
    public void testDeleteById_BadPath() {
        todoService.addTodo("user1", "Test description", LocalDate.now(), TodoEnum.TODO, "Test notes");
        todoService.deleteById(999);  // Attempt to delete non-existent ID
        List<Todo> todos = todoService.findByUsername("user1");
        assertFalse(todos.isEmpty());
    }
}
