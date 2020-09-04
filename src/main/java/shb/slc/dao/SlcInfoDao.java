package shb.slc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shb.slc.dto.SlcInfoDto;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SlcInfoDao extends JpaRepository<SlcInfoDto, Long>,
        QuerydslPredicateExecutor<SlcInfoDto> {
    // CrudRepository 랑 다른점 (수요일)
    // jpa 는 hibernate orm api 사용 위함

    SlcInfoDto findByLicInfoNo(Long licInfoNo);
//    SlcInfoDto findByLicNm(String licNm);
}
