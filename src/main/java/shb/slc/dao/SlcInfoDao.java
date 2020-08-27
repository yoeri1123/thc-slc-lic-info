package shb.slc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
public interface SlcInfoDao extends JpaRepository<SlcInfoDao, String> {

}
