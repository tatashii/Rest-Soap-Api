package org.api.rest.services;

import org.api.rest.dao.impl.AddressDAO;
import org.api.rest.dao.impl.WorkDAO;
import org.api.rest.dto.AddressDto;
import org.api.rest.dto.WorkDto;
import org.api.rest.entity.Address;
import org.api.rest.entity.Employee;
import org.api.rest.entity.Work;
import org.api.rest.entityManager.Database;
import org.api.rest.mapper.AddressMapper;
import org.api.rest.mapper.WorkMapper;

import java.util.List;

public class WorkServices {

    private WorkDAO workDAO;
    private WorkMapper workMapper;
    private WorkDto workDto;

    public WorkServices() {

        this.workDAO = new WorkDAO();
        this.workMapper = WorkMapper.INSTANCE;
        this.workDto = new WorkDto();

    }

    public List<WorkDto> getAllWorks() {
        try {
            return Database.doInTransaction(em -> {
                List<Work> workes = workDAO.getAll(em);
                return workMapper.toListDto(workes);
            });
        } catch (Exception e) {
            System.out.println("Failed to retrieve workes from the database" + e);

            throw new RuntimeException("Failed to retrieve workes", e);
        }
    }

    public boolean addWork(WorkDto workDto) {
        try {
            Work work = workMapper.toEntity(workDto);
            Database.doInTransactionWithoutResult(em -> {
                em.persist(work);
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateWork(WorkDto workDto) {
        try {
            Work existingWork = Database.doInTransaction(em -> {
                return em.find(Work.class, workDto.getId());
            });
            if (existingWork == null) {
                throw new IllegalArgumentException("Work with ID " + workDto.getId() + " not found");
            }
            Database.doInTransactionWithoutResult(em -> {
                em.merge(existingWork);
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteWork(int workId) {
        try {
            Database.doInTransactionWithoutResult(em -> {
                Work work = em.find(Work.class, workId); // Fetch the entity within the transactional context
                if (work != null) {
                    em.remove(work);
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
