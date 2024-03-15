    package com.example.booknook.repo;

    import com.example.booknook.model.Cart;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.repository.query.Param;
    import org.springframework.data.rest.core.annotation.RepositoryRestResource;

    @RepositoryRestResource
    public interface CartRepo extends JpaRepository<Cart, Long> {
    }
