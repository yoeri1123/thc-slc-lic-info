package shb.slc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import shb.slc.dto.SlcEosDto;
import shb.slc.dto.SlcEosPK;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SlcEosDao extends JpaRepository<SlcEosDto, SlcEosPK>, QuerydslPredicateExecutor<SlcEosDto> {
//    SlcEosDto findBy
    SlcEosDto findByLicNmAndLicVer(String licNm, String licVer);
}
