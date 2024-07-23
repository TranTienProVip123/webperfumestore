package web.Webperfume.repository;



import  web.Webperfume.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    void deleteByProductId(Long productId);
}
