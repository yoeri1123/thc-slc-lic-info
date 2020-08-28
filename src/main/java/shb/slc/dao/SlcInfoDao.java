package shb.slc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
public interface SlcInfoDao extends JpaRepository<SlcInfoDao, String> {
    // CrudRepository 랑 다른점 (수요일)
    // jpa 는 hibernate orm api 사용 위함 (

}
