package shb.slc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shb.slc.dto.SlcRegDto;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SlcRegDao extends JpaRepository<SlcRegDto, Long> {
    SlcRegDto findBySlcRegNo(Long slcRegNo);
    SlcRegDto findByPurCntrctNo(String purCntrctNo);
}
