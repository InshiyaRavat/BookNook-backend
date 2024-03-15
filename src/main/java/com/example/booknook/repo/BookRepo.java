    package com.example.booknook.repo;

    import com.example.booknook.model.Book;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.repository.query.Param;
    import org.springframework.data.rest.core.annotation.RepositoryRestResource;

    import java.util.List;

    @RepositoryRestResource
    public interface BookRepo extends JpaRepository<Book,Long> {
        Book findIdByTitle(@Param("title") String title);

        List<Book> findAllByCategory(@Param("category") String category);
    }
